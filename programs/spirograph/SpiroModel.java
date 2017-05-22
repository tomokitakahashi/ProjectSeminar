package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;
import java.awt.Color;

public class SpiroModel extends Model
{
  // ファイルロードしているかの状態保存
  public Boolean isLoading;

  // Viewのアニメーションが止まっているかの状態保存
  public Boolean isStop;

  // スピログラフで描かれた軌跡モデル
  private SpiroLocusModel spiroLocusModel;

  // スピログラフのスパーギアモデル
  private SpurModel spurModel;

  // スピログラフのピニオンモデル
  private PinionModel pinionModel;

  //軸線の角度
  private double axisDegree;

  // ギア同士の距離
  private double gearDistance;

  // メニューのモデル (セーブなどは)
  private MenuModel menuModel;

  // スピログラフの選択色
  private Color selectedColor;

  public SpiroModel()
  {
    super();
    menuModel = null;
    spiroLocusModel = new SpiroLocusModel();
    spurModel = new SpurModel(SpiroConstruct.SPIRO_WINDOW_CENTER,SpiroConstruct.SPUR_RADIUS);
    pinionModel = new PinionModel(SpiroConstruct.PINION_CENTER,SpiroConstruct.PINION_RADIUS);
    isStop = true;
    isLoading = false;
    axisDegree = 0.0;
    gearDistance = SpiroConstruct.PINION_CENTER.x - SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    selectedColor = SpiroConstruct.RGB_INIT_COLOR;
    return;
  }

  // ピニオンギアのモデルを応答する
  public PinionModel getPinionModel()
  {
    return pinionModel;
  }

  public void setPinionModel(GearModel aGearModel)
  {
    pinionModel = (PinionModel)aGearModel;
    return;
  }

  // スパーギアのモデルを応答
  public SpurModel getSpurModel()
  {
    return spurModel;
  }

  public void setSpurModel(GearModel aGearModel)
  {
    spurModel = (SpurModel)aGearModel;
    return;
  }

  public SpiroLocusModel getSpiroLocusModel()
  {
    return spiroLocusModel;
  }

  public void setSpiroLocusModel(SpiroLocusModel aSpiroLocusModel)
  {
    spiroLocusModel = aSpiroLocusModel;
    return;
  }

  public double degree()
  {
    return axisDegree;
  }

  public void setDegree()
  {
    axisDegree += 0.1;
    return;
  }

  public void axisDegree(double newDegree)
  {
    axisDegree = newDegree;
    return;
  }

  public double gearDistance()
  {
    return gearDistance;
  }

  public void gearDistance(double newDistance)
  {
    gearDistance = newDistance;
    return;
  }

  // アニメーションが止まっているかを設定するメソッド
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

  //アニメーションが止まっているかを応答するメソッド
  public Boolean isStop()
  {
    return isStop;
  }

  // リスタートした時に再設定するメソッド
  public void restartModel()
  {
    pinionModel.restart(gearDistance);
    return;
  }

  public void changePinionPosition()
  {
    pinionModel.changeCenterPosition(Math.toRadians(axisDegree),gearDistance);
    this.updateGearDistance();
    return;
  }
  //アニメーションでモデルを更新するメソッド
  // MEMO: 色、軌跡のデータ更新
  public void updateByAnimation()
  {
    pinionModel.animationManager(Math.toRadians(axisDegree),gearDistance);

    // create Rainbow Color with HSB
    int hsbColorBit = Color.HSBtoRGB((float)Math.toRadians(axisDegree),1,1);
    Color rgbColor = new Color(hsbColorBit);

    spiroLocusModel.locusList().add(pinionModel.pencilLocusCoodinate());
    spiroLocusModel.locusColorList().add(rgbColor);

    return;
  }

  //マウスプレスに応じてモデルを更新するメソッド
  public void updateByPress(Point aPoint)
  {
    if(!isStop) return;
    spurModel.judgePressArea(aPoint);
    pinionModel.judgePressArea(aPoint);
    return;
  }

  // マウスドラッグに応じてモデルを更新するメソッド
  public void updateByDrag(Point aPoint)
  {
    if(!isStop) return;
    spurModel.updateByDrag(aPoint);
    pinionModel.updateByDrag(aPoint);
    this.updateCurrent();
    return;
  }

  // マウスリリースに応じてモデルを更新するメソッド
  public void updateByRelease(Point aPoint)
  {
    if(!isStop) return;
    spurModel.updateByRelease(aPoint);
    pinionModel.updateByRelease(aPoint);
    return;
  }

  // イベントによる最新の座標等を更新するメソッド
  public void updateCurrent()
  {
    double radian = Math.toRadians(axisDegree);
    double spurRadius = spurModel.radius();
    double newDistance = gearDistance * spurModel.radius()/spurModel.previousRadius;
    Point2D.Double coodinate = new Point2D.Double(Math.cos(radian)*spurRadius+SpiroConstruct.SPIRO_WINDOW_CENTER.x,Math.sin(radian)*spurRadius+SpiroConstruct.SPIRO_WINDOW_CENTER.y);
    pinionModel.updateCurrent(radian,coodinate);
    return;
  }

  private void updateGearDistance()
  {
    double distanceX = (pinionModel.centerCoodinate().x - spurModel.centerCoodinate().x);
    double distanceY = (pinionModel.centerCoodinate().y - spurModel.centerCoodinate().y);
    gearDistance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
    return;
  }

  public void clearLocus()
  {
    spiroLocusModel.clear();
    return;
  }

  //MenuModelをセットするメソッド
  //ファイルシステムに関する処理はMenuModel内で行うため
  //SpiroModelとMenuModelは「関係」している必要がある
  public void setMenuModel(MenuModel aMenuModel)
  {
    menuModel = aMenuModel;
    return;
  }

  public SpiroView getSpiroView()
  {
    return (SpiroView)dependents.get(0);
  }

  public Color getSpiroColor()
  {
    return selectedColor;
  }

  public void setSpiroColor(Color aColor)
  {
    selectedColor = aColor;
    return;
  }

}
