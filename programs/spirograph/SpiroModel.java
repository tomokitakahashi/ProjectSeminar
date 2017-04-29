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

  private double gearDistance;

  public SpiroModel()
  {
    super();
    spurModel = new SpurModel(SpiroConstruct.SPIRO_WINDOW_CENTER,SpiroConstruct.SPUR_RADIUS);
    pinionModel = new PinionModel(SpiroConstruct.PINION_CENTER,SpiroConstruct.PINION_RADIUS);
    isStop = true;
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

  public void setStop(Boolean aBool)
  {
    if (aBool) pinionModel.dataReset();
    isStop = aBool;
    return;
  }

  public Boolean isStop()
  {
    return isStop;
  }

  public void updateByRadian(double aRadian)
  {
    double distanceX = spurModel.centerCoodinate().x - pinionModel.centerCoodinate().x;
    double distanceY = spurModel.centerCoodinate().y - pinionModel.centerCoodinate().y ;
    double distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    pinionModel.animationManager(aRadian,spurModel.radius,distance);
    return;
  }

  public void updateByPress(Point aPoint)
  {
    spurModel.updateByPress(aPoint);
    pinionModel.updateByPress(aPoint);
    return;
  }

  public void updateByDrag(Point aPoint)
  {
    spurModel.updateByDrag(aPoint);
    pinionModel.updateByDrag(aPoint);
    return;
  }

  public void updateByRelease(Point aPoint)
  {
    spurModel.updateByRelease(aPoint);
    pinionModel.updateByRelease(aPoint);
    return;
  }

}
