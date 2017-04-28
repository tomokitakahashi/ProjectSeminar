package spirograph;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class GearModel extends Object
{
  protected Point2D.Double centerCoodinate;

  protected double radius;

  protected ArrayList<Point2D.Double> tapAreaCoodinateList;

  public GearModel(Point2D.Double aCenterCoodinate,double aRadius)
  {
    super();
    centerCoodinate = aCenterCoodinate;
    radius = aRadius;
    this.initializeTapArea();
  }

  private void initializeTapArea()
  {
    tapAreaCoodinateList = new ArrayList<Point2D.Double>();
    Point2D.Double topAreaCoodinate = new Point2D.Double(centerCoodinate.x - SpiroConstruct.TAP_AREA_RADIUS,centerCoodinate.y - radius - SpiroConstruct.TAP_AREA_RADIUS);
    tapAreaCoodinateList.add(topAreaCoodinate);
    Point2D.Double rightAreaCoodinate = new Point2D.Double(centerCoodinate.x + radius - SpiroConstruct.TAP_AREA_RADIUS,centerCoodinate.y - SpiroConstruct.TAP_AREA_RADIUS);
    tapAreaCoodinateList.add(rightAreaCoodinate);
    Point2D.Double bottomAreaCoodinate = new Point2D.Double(centerCoodinate.x - SpiroConstruct.TAP_AREA_RADIUS,centerCoodinate.y + radius - SpiroConstruct.TAP_AREA_RADIUS);
    tapAreaCoodinateList.add(bottomAreaCoodinate);
    Point2D.Double leftAreaCoodinate = new Point2D.Double(centerCoodinate.x - radius - SpiroConstruct.TAP_AREA_RADIUS,centerCoodinate.y - SpiroConstruct.TAP_AREA_RADIUS);
    tapAreaCoodinateList.add(leftAreaCoodinate);
    return;
  }

  public ArrayList<Point2D.Double> tapAreaCoodinateList()
  {
    return tapAreaCoodinateList;
  }

  // ギアの半径を設定する
  public void radius(double aRadius)
  {
    radius = aRadius;
    return;
  }

  // ギアの半径を応答する
  public double radius()
  {
    return radius;
  }

  // ギアの中心座標を設定する
  public void centerCoodinate(Point2D.Double aCenterCoodinate)
  {
    centerCoodinate = aCenterCoodinate;
    return;
  }

  // ギアの中心座標を応答する
  public Point2D.Double centerCoodinate()
  {
    return centerCoodinate;
  }

  public void updateByEvent(Point aPoint)
  {
    this.updateRadius(aPoint);
    this.updateTapArea();
  }

  private void updateRadius(Point aPoint)
  {
    double x = centerCoodinate.x - aPoint.x;
    double y = centerCoodinate.y - aPoint.y;
    double newRadius = Math.sqrt(x*x+y*y);
    this.radius(newRadius);
  }

  private void updateTapArea()
  {
    for(Integer index = 0; index < 4; index ++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      double x = centerCoodinate.x - (coodinate.x + SpiroConstruct.TAP_AREA_RADIUS);
      double y = centerCoodinate.y - (coodinate.y + SpiroConstruct.TAP_AREA_RADIUS);
      double radian = Math.atan2(x,y);
      coodinate.x = Math.cos(radian) * radius + centerCoodinate.x - SpiroConstruct.TAP_AREA_RADIUS;
      coodinate.y = Math.sin(radian) * radius + centerCoodinate.y - SpiroConstruct.TAP_AREA_RADIUS;
    }
  }

  public String toString()
  {
    StringBuffer aBuffer = new StringBuffer();
    Class aClass = this.getClass();
    aBuffer.append(aClass.getName());
    aBuffer.append("[centerCoodinate=");
    aBuffer.append(centerCoodinate);
    aBuffer.append(",radius=");
    aBuffer.append(radius);
    aBuffer.append("]");
    return aBuffer.toString();
  }
}
