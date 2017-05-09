package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.awt.Point;

public class PinionModel extends GearModel
{
  // 鉛筆の点の座標を格納しておくプロパティ
  public Point2D.Double pencilCoodinate;

  private double pencilRadian;

  private double pencilDistance;

  private Boolean pencilMoveEnabled;

  private double pinionTheta;

  private double increaseRate;
  // コンストラクタ
  public PinionModel(Point2D.Double aCenterCoodinate, double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    pinionTheta = 0.0;
    pencilCoodinate = SpiroConstruct.PENCIL_CENTER;
    pencilMoveEnabled = false;
    this.dataReset();
    return;
  }

  //各プロパティのリセッター
  //リスタートなどの際に再セットする
  @Override
  public void dataReset()
  {
    Point2D.Double axisPoint = tapAreaCoodinateList.get(1);
    double axisRadian = Math.atan2(centerCoodinate.y-axisPoint.y,centerCoodinate.x-axisPoint.x);
    pencilRadian = Math.atan2(pencilCoodinate.y - centerCoodinate.y,pencilCoodinate.x - centerCoodinate.x);
    pencilRadian -= (axisRadian + Math.toRadians(180));
    double distanceX = centerCoodinate.x - pencilCoodinate.x;
    double distanceY = centerCoodinate.y - pencilCoodinate.y;
    pencilDistance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    return;
  }
  public void restart(double aGearDistance)
  {
    increaseRate = -aGearDistance /  radius * Math.toRadians(0.1);
    return;
  }

  // アニメーション全体を制御するメソッド
  public void animationManager(double aRadian,double aSpurRadius,double aGearDistance)
  {
    pinionTheta += increaseRate;
    this.centerMoveManager(aRadian,aGearDistance);
    this.spinManager(aRadian,aGearDistance);
    this.pencilMoveManager(aRadian,aGearDistance);
    return;
  }

  // ピニオンギアの中心アニメーションを制御するメソッド
  private void centerMoveManager(double aRadian,double aGearDistance)
  {
    centerCoodinate.x = Math.cos(aRadian) * aGearDistance + SpiroConstruct.SPIRO_WINDOW_CENTER.x;
    centerCoodinate.y = Math.sin(aRadian) * aGearDistance + SpiroConstruct.SPIRO_WINDOW_CENTER.y;
    return;
  }

  // ピニオンギアの回転を制御するメソッド
  private void spinManager(double aRadian,double aGearDistance)
  {
    double addRadian = Math.toRadians(90);
    for(Integer index = 0; index < tapAreaCoodinateList.size();index++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      coodinate.x = Math.cos(pinionTheta+(addRadian * (index-1))) * radius + centerCoodinate.x;
      coodinate.y = Math.sin(pinionTheta+(addRadian * (index-1))) * radius + centerCoodinate.y;
    }
    return;
  }

  // 鉛筆のアニメーションを制御するメソッド
  private void pencilMoveManager(double aRadian,double aGearDistance)
  {
    pencilCoodinate.x = Math.cos(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.x;
    pencilCoodinate.y = Math.sin(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.y;
    return;
  }

  // 鉛筆を描写する座標を応答する
  public Point2D.Double drawPencilCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(pencilCoodinate.x - SpiroConstruct.PENCIL_RADIUS,pencilCoodinate.y - SpiroConstruct.PENCIL_RADIUS);
    return coodinate;
  }

  // マウスイベントの有効箇所を判断するメソッド
  @Override
  public void judgePressArea(Point aPoint)
  {
    double tapRange = SpiroConstruct.TAP_AREA_RADIUS*2*SpiroConstruct.TAP_AREA_RADIUS*2;
    double centerTapPoint = (centerCoodinate.x - aPoint.x) * (centerCoodinate.x - aPoint.x) + (centerCoodinate.y - aPoint.y) * (centerCoodinate.y - aPoint.y);
    double pencilTapPoint = (pencilCoodinate.x - aPoint.x) * (pencilCoodinate.x - aPoint.x) + (pencilCoodinate.y - aPoint.y) * (pencilCoodinate.y - aPoint.y);
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
    if(centerTapPoint <= tapRange)
    {
      centerMoveEnabled = true;
    }
    if(pencilTapPoint <= tapRange)
    {
      pencilMoveEnabled = true;
    }
    return;
  }

  //マウスリリースの際の処理
  @Override
  public void updateByRelease(Point aPoint)
  {
    this.dataReset();
    centerMoveEnabled = false;
    radiusAbjustEnabled = false;
    pencilMoveEnabled = false;
    return;
  }

  // マウスドラッグの際の処理
  @Override
  public void updateByDrag(Point aPoint)
  {
    if(radiusAbjustEnabled)
    {
      this.updateRadiusByDrag(aPoint);
    }
    else if (centerMoveEnabled)
    {
      this.updateCenterByDrag(aPoint);
    }
    else if (pencilMoveEnabled)
    {
      this.updatePencilCenterByDrag(aPoint);
    }
    this.updateTapArea();
    return;
  }

  private void updatePencilCenterByDrag(Point aPoint)
  {
    double distanceX = centerCoodinate.x - aPoint.x;
    double distanceY = centerCoodinate.y - aPoint.y;
    double distance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
    if(distance <= radius)
    {
      pencilCoodinate.x = aPoint.x;
      pencilCoodinate.y = aPoint.y;
    }
    return;
  }

  public void updateRelative(double aRadian,Point2D.Double pointCoodinate,double aGearDistance)
  {
    if(!centerMoveEnabled)
    {
      centerCoodinate.x = Math.cos(aRadian+Math.toRadians(180)) * radius + pointCoodinate.x;
      centerCoodinate.y = Math.sin(aRadian+Math.toRadians(180)) * radius + pointCoodinate.y;
    }
    if(!pencilMoveEnabled)
    {
      pencilCoodinate.x = Math.cos(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.x;
      pencilCoodinate.y = Math.sin(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.y;
    }
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
