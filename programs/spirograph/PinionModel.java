package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.awt.Point;

public class PinionModel extends GearModel
{
  /**
  * 鉛筆の中心座標
  **/
  public Point2D.Double pencilCoodinate;

  /**
  * 鉛筆の角度 (鉛筆の回転角)
  **/
  private double pencilRadian;

  /**
  * 中心座標と鉛筆の距離
  **/
  private double pencilDistance;

  /**
  * 鉛筆が移動できるかどうかをプレス時に格納する
  **/
  private Boolean pencilMoveEnabled;

  /**
  * ピニオンギアの角度 (回転角度)
  **/
  private double pinionTheta;

  /**
  * ピニオンギアの回転する際のradianの回転率を格納しておく。
  * MEMO: 拡大拡小によって回転率が異なるため
  **/
  private double changeRate;

  /**
  * ピニオンギアの回転する向きを格納しておく(内接・外接)
  * MEMO: 反時計周り -> 1 or 時計周り -> -1
  **/
  private double spinDirection;

  /**
  * PinionModelのコンストラクタ
  * @param aCenterCoodinate 初期中心座標
  * @param aRadius 初期半径
  **/
  public PinionModel(Point2D.Double aCenterCoodinate, double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    pinionTheta = 0.0;
    pencilCoodinate = SpiroConstruct.PENCIL_CENTER;
    pencilMoveEnabled = false;
    this.dataReset();
    return;
  }

  /**
  * 外接と内接の切り替えを行うメソッド
  * @param aRadian スピログラフの軸線のラジアン
  * @param aGearDistance ギア同士の距離
  **/
  public void changeCenterPosition(double aRadian, double aGearDistance)
  {
    this.updateCurrentCenter(aRadian,aGearDistance+radius*2,SpiroConstruct.SPIRO_WINDOW_CENTER);
    this.updateCurrentPencil();
    this.updateCurrentTapArea();
    spinDirection *= -1;
    return;
  }

  /**
  * 各プロパティのリセッター
  * リスタートなどの際に再セットする
  * TODO: 要リファクタリング
  **/
  @Override
  public void dataReset()
  {
    Point2D.Double axisPoint = tapAreaCoodinateList.get(1);
    double axisRadian = Math.atan2(centerCoodinate.y-axisPoint.y,centerCoodinate.x-axisPoint.x);
    pencilRadian -= (axisRadian + Math.toRadians(180));
    double distanceX = centerCoodinate.x - pencilCoodinate.x;
    double distanceY = centerCoodinate.y - pencilCoodinate.y;
    pencilDistance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    return;
  }

  /**
  * リスタートした時に鉛筆の増加率または減少率を再格納するメソッド
  * MEMO: 半径が変わった際に回転率が異なるため。
  * TODO: 要リファクタリング
  **/
  public void restart(double aGearDistance)
  {
    changeRate = -aGearDistance /  radius * Math.toRadians(0.1);
    return;
  }

  /**
  * アニメーションするためにアップデートするメソッド
  * @param aRadian スピログラフの軸線のラジアン
  * @param aGearDistance ギア同士の距離
  **/
  public void animationManager(double aRadian,double aGearDistance)
  {
    pinionTheta += (changeRate * spinDirection);
    this.updateCurrentCenter(aRadian,aGearDistance,SpiroConstruct.SPIRO_WINDOW_CENTER);
    this.updateCurrentTapArea();
    this.updateCurrentPencil();
    return;
  }
  /**
  * 鉛筆の中心座標を元に描くべき座標に変換して返却する
  * MEMO: drawOvalを利用しているため
  **/
  public Point2D.Double drawPencilCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(pencilCoodinate.x - SpiroConstruct.PENCIL_RADIUS,pencilCoodinate.y - SpiroConstruct.PENCIL_RADIUS);
    return coodinate;
  }

  /**
  * 鉛筆によって描かれた軌跡の座標をオブジェクト化して返却する
  * MEMO: ArrayListに格納していくため
  **/
  public Point2D.Double pencilLocusCoodinate()
  {
    return new Point2D.Double(pencilCoodinate.x,pencilCoodinate.y);
  }

  /**
  * マウスのプレスによってモデルがアップデート可能かどうかを判断するメソッド
  **/
  @Override
  public void judgePressArea(Point aPoint)
  {
    previousCenterCoodinate = centerCoodinate;
    radiusAbjustEnabled = this.validateAroundTapArea(aPoint);
    pencilMoveEnabled = this.validatePencilTapArea(aPoint);
    return;
  }

  /**
  * タップした箇所が鉛筆上にあるかどうか判断するメソッド
  * @param aPoint タップしたポイント
  **/
  private Boolean validatePencilTapArea(Point aPoint)
  {
    double pencilTapPoint = (pencilCoodinate.x - aPoint.x) * (pencilCoodinate.x - aPoint.x) + (pencilCoodinate.y - aPoint.y) * (pencilCoodinate.y - aPoint.y);
    if(pencilTapPoint <= SpiroConstruct.TAP_AREA_RANGE) { return true; }
    return false;
  }

  /**
  * マウスリリースの際に各データをリセット(再設定)するメソッド
  * @param aPoint マウスカーソル位置
  **/
  @Override
  public void updateByRelease(Point aPoint)
  {
    this.dataReset();
    centerMoveEnabled = false;
    radiusAbjustEnabled = false;
    pencilMoveEnabled = false;
    return;
  }

  /**
  * マウスドラッグの際に処理をふり分けるメソッド
  * @param aPoint マウスカーソル位置
  **/
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

  /**
  * 鉛筆をドラッグによって移動(座標アップデート)するメソッド
  * @param aPoint マウスカーソルの位置
  **/
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
