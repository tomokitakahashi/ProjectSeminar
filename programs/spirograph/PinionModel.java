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

  public void spinManager(double radian,double spurRadius,double distance)
  {
    double spinRate = (spurRadius-radius) / (radius*2);
    //double pinionTheta = (radius-distance)/radius * radian + Math.toRadians(90);
    double addRadian = Math.toRadians(90);
    for(Integer index = 0; index < tapAreaCoodinateList.size();index++)
    {
      double pinionTheta = (radius-distance)/radius * (radian * spinRate) + addRadian * (index-1);
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      coodinate.x = radius*Math.cos(pinionTheta) + distance*Math.cos(radian) + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
      coodinate.y = radius*Math.sin(pinionTheta) + distance*Math.sin(radian) + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    }
    return;
  }

  public void pencilMoveManager(double radian,double spurRadius,double distance)
  {
    double testD = Math.sqrt((centerCoodinate.x - pencilCoodinate.x) * (centerCoodinate.x - pencilCoodinate.x) + (centerCoodinate.y - pencilCoodinate.y) * (centerCoodinate.y - pencilCoodinate.y));
    pencilCoodinate.x = (spurRadius - radius)*Math.cos(radian) + 30*Math.cos((spurRadius - radius)/radius*radian) + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    pencilCoodinate.y = (spurRadius - radius)*Math.sin(radian) - 30*Math.sin((spurRadius - radius)/radius*radian) + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
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
