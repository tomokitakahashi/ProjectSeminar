package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

public class SpiroModel extends Model
{
  // スピログラフで描かれた軌跡モデル
  public SpiroLocusModel spiroLocusModel;

  // スピログラフのスパーギアモデル
  public SpurModel spurModel;

  // スピログラフのピニオンモデル
  public PinionModel pinionModel;

  private Boolean moveSpurEnabled;

  private Boolean movePinionEnabled = false;

  public SpiroModel()
  {
    super();
    Point2D.Double aCenterCoodinate = new Point2D.Double(SpiroConstruct.SPIRO_WINDOW_CENTER.x,SpiroConstruct.SPIRO_WINDOW_CENTER.y);
    double aRadius = 250.0;
    spurModel = new SpurModel(aCenterCoodinate,aRadius);
    moveSpurEnabled = false;
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
    if(moveSpurEnabled)
    {
      spurModel.updateByEvent(aPoint);
    }
    return;
  }

  public void resetEnabledValue()
  {
    moveSpurEnabled = false;
    movePinionEnabled = false;
  }
}
