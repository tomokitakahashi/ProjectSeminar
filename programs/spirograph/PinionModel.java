package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.awt.Point;

public class PinionModel extends GearModel
{
  // 鉛筆の点の座標を格納しておくプロパティ
  private Point2D.Double pencilCoodinate;

  private double pencilRadian;

  private double pencilDistance;

  private double pinionTheta;

  private double previousTheta;

  private double increaseRate;
  // コンストラクタ
  public PinionModel(Point2D.Double aCenterCoodinate, double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    pinionTheta = 0.0;
    previousTheta = 0.0;
    pencilCoodinate = SpiroConstruct.PENCIL_CENTER;
    this.dataReset();
    return;
  }

  @Override
  public void dataReset()
  {
    pencilRadian = Math.atan2(pencilCoodinate.y - centerCoodinate.y,pencilCoodinate.x - centerCoodinate.x);
    double distanceX = centerCoodinate.x - pencilCoodinate.x;
    double distanceY = centerCoodinate.y - pencilCoodinate.y;
    pencilDistance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    previousTheta = pinionTheta;
    return;
  }
  public void restart(double aGearDistance)
  {
     increaseRate = (-aGearDistance /  radius * Math.toRadians(0.1)) - (-aGearDistance /  radius * 0);
     return;
  }
  public void animationManager(double aRadian,double aSpurRadius,double aGearDistance)
  {
    pinionTheta += increaseRate;
    this.centerMoveManager(aRadian,aGearDistance);
    this.spinManager(aRadian,aGearDistance);
    this.pencilMoveManager(aRadian,aGearDistance);
    System.out.println("animation " + pinionTheta);
    return;
  }

  private void centerMoveManager(double aRadian,double aGearDistance)
  {
    centerCoodinate.x = Math.cos(aRadian) * aGearDistance + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    centerCoodinate.y = Math.sin(aRadian) * aGearDistance + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    return;
  }

  private void spinManager(double aRadian,double aGearDistance)
  {
    double addRadian = Math.toRadians(90);
    for(Integer index = 0; index < tapAreaCoodinateList.size();index++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      coodinate.x = Math.cos(pinionTheta+(addRadian * (index-1))) * radius + centerCoodinate.x;
      coodinate.y = Math.sin(pinionTheta+(addRadian * (index-1))) * radius + centerCoodinate.y;
      // coodinate.x = radius*Math.cos(pinionTheta+(addRadian * (index-1))) + aGearDistance*Math.cos(aRadian) + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
      // coodinate.y = radius*Math.sin(pinionTheta+(addRadian * (index-1))) + aGearDistance*Math.sin(aRadian) + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    }
    return;
  }

  private void pencilMoveManager(double aRadian,double aGearDistance)
  {
    pencilCoodinate.x = Math.cos(pinionTheta) * pencilDistance + centerCoodinate.x;
    pencilCoodinate.y = Math.sin(pinionTheta) * pencilDistance + centerCoodinate.y;
    return;
  }

  public Point2D.Double drawPencilCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(pencilCoodinate.x - SpiroConstruct.PENCIL_RADIUS,pencilCoodinate.y - SpiroConstruct.PENCIL_RADIUS);
    return coodinate;
  }

  public void updateRelative(double aRadian,Point2D.Double pointCoodinate,double aGearDistance)
  {
    System.out.println("relative  " + pinionTheta);
    //pinionTheta = -aGearDistance /  radius * aRadian;
    System.out.println("test");
    centerCoodinate.x = Math.cos(aRadian+Math.toRadians(180)) * radius + pointCoodinate.x;
    centerCoodinate.y = Math.sin(aRadian+Math.toRadians(180)) * radius + pointCoodinate.y;
    pencilCoodinate.x = Math.cos(pinionTheta) * pencilDistance + centerCoodinate.x;
    pencilCoodinate.y = Math.sin(pinionTheta) * pencilDistance + centerCoodinate.y;
    double addRadian = Math.toRadians(90);
    for(Integer index = 0; index < tapAreaCoodinateList.size();index++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      coodinate.x = Math.cos(pinionTheta+(addRadian * (index-1))) * radius + centerCoodinate.x;
      coodinate.y = Math.sin(pinionTheta+(addRadian * (index-1))) * radius + centerCoodinate.y;
    }
    return;
  }

}
