package spirograph;


import java.awt.Point;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

abstract public class GearModel extends Object
{
  protected Point2D.Double centerCoodinate;

  protected double radius;

  protected double previousRadius;

  protected Point2D.Double previousCenterCoodinate;

  protected ArrayList<Point2D.Double> tapAreaCoodinateList;

  private Boolean radiusAbjustEnabled;

  private Boolean centerMoveEnabled;

  abstract void updateCenterByPick(Point aPoint);

  abstract void updateRadiusByDrag(double aRadius);

  public GearModel(Point2D.Double aCenterCoodinate,double aRadius)
  {
    super();
    centerCoodinate = aCenterCoodinate;
    previousCenterCoodinate = aCenterCoodinate;
    radius = aRadius;
    previousRadius = aRadius;
    radiusAbjustEnabled = false;
    this.initializeTapArea();
  }

  private void initializeTapArea()
  {
    tapAreaCoodinateList = new ArrayList<Point2D.Double>();
    Point2D.Double topAreaCoodinate = new Point2D.Double(centerCoodinate.x,centerCoodinate.y - radius);
    tapAreaCoodinateList.add(topAreaCoodinate);
    Point2D.Double rightAreaCoodinate = new Point2D.Double(centerCoodinate.x + radius,centerCoodinate.y);
    tapAreaCoodinateList.add(rightAreaCoodinate);
    Point2D.Double bottomAreaCoodinate = new Point2D.Double(centerCoodinate.x,centerCoodinate.y + radius);
    tapAreaCoodinateList.add(bottomAreaCoodinate);
    Point2D.Double leftAreaCoodinate = new Point2D.Double(centerCoodinate.x - radius,centerCoodinate.y);
    tapAreaCoodinateList.add(leftAreaCoodinate);
    return;
  }
  public Point2D.Double drawTapAreaCoodinate(Integer index)
  {
    Point2D.Double tapAreaCoodinate = tapAreaCoodinateList.get(index);
    Point2D.Double drawCoodinate = new Point2D.Double(tapAreaCoodinate.x - SpiroConstruct.TAP_AREA_RADIUS,tapAreaCoodinate.y - SpiroConstruct.TAP_AREA_RADIUS);
    return drawCoodinate;
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
  public void judgePressArea(Point aPoint)
  {
    double tapRange = SpiroConstruct.TAP_AREA_RADIUS*2*SpiroConstruct.TAP_AREA_RADIUS*2;
    for(Integer index = 0; index < tapAreaCoodinateList.size(); index++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      double tapPoint = (coodinate.x - aPoint.x) * (coodinate.x - aPoint.x) + (coodinate.y - aPoint.y) * (coodinate.y - aPoint.y);
      if(tapPoint <= tapRange)
      {
        radiusAbjustEnabled = true;
        return;
      }
    }
    double centerTapPoint = (centerCoodinate.x - aPoint.x) * (centerCoodinate.x - aPoint.x) + (centerCoodinate.y - aPoint.y) * (centerCoodinate.y - aPoint.y);
    if(centerTapPoint <= tapRange)
    {
      centerMoveEnabled = true;
    }
    return;
  }

  public void updateByDrag(Point aPoint)
  {
    if(radiusAbjustEnabled)
    {
      //System.out.println(tappedAreaIndex);
      double x = centerCoodinate.x - aPoint.x;
      double y = centerCoodinate.y - aPoint.y;
      double newRadius = Math.sqrt(x*x+y*y);
      radius = newRadius;
    }
    this.updateTapArea();
    return;
  }

  public void updateByRelease(Point aPoint)
  {
    centerMoveEnabled = false;
    radiusAbjustEnabled = false;
    return;
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
    double newRadius = -radius;
    for(Integer index = 0; index < 4; index ++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      if(index % 2 == 0)
      {
        coodinate.x = centerCoodinate.x;
        coodinate.y = centerCoodinate.y + newRadius;
      }
      else
      {
        coodinate.x = centerCoodinate.x - newRadius;
        coodinate.y = coodinate.y;
      }
      if(index == 1) { newRadius *= -1; }
    }
  }

  public Point2D.Double drawGearCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(centerCoodinate.x - radius, centerCoodinate.y - radius);
    return coodinate;
  }

  public Dimension drawGearDimension()
  {
    Dimension dimension = new Dimension((int)radius * 2,(int)radius * 2);
    return dimension;
  }

  public Point2D.Double drawGearCenterCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(centerCoodinate.x - SpiroConstruct.TAP_AREA_RADIUS,centerCoodinate.y - SpiroConstruct.TAP_AREA_RADIUS);
    return coodinate;
  }

  public Dimension drawGearCenterDimension()
  {
    Dimension dimension = new Dimension(SpiroConstruct.TAP_AREA_RADIUS * 2,SpiroConstruct.TAP_AREA_RADIUS * 2);
    return dimension;
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
