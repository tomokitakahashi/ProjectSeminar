package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;
import java.awt.Color;

public class SpiroModel extends Model
{
  /**
  * 虹色に設定するためのフラグ
  **/
  private Boolean isRainbow;

  /**
  * Viewのアニメーションが止まっているかの状態保存
  **/
  public Boolean isStop;

  /**
  * スピログラフで描かれた軌跡モデル
  **/
  private SpiroLocusModel spiroLocusModel;

  /**
  * スピログラフのスパーギアモデル
  */
  private SpurModel spurModel;

  /**
  * スピログラフのピニオンモデル
  **/
  private PinionModel pinionModel;

  /**
  * 軸線の角度
  **/
  private double axisDegree;

  /**
  * ギア同士の距離
  **/
  private double gearDistance;

  /**
  * メニューのモデル (セーブなどは)
  **/
  private MenuModel menuModel;

  /**
  * スピログラフの選択色
  **/
  private Color selectedColor;

  /**
  * SpiroModelのコンストラクタ
  **/
  public SpiroModel()
  {
    super();
    menuModel = null;
    spiroLocusModel = new SpiroLocusModel();
    spurModel = new SpurModel(SpiroConstruct.SPIRO_WINDOW_CENTER,SpiroConstruct.SPUR_RADIUS);
    pinionModel = new PinionModel(SpiroConstruct.PINION_CENTER,SpiroConstruct.PINION_RADIUS);
    isStop = true;
    isRainbow = false;
    axisDegree = 0.0;
    gearDistance = SpiroConstruct.PINION_CENTER.x - SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    selectedColor = SpiroConstruct.RGB_INIT_COLOR;
    return;
  }

  /**
  * ピニオンギアのモデルを応答する
  **/
  public PinionModel getPinionModel()
  {
    return pinionModel;
  }

  /**
  * ピニオンギアのモデルをセットする
  * @param aGearModel ギアモデル
  **/
  public void setPinionModel(GearModel aGearModel)
  {
    pinionModel = (PinionModel)aGearModel;
    return;
  }

  /**
  * スパーギアのモデルを応答
  **/
  public SpurModel getSpurModel()
  {
    return spurModel;
  }

  /**
  * スパーギアのモデルをセットする
  * @param aGearModel ギアモデル
  **/
  public void setSpurModel(GearModel aGearModel)
  {
    spurModel = (SpurModel)aGearModel;
    return;
  }

  /**
  * スピロモデルの軌跡モデルを応答する
  **/
  public SpiroLocusModel getSpiroLocusModel()
  {
    return spiroLocusModel;
  }

  /**
  * スピロモデルの軌跡モデルをセットする
  * @param aSpiroLocusModel 軌跡モデル
  **/
  public void setSpiroLocusModel(SpiroLocusModel aSpiroLocusModel)
  {
    spiroLocusModel = aSpiroLocusModel;
    return;
  }

  /**
  * 角度を応答する
  **/
  public double degree()
  {
    return axisDegree;
  }

  /**
  * 角度を増やす
  **/
  public void setDegree()
  {
    axisDegree += 0.1;
    return;
  }

  /**
  * 軸角度をセットする
  * @param newDegree 新しい角度
  **/
  public void axisDegree(double newDegree)
  {
    axisDegree = newDegree;
    return;
  }

  /**
  * ギア同士の距離を応答する
  **/
  public double gearDistance()
  {
    return gearDistance;
  }

  /**
  * ギア同士の距離をセットする
  **/
  public void gearDistance(double newDistance)
  {
    gearDistance = newDistance;
    return;
  }

  /**
  * アニメーションを止めるメソッド
  * @param aBool 真偽
  **/
  public void setStop(Boolean aBool)
  {
    if (aBool)
    {
      pinionModel.dataReset();
      spurModel.dataReset();
    }
    this.updateGearDistance();
    isStop = aBool;
    return;
  }

  /**
  * アニメーションが止まっているかを応答するメソッド
  **/
  public Boolean isStop()
  {
    return isStop;
  }

  /**
  * リスタートした時に再設定するメソッド
  **/
  public void restartModel()
  {
    pinionModel.restart(gearDistance);
    return;
  }

  /**
  * 内接・外接を切り替えた時に呼ばれるメソッド
  **/
  public void changePinionPosition()
  {
    this.updateGearDistance();
    pinionModel.changeCenterPosition(Math.toRadians(axisDegree),gearDistance,spurModel.centerCoodinate());
    this.updateGearDistance();
    return;
  }

  /**アニメーションでモデルを更新するメソッド
  * MEMO: 色、軌跡のデータ更新
  **/
  public void updateByAnimation()
  {
    pinionModel.animationManager(Math.toRadians(axisDegree),gearDistance,spurModel.centerCoodinate());

    spiroLocusModel.locusList().add(pinionModel.pencilLocusCoodinate());
    if(isRainbow)
    {
      int hsbColorBit = Color.HSBtoRGB((float)Math.toRadians(axisDegree),1,1);
      Color rgbColor = new Color(hsbColorBit);
      spiroLocusModel.locusColorList().add(rgbColor);
    } else
    {
      spiroLocusModel.locusColorList().add(selectedColor);
    }

    return;
  }

  /**
  * マウスプレスに応じてモデルを更新するメソッド
  * @param aPoint マウスポイント
  **/
  public void updateByPress(Point aPoint)
  {
    if(!isStop) return;
    spurModel.judgePressArea(aPoint);
    pinionModel.judgePressArea(aPoint);
    return;
  }

  /**
  * マウスドラッグに応じてモデルを更新するメソッド
  * @param aPoint マウスポイント
  **/
  public void updateByDrag(Point aPoint)
  {
    if(!isStop) return;
    spurModel.updateByDrag(aPoint);
    pinionModel.updateByDrag(aPoint);
    this.updateCurrent();
    return;
  }

  /**
  * マウスリリースに応じてモデルを更新するメソッド
  * @param aPoint マウスポイント
  **/
  public void updateByRelease(Point aPoint)
  {
    if(!isStop) return;
    spurModel.updateByRelease(aPoint);
    pinionModel.updateByRelease(aPoint);
    return;
  }

  /**
  * イベントによる最新の座標等を更新するメソッド
  **/
  public void updateCurrent()
  {
    double radian = Math.toRadians(axisDegree);
    double spurRadius = spurModel.radius();
    Point2D.Double coodinate = new Point2D.Double(Math.cos(radian)*spurRadius+spurModel.centerCoodinate().x,Math.sin(radian)*spurRadius+spurModel.centerCoodinate().y);
    pinionModel.updateCurrent(radian,coodinate);
    return;
  }

  /**
  * 最新のギア同士の距離を設定するメソッド
  **/
  private void updateGearDistance()
  {
    double distanceX = (pinionModel.centerCoodinate().x - spurModel.centerCoodinate().x);
    double distanceY = (pinionModel.centerCoodinate().y - spurModel.centerCoodinate().y);
    gearDistance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
    return;
  }

  /**
  * 軌跡をクリアするメソッド
  **/
  public void clearLocus()
  {
    spiroLocusModel.clear();
    return;
  }

  /**
  * MenuModelをセットするメソッド
  * ファイルシステムに関する処理はMenuModel内で行うため
  * SpiroModelとMenuModelは「関係」している必要がある
  **/
  public void setMenuModel(MenuModel aMenuModel)
  {
    menuModel = aMenuModel;
    return;
  }

  /**
  * スピロビューのゲッター
  **/
  public SpiroView getSpiroView()
  {
    return (SpiroView)dependents.get(0);
  }

  /**
  * スピログラフの色のゲッター
  **/
  public Color getSpiroColor()
  {
    return selectedColor;
  }

  /**
  * スピログラフの色のセッター
  * @param aColor カラー
  **/
  public void setSpiroColor(Color aColor)
  {
    selectedColor = aColor;
    return;
  }

  /**
  * 虹色に設定するためのセッター
  * @param aBool 真偽
  **/
  public void setSpiroRainbowColor(Boolean aBool)
  {
    isRainbow = aBool;
    return;
  }

  /**
  * 現在の色が虹色かどうか応答するメソッド
  **/
  public Boolean isRainbow()
  {
    return isRainbow;
  }

}
