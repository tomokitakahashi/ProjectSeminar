package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.awt.Point;

/**
* ピニオンギアのモデル
*/
public class PinionModel extends GearModel
{
  /**
  * 鉛筆の中心座標
  * 良好（2017年5月20日）
  **/
  private Point2D.Double pencilCoodinate;

  /**
  * 鉛筆の角度 (鉛筆の回転角)
  * 良好（2017年5月20日）
  */
  private double pencilRadian;

  /**
  * 以前の鉛筆の距離
  * 良好（2017年5月20日）
  */
  private double previousPencilDistance;

  /**
  * 中心座標と鉛筆の距離
  * 良好（2017年5月20日）
  */
  private double pencilDistance;

  /**
  * 鉛筆が移動できるかどうかをプレス時に格納する
  * 良好（2017年5月20日）
  */
  private Boolean pencilMoveEnabled;

  /**
  * ピニオンギアの角度 (回転角度)
  * 良好（2017年5月20日）
  */
  private double pinionTheta;

  /**
  * ピニオンギアの回転する際のradianの回転率を格納しておく。
  * MEMO: 拡大拡小によって回転率が異なるため
  * 良好（2017年5月20日）
  */
  private double changeRate;

  /**
  * ピニオンギアの回転する向きを格納しておく(内接・外接)
  * MEMO: 反時計周り -> 1 or 時計周り -> -1
  * 良好（2017年5月20日）
  */
  private double spinDirection;

  /**
  * PinionModelのコンストラクタ
  * 良好（2017年5月20日）
  * @param aCenterCoodinate 初期中心座標
  * @param aRadius 初期半径
  */
  public PinionModel(Point2D.Double aCenterCoodinate, double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    pinionTheta = 0.0;
    pencilCoodinate = SpiroConstruct.PENCIL_CENTER;
    pencilMoveEnabled = false;
    spinDirection = 1;
    this.dataReset();
    return;
  }

  /**
  * 外接と内接の切り替えを行うメソッド
  * 良好（2017年5月20日）
  * @param aRadian スピログラフの軸線のラジアン
  * @param aGearDistance ギア同士の距離
  */
  public void changeCenterPosition(double aRadian, double aGearDistance,Point2D.Double spurCenterCoodinate)
  {
    this.updateCurrentCenter(aRadian,aGearDistance+spinDirection*radius*2,spurCenterCoodinate);
    this.updateCurrentPencil();
    this.updateCurrentTapArea();
    spinDirection *= -1;
    return;
  }

  /**
  * 各プロパティのリセッター
  * リスタートなどの際に再セットする
  * 良好（2017年5月20日）
  */
  @Override
  public void dataReset()
  {
    double distanceX = centerCoodinate.x - pencilCoodinate.x;
    double distanceY = centerCoodinate.y - pencilCoodinate.y;
    pencilDistance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    previousPencilDistance = pencilDistance;
    return;
  }

  /**
  * リスタートした時に鉛筆の増加率または減少率を再格納するメソッド
  * MEMO: 半径が変わった際に回転率が異なるため。
  * 良好（2017年5月20日）
  */
  public void restart(double aGearDistance)
  {
    changeRate = -aGearDistance /  radius * Math.toRadians(0.1);
    return;
  }

  /**
  * アニメーションするためにアップデートするメソッド
  * 良好（2017年5月20日）
  * @param aRadian スピログラフの軸線のラジアン
  * @param aGearDistance ギア同士の距離
  */
  public void animationManager(double aRadian,double aGearDistance,Point2D.Double spiroCenterCoodinate)
  {
    System.out.println(aRadian);
    pinionTheta += (changeRate * spinDirection);
    this.updateCurrentCenter(aRadian,aGearDistance,spiroCenterCoodinate);
    this.updateCurrentTapArea();
    this.updateCurrentPencil();
    return;
  }

  /**
  * ピニオンギアの回転方向を設定する
  * 良好（2017年5月20日）
  */
  public void spinDirection(double aDirection)
  {
    spinDirection = aDirection;
    return;
  }

  /**
  * ピニオンギアの回転方向を応答する
  * 良好（2017年5月20日）
  */
  public double spinDirection()
  {
    return spinDirection;
  }

  /**
  * ピニオンギアの回転角を応答する
  * 良好（2017年5月20日）
  */
  public double pinionTheta()
  {
    return pinionTheta;
  }

  /**
  * ピニオンギアの回転角を設定する
  * 良好（2017年5月20日）
  * @param aTheta 新規値
  */
  public void pinionTheta(double aTheta)
  {
    pinionTheta = aTheta;
    return;
  }

  /**
  * 鉛筆の座標を応答する
  * 良好（2017年5月20日）
  */
  public Point2D.Double pencilCoodinate()
  {
    return pencilCoodinate;
  }

  /**
  * 鉛筆の座標を設定する
  * 良好（2017年5月20日）
  * @param newPoint 新規値
  */
  public void pencilCoodinate(Point2D.Double newPoint)
  {
    pencilCoodinate = newPoint;
    return;
  }

  /**
  * 鉛筆の中心座標を元に描くべき座標に変換して返却する
  * 良好（2017年5月20日）
  * MEMO: drawOvalを利用しているため
  */
  public Point2D.Double drawPencilCoodinate()
  {
    Point2D.Double coodinate = new Point2D.Double(pencilCoodinate.x - SpiroConstruct.PENCIL_RADIUS,pencilCoodinate.y - SpiroConstruct.PENCIL_RADIUS);
    return coodinate;
  }

  /**
  * 鉛筆によって描かれた軌跡の座標をオブジェクト化して返却する
  * 良好（2017年5月20日）
  * MEMO: ArrayListに格納していくため
  */
  public Point2D.Double pencilLocusCoodinate()
  {
    return new Point2D.Double(pencilCoodinate.x,pencilCoodinate.y);
  }

  /**
  * マウスのプレスによってモデルがアップデート可能かどうかを判断するメソッド
  * 良好（2017年5月20日）
  * @param aPoint タップしたポイント
  */
  @Override
  public void judgePressArea(Point aPoint)
  {
    radiusAbjustEnabled = this.validateAroundTapArea(aPoint);
    pencilMoveEnabled = this.validatePencilTapArea(aPoint);
    return;
  }

  /**
  * タップした箇所が鉛筆上にあるかどうか判断するメソッド
  * 良好（2017年5月20日）
  * @param aPoint タップしたポイント
  */
  private Boolean validatePencilTapArea(Point aPoint)
  {
    double pencilTapPoint = (pencilCoodinate.x - aPoint.x) * (pencilCoodinate.x - aPoint.x) + (pencilCoodinate.y - aPoint.y) * (pencilCoodinate.y - aPoint.y);
    if(pencilTapPoint <= SpiroConstruct.TAP_AREA_RANGE) { return true; }
    return false;
  }

  /**
  * マウスリリースの際に各データをリセット(再設定)するメソッド
  * 良好（2017年5月20日）
  * @param aPoint マウスカーソル位置
  */
  @Override
  public void updateByRelease(Point aPoint)
  {
    this.dataReset();
    if(pencilMoveEnabled)
    {
      double axisPencilRadian = Math.atan2(tapAreaCoodinateList.get(1).y - centerCoodinate.y,tapAreaCoodinateList.get(1).x - centerCoodinate.x);
      pencilRadian = Math.atan2(pencilCoodinate.y - centerCoodinate.y,pencilCoodinate.x - centerCoodinate.x) - axisPencilRadian;
    }
    pencilMoveEnabled = false;
    return;
  }

  /**
  * マウスドラッグの際に処理をふり分けるメソッド
  * 良好（2017年5月20日）
  * @param aPoint マウスカーソル位置
  */
  @Override
  public void updateByDrag(Point aPoint)
  {
    //super.updateByDrag(aPoint);
    if(radiusAbjustEnabled)
    {
      this.updateRadiusByDrag(aPoint);
      pencilDistance = previousPencilDistance*radius / previousRadius;
    }
    if (pencilMoveEnabled)
    {
      this.updatePencilCenterByDrag(aPoint);
    }
    this.updateTapArea();
    return;
  }

  /**
  * 鉛筆をドラッグによって移動(座標アップデート)するメソッド
  * 良好（2017年5月20日）
  * @param aPoint マウスカーソルの位置
  */
  private void updatePencilCenterByDrag(Point aPoint)
  {
    System.out.println("updatePencilCenterByDrag");
    double distanceX = centerCoodinate.x - aPoint.x;
    double distanceY = centerCoodinate.y - aPoint.y;
    double distance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
    if(distance <= radius)
    {
      System.out.println("updatePencilCoodinate");
      pencilCoodinate.x = aPoint.x;
      pencilCoodinate.y = aPoint.y;
    }
    return;
  }

  /**
  * すべてのデータに基づいて最新のデータを更新するメソッド
  * 外部からアップデートの指示がされる場合に呼ばれる
  * 主に拡大拡小、中心の移動などイベントによる場合
  * 良好（2017年5月20日）
  * @param aRadian 基準となる角度
  * @param aPointCoodinate イベントによって定められた基準点
  */
  public void updateCurrent(double aRadian,Point2D.Double aPointCoodinate)
  {
    if(!centerMoveEnabled)
    {
      this.updateCurrentCenter(aRadian+Math.toRadians(180),radius*spinDirection,aPointCoodinate);
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
  * 良好（2017年5月20日）
  * @param aRadian 中心座標を定める角度
  * @param aDistance 距離 (ギア同士)
  * @param aStandardCoodinate 基準となる点 (JFrameでは左上が(0,0)のため)
  */
  private void updateCurrentCenter(double aRadian, double aDistance ,Point2D.Double aStandardCoodinate)
  {
    centerCoodinate.x = Math.cos(aRadian) * aDistance + aStandardCoodinate.x;
    centerCoodinate.y = Math.sin(aRadian) * aDistance + aStandardCoodinate.y;
    return;
  }

  /**
  * 最新の鉛筆の座標を更新するメソッド
  * 良好（2017年5月20日）
  */
  private void updateCurrentPencil()
  {
    pencilCoodinate.x = Math.cos(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.x;
    pencilCoodinate.y = Math.sin(pinionTheta + pencilRadian) * pencilDistance + centerCoodinate.y;
    return;
  }

  /**
  * 最新のタップエリアの座標を更新するメソッド
  * 良好（2017年5月20日）
  */
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
