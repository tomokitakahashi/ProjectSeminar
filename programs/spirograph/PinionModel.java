package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class PinionModel extends GearModel
{
  // 鉛筆の点の座標を格納しておくプロパティ
  private Point2D.Double pencilCoodinate;

  // コンストラクタ
  public PinionModel(Point2D.Double aCenterCoodinate, double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    pencilCoodinate = SpiroConstruct.PENCIL_CENTER;
    return;
  }

  public void centerMoveManager(double radian,double distance,Point2D.Double aSpurCenterCoodinate)
  {
    centerCoodinate.x = Math.cos(radian) * distance + aSpurCenterCoodinate.x;
    centerCoodinate.y = Math.sin(radian) * distance + aSpurCenterCoodinate.y;
    //this.pencilMoveManager(radian,distance);
    return;
  }

  public void spinManager(double radian, double distance)
  {
    double addRadian = Math.toRadians(90.0);
    System.out.println(addRadian);
    for(Integer index = 0; index < tapAreaCoodinateList.size();index++)
    {
      double pinionTheta = (radius-distance)/radius * radian + addRadian * index;
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      coodinate.x = radius*Math.cos(pinionTheta) + distance*Math.cos(radian) + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
      coodinate.y = radius*Math.sin(pinionTheta) + distance*Math.sin(radian) + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    }
    return;
  }

  public void pencilMoveManager(double radian,double spurRadius,double distance)
  {
    double pinionTheta = (radius-distance)/radius * radian;
    pencilCoodinate.x = (spurRadius - radius) * Math.cos(pinionTheta) - SpiroConstruct.PENCIL_RADIUS * Math.cos(((spurRadius - radius)/SpiroConstruct.PENCIL_RADIUS)*pinionTheta) + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    pencilCoodinate.y = (spurRadius - radius) * Math.sin(pinionTheta) - SpiroConstruct.PENCIL_RADIUS * Math.sin(((spurRadius - radius)/SpiroConstruct.PENCIL_RADIUS)*pinionTheta) + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    return;
  }

  public Point2D.Double drawPencilCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(SpiroConstruct.PENCIL_CENTER.x - SpiroConstruct.PENCIL_RADIUS,SpiroConstruct.PENCIL_CENTER.y - SpiroConstruct.PENCIL_RADIUS);
    return coodinate;
  }

  public Point2D.Double pencilCoodinate()
  {
    return pencilCoodinate;
  }

  public void pencilCoodinate(Point2D.Double aPencilCoodinate)
  {
    pencilCoodinate = aPencilCoodinate;
    return;
  }

}
