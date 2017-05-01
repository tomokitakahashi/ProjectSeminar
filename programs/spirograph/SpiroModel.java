package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

public class SpiroModel extends Model
{
  public Boolean isStop;
  // スピログラフで描かれた軌跡モデル
  private SpiroLocusModel spiroLocusModel;

  // スピログラフのスパーギアモデル
  private SpurModel spurModel;

  // スピログラフのピニオンモデル
  private PinionModel pinionModel;

  private double axisDegree;

  private double gearDistance;

  public SpiroModel()
  {
    super();
    spurModel = new SpurModel(SpiroConstruct.SPIRO_WINDOW_CENTER,SpiroConstruct.SPUR_RADIUS);
    pinionModel = new PinionModel(SpiroConstruct.PINION_CENTER,SpiroConstruct.PINION_RADIUS);
    isStop = true;
    axisDegree = 0.0;
    double distanceX = (pinionModel.centerCoodinate().x - spurModel.centerCoodinate().x);
    double distanceY = (pinionModel.centerCoodinate().y - spurModel.centerCoodinate().y);
    gearDistance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
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

  public double radian()
  {
    return axisDegree;
  }
  public void setRadian()
  {
    axisDegree += 0.1;
    return;
  }

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

  public Boolean isStop()
  {
    return isStop;
  }

  public void updateByAnimation()
  {
    pinionModel.animationManager(Math.toRadians(axisDegree),spurModel.radius,gearDistance);
    return;
  }

  public void updateByPress(Point aPoint)
  {
    spurModel.judgePressArea(aPoint);
    pinionModel.judgePressArea(aPoint);
    return;
  }

  public void updateByDrag(Point aPoint)
  {
    spurModel.updateByDrag(aPoint);
    pinionModel.updateByDrag(aPoint);
    this.updateRelative();
    return;
  }

  public void updateRelative()
  {
    double radian = Math.toRadians(axisDegree);
    double spurRadius = spurModel.radius();
    double newDistance = gearDistance * spurModel.radius()/spurModel.previousRadius;
    Point2D.Double coodinate = new Point2D.Double(Math.cos(radian)*spurRadius+SpiroConstruct.SPIRO_WINDOW_CENTER.x,Math.sin(radian)*spurRadius+SpiroConstruct.SPIRO_WINDOW_CENTER.y);
    pinionModel.updateRelativeCenter(radian,coodinate);
    return;
  }

  public void updateByRelease(Point aPoint)
  {
    spurModel.updateByRelease(aPoint);
    pinionModel.updateByRelease(aPoint);
    return;
  }

}
