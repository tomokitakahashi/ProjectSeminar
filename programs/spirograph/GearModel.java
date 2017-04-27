package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class GearModel extends Object
{
  private Point2D.Double centerCoodinate;

  private double radius;

  private ArrayList<Point2D.Double> tapAreaCoodinateList;

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
    Point2D.Double topAreaCoodinate = new Point2D.Double(centerCoodinate.x,centerCoodinate.y - radius - SpiroConstruct.TAP_AREA_RADIUS/2);
    tapAreaCoodinateList.add(topAreaCoodinate);
    Point2D.Double rightAreaCoodinate = new Point2D.Double(centerCoodinate.x + radius - SpiroConstruct.TAP_AREA_RADIUS/2,centerCoodinate.y);
    tapAreaCoodinateList.add(rightAreaCoodinate);
    Point2D.Double bottomAreaCoodinate = new Point2D.Double(centerCoodinate.x,centerCoodinate.y + radius - SpiroConstruct.TAP_AREA_RADIUS/2);
    tapAreaCoodinateList.add(bottomAreaCoodinate);
    Point2D.Double leftAreaCoodinate = new Point2D.Double(centerCoodinate.x - radius - SpiroConstruct.TAP_AREA_RADIUS/2,centerCoodinate.y);
    tapAreaCoodinateList.add(leftAreaCoodinate);
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
