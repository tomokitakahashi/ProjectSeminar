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

  private Boolean moveSpurEnabled;

  private Boolean movePinionEnabled;

  public SpiroModel()
  {
    super();
    spurModel = new SpurModel(SpiroConstruct.SPIRO_WINDOW_CENTER,SpiroConstruct.SPUR_RADIUS);
    pinionModel = new PinionModel(SpiroConstruct.PINION_CENTER,SpiroConstruct.PINION_RADIUS);
    moveSpurEnabled = false;
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
    isStop = aBool;
    return;
  }

  public Boolean isStop()
  {
    return isStop;
  }

  public void updateTapArea(Point aPoint)
  {
    for(Integer index = 0; index < spurModel.tapAreaCoodinateList().size(); index++)
    {
      Point2D.Double coodinate = spurModel.tapAreaCoodinateList().get(index);
      double x = coodinate.x - aPoint.x;
      double y = coodinate.y - aPoint.y;
      if(x*x + y*y <= SpiroConstruct.TAP_AREA_RADIUS*2*SpiroConstruct.TAP_AREA_RADIUS*2)
      {
        moveSpurEnabled = true;
      }
    }
    return;
  }

  public void updateByRadian(double aRadian)
  {
    double distanceX = spurModel.centerCoodinate().x - pinionModel.centerCoodinate().x;
    double distanceY = spurModel.centerCoodinate().y - pinionModel.centerCoodinate().y ;
    double distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    pinionModel.spinManager(aRadian,spurModel.radius,distance);
    pinionModel.centerMoveManager(aRadian,distance,spurModel.centerCoodinate());
    pinionModel.pencilMoveManager(aRadian,spurModel.radius,distance);
    return;
  }

  public void draggedSpur(Point aPoint)
  {
    if(!isStop) return;

    if(moveSpurEnabled)
    {
      spurModel.updateByEvent(aPoint);
      pinionModel.updateByEvent(aPoint);
    }
    return;
  }

  public void resetEnabledValue()
  {
    moveSpurEnabled = false;
    movePinionEnabled = false;
  }
}
