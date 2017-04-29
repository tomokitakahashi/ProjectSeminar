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

  // コンストラクタ
  public PinionModel(Point2D.Double aCenterCoodinate, double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    pencilCoodinate = SpiroConstruct.PENCIL_CENTER;
    return;
  }

  public void dataReset()
  {
    pencilRadian = Math.atan2(pencilCoodinate.y - centerCoodinate.y,pencilCoodinate.x - centerCoodinate.x);
    double distanceX = centerCoodinate.x - pencilCoodinate.x;
    double distanceY = centerCoodinate.y - pencilCoodinate.y;
    pencilDistance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    return;
  }

  public void animationManager(double aRadian,double aSpurRadius,double aGearDistance)
  {
    double spinRate = (aSpurRadius-radius) / (radius*2);
    double pinionTheta = (radius-aGearDistance)/radius * (aRadian * spinRate);
    this.centerMoveManager(aRadian,aGearDistance);
    this.spinManager(aRadian,pinionTheta,aGearDistance);
    this.pencilMoveManager(aRadian,pinionTheta,aGearDistance);
    return;
  }

  private void centerMoveManager(double aRadian,double aGearDistance)
  {
    centerCoodinate.x = Math.cos(aRadian) * aGearDistance + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    centerCoodinate.y = Math.sin(aRadian) * aGearDistance + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    return;
  }

  private void spinManager(double aRadian,double aPinionTheta,double aGearDistance)
  {
    double addRadian = Math.toRadians(90);
    for(Integer index = 0; index < tapAreaCoodinateList.size();index++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      coodinate.x = radius*Math.cos(aPinionTheta+(addRadian * (index-1))) + aGearDistance*Math.cos(aRadian) + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
      coodinate.y = radius*Math.sin(aPinionTheta+(addRadian * (index-1))) + aGearDistance*Math.sin(aRadian) + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    }
    return;
  }

  private void pencilMoveManager(double aRadian,double aPinionTheta,double aGearDistance)
  {
    pencilCoodinate.x = Math.cos(aPinionTheta + pencilRadian) * pencilDistance + centerCoodinate.x;
    pencilCoodinate.y = Math.sin(aPinionTheta + pencilRadian) * pencilDistance + centerCoodinate.y;
    return;
  }

  public Point2D.Double drawPencilCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(SpiroConstruct.PENCIL_CENTER.x - SpiroConstruct.PENCIL_RADIUS,SpiroConstruct.PENCIL_CENTER.y - SpiroConstruct.PENCIL_RADIUS);
    return coodinate;
  }

  @Override
  public void updateCenterByPick(Point aPoint)
  {

  }

}
