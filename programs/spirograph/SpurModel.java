package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

public class SpurModel extends GearModel
{
  public SpurModel(Point2D.Double aCenterCoodinate,double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    return;
  }

  public void updateCenterByDrag(Point aPoint)
  {

    if(10 < aPoint.x && centerCoodinate.x < SpiroConstruct.SPIRO_WINDOW.width - 10)
    {
      centerCoodinate.x = aPoint.x;
    }
    if(30 < aPoint.y && centerCoodinate.y < SpiroConstruct.SPIRO_WINDOW.height - 10)
    {
      centerCoodinate.y = aPoint.y;
    }
    return;
  }

}
