package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

public class SpiroModel extends Model
{
  public Boolean isStop;
  // スピログラフで描かれた軌跡モデル
  public SpiroLocusModel spiroLocusModel;

  // スピログラフのスパーギアモデル
  public SpurModel spurModel;

  // スピログラフのピニオンモデル
  public PinionModel pinionModel;

  private Boolean moveSpurEnabled;

  private Boolean movePinionEnabled;

  public SpiroModel()
  {
    super();
    double aRadius = 250.0;
    Point2D.Double spurCenterCoodinate = new Point2D.Double(SpiroConstruct.SPIRO_WINDOW_CENTER.x,SpiroConstruct.SPIRO_WINDOW_CENTER.y);
    Point2D.Double pinionCenterCoodinate = new Point2D.Double(SpiroConstruct.SPIRO_WINDOW_CENTER.x + 2 * aRadius / 3,SpiroConstruct.SPIRO_WINDOW_CENTER.y);
    spurModel = new SpurModel(spurCenterCoodinate,aRadius);
    pinionModel = new PinionModel(pinionCenterCoodinate,aRadius/3);
    moveSpurEnabled = false;
    isStop = true;
    return;
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

  public void draggedSpur(Point aPoint)
  {
    if(!isStop) { return; }

    if(moveSpurEnabled)
    {
      //System.out.println(pinionRatio);
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
