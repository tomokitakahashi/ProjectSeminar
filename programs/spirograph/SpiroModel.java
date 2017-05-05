package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

public class SpiroModel extends Model
{
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

  public SpiroModel()
  {
    super();
    spurModel = new SpurModel(SpiroConstruct.SPIRO_WINDOW_CENTER,SpiroConstruct.SPUR_RADIUS);
    pinionModel = new PinionModel(SpiroConstruct.PINION_CENTER,SpiroConstruct.PINION_RADIUS);
    isStop = true;
    axisDegree = 0.0;
    gearDistance = SpiroConstruct.PINION_CENTER.x - SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    return;
  }

  // ピニオンギアのモデルを応答する
  public PinionModel getPinionModel()
  {
    return pinionModel;
  }
  // スパーギアのモデルを応答
  public SpurModel getSpurModel()
  {
    return spurModel;
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
  // アニメーションが止まっているかを設定するメソッド
  public void setStop(Boolean aBool)
  {
    if (aBool)
    {
      pinionModel.dataReset();
      spurModel.dataReset();
    }
    double distanceX = (pinionModel.centerCoodinate().x - spurModel.centerCoodinate().x);
    double distanceY = (pinionModel.centerCoodinate().y - spurModel.centerCoodinate().y);
    gearDistance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
    isStop = aBool;
    return;
  }

  //アニメーションが止まっているかを応答するメソッド
  public Boolean isStop()
  {
    return isStop;
  }

  //アニメーションでモデルを更新するメソッド
  public void updateByAnimation()
  {
    pinionModel.animationManager(Math.toRadians(axisDegree),spurModel.radius,gearDistance);
    return;
  }

  //マウスプレスに応じてモデルを更新するメソッド
  public void updateByPress(Point aPoint)
  {
    spurModel.judgePressArea(aPoint);
    pinionModel.judgePressArea(aPoint);
    return;
  }

  // マウスドラッグに応じてモデルを更新するメソッド
  public void updateByDrag(Point aPoint)
  {
    spurModel.updateByDrag(aPoint);
    pinionModel.updateByDrag(aPoint);
    this.updateRelative();
    return;
  }

  // マウスリリースに応じてモデルを更新するメソッド
  public void updateByRelease(Point aPoint)
  {
    spurModel.updateByRelease(aPoint);
    pinionModel.updateByRelease(aPoint);
    return;
  }

  // イベントによる最新の座標等を更新するメソッド
  public void updateRelative()
  {
    double radian = Math.toRadians(axisDegree);
    double spurRadius = spurModel.radius();
    double newDistance = gearDistance * spurModel.radius()/spurModel.previousRadius;
    Point2D.Double coodinate = new Point2D.Double(Math.cos(radian)*spurRadius+SpiroConstruct.SPIRO_WINDOW_CENTER.x,Math.sin(radian)*spurRadius+SpiroConstruct.SPIRO_WINDOW_CENTER.y);
    pinionModel.updateRelative(radian,coodinate);
    return;
  }

}
