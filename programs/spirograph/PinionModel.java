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

  private Boolean changedPosition;

  private double pinionTheta;

  private double changeRate;

  // ピニオンギアの回転する際のradianの増加、減少かを決定する
  private double spinDirection;

  // コンストラクタ
  public PinionModel(Point2D.Double aCenterCoodinate, double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    pinionTheta = 0.0;
    pencilCoodinate = SpiroConstruct.PENCIL_CENTER;
    pencilMoveEnabled = false;
    changedPosition = false;
    this.dataReset();
    return;
  }

  public void changeCenterPosition(double axisDegree, double aDistance)
  {

    this.updateCurrentCenter(Math.toRadians(axisDegree),aDistance+radius*2,SpiroConstruct.SPIRO_WINDOW_CENTER);
    this.updateCurrentPencil();
    this.updateCurrentTapArea();
    changeRate *= -1;
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

  // リスタートした時に鉛筆の移動割合を再格納するメソッド
  public void restart(double aGearDistance)
  {
    changeRate = -aGearDistance /  radius * Math.toRadians(0.1);
    return;
  }

  // アニメーション全体を制御するメソッド
  public void animationManager(double aRadian,double aSpurRadius,double aGearDistance)
  {
    pinionTheta += changeRate;
    this.updateCurrentCenter(aRadian,aGearDistance,SpiroConstruct.SPIRO_WINDOW_CENTER);
    this.updateCurrentTapArea();
    this.updateCurrentPencil();
    return;
  }
  // 鉛筆を描写する座標を応答する
  public Point2D.Double drawPencilCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(pencilCoodinate.x - SpiroConstruct.PENCIL_RADIUS,pencilCoodinate.y - SpiroConstruct.PENCIL_RADIUS);
    return coodinate;
  }

  // 鉛筆によって描かれた軌跡の座標を再オブジェクト化して返す
  public Point2D.Double pencilLocusCoodinate()
  {
    return new Point2D.Double(pencilCoodinate.x,pencilCoodinate.y);
  }

  // マウスイベントの有効箇所を判断するメソッド
  @Override
  public void judgePressArea(Point aPoint)
  {
    previousCenterCoodinate = centerCoodinate;
    double tapRange = SpiroConstruct.TAP_AREA_RADIUS*2*SpiroConstruct.TAP_AREA_RADIUS*2;
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
    else if (pencilMoveEnabled)
    {
      this.updatePencilCenterByDrag(aPoint);
    }
    this.updateTapArea();
    return;
  }

  // 鉛筆のドラッグによって移動させるメソッド
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

  /**
  * すべてのデータに基づいて最新のデータを更新するメソッド
  * 外部からアップデートの指示がされる場合に呼ばれる
  * 主に拡大拡小、中心の移動などイベントによる場合
  * @param aRadian 基準となる角度
  * @param aPointCoodinate イベントによって定められた基準点
  **/
  public void updateCurrent(double aRadian,Point2D.Double aPointCoodinate)
  {
    if(!centerMoveEnabled)
    {
      this.updateCurrentCenter(aRadian+Math.toRadians(180),radius,aPointCoodinate);
    }
    if(!pencilMoveEnabled)
    {
      this.updateCurrentPencil();
    }
    this.updateCurrentTapArea();
    return;
  }

  /**
  * 最新の中心座標を更新するメソッド (角度と距離より)
  * @param aRadian 中心座標を定める角度
  * @param aDistance 距離 (ギア同士)
  * @param aStandardCoodinate 基準となる点 (JFrameでは左上が(0,0)のため)
  **/
  private void updateCurrentCenter(double aRadian, double aDistance ,Point2D.Double aStandardCoodinate)
  {
    centerCoodinate.x = Math.cos(aRadian) * aDistance + aStandardCoodinate.x;
    centerCoodinate.y = Math.sin(aRadian) * aDistance + aStandardCoodinate.y;
    return;
  }

  /**
  * 最新の鉛筆の座標を更新するメソッド
  **/
  private void updateCurrentPencil()
  {
    pencilCoodinate.x = Math.cos(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.x;
    pencilCoodinate.y = Math.sin(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.y;
    return;
  }

  /**
  * 最新のタップエリアの座標を更新するメソッド
  **/
  private void updateCurrentTapArea()
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

}
