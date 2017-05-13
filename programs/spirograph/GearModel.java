package spirograph;


import java.awt.Point;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

abstract public class GearModel extends Object
{
  // ギアの中心座標
  protected Point2D.Double centerCoodinate;

  // ギアの半径
  protected double radius;

  // アニメーションするために、前の半径を格納するプロパティ
  protected double previousRadius;

  // アニメーションするために、前の中心座標を格納しておくプロパティ
  protected Point2D.Double previousCenterCoodinate;

  // マウスイベントを取得する部分の座標を格納しておくArrayList
  protected ArrayList<Point2D.Double> tapAreaCoodinateList;

  // 半径の拡大拡小が可能かどうかを格納するプロパティ
  protected Boolean radiusAbjustEnabled;

  // 中心が移動できるかどうかを格納しておくプロパティ
  protected Boolean centerMoveEnabled;

  // リスタート時などにデータの再設定、リセットを行うメソッド
  abstract void dataReset();

  // マウスのイベントが有効エリアにあるかどうかを判別するメソッド
  abstract void judgePressArea(Point aPoint);

  /**
  * GearModelのコンストラクタ
  * @param aCenterCoodinate 初期中心座標
  * @param aRadius 初期半径
  **/
  public GearModel(Point2D.Double aCenterCoodinate,double aRadius)
  {
    super();
    centerCoodinate = aCenterCoodinate;
    previousCenterCoodinate = aCenterCoodinate;
    radius = aRadius;
    previousRadius = aRadius;
    radiusAbjustEnabled = false;
    centerMoveEnabled = false;
    this.initializeTapArea();
  }

  /**
  * タップした箇所が円の周りの4点上のいずれかに該当しているかどうかを判断するメソッド
  * @param aPoint タップしたポイント
  **/
  protected Boolean validateAroundTapArea(Point aPoint)
  {
    for(Integer index = 0; index < tapAreaCoodinateList.size(); index++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      double tapPoint = (coodinate.x - aPoint.x) * (coodinate.x - aPoint.x) + (coodinate.y - aPoint.y) * (coodinate.y - aPoint.y);
      if(tapPoint <= SpiroConstruct.TAP_AREA_RANGE) { return true; }
    }
    return false;
  }

  /**
  * タップした箇所が中心上であるかどうかを判断するメソッドe
  * @param aPoint タップしたポイント
  **/
  protected Boolean validateCenterTapArea(Point aPoint)
  {
    double centerTapPoint = (centerCoodinate.x - aPoint.x) * (centerCoodinate.x - aPoint.x) + (centerCoodinate.y - aPoint.y) * (centerCoodinate.y - aPoint.y);
    if(centerTapPoint <= SpiroConstruct.TAP_AREA_RANGE) { return true; }
    return false;
  }

  /**
  * 円周上のタップエリアの初期座標設定
  **/
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

  /**
  * タップエリアの描くべき座標を返却する
  * @param index インデックス
  **/
  public Point2D.Double drawTapAreaCoodinate(Integer index)
  {
    Point2D.Double tapAreaCoodinate = tapAreaCoodinateList.get(index);
    Point2D.Double drawCoodinate = new Point2D.Double(tapAreaCoodinate.x - SpiroConstruct.TAP_AREA_RADIUS,tapAreaCoodinate.y - SpiroConstruct.TAP_AREA_RADIUS);
    return drawCoodinate;
  }

  /**
  * タップエリアのリストを応答する
  **/
  public ArrayList<Point2D.Double> tapAreaCoodinateList()
  {
    return tapAreaCoodinateList;
  }

  /**
  ギアの半径を設定する
  @param aRadius 半径
  **/
  public void radius(double aRadius)
  {
    radius = aRadius;
    return;
  }

  /**
  * ギアの半径を応答する
  **/
  public double radius()
  {
    return radius;
  }

  /**
  * ギアの中心座標を設定する
  * @param aCenterCoodinate 中心座標
  **/
  public void centerCoodinate(Point2D.Double aCenterCoodinate)
  {
    centerCoodinate = aCenterCoodinate;
    return;
  }

  /**
  ギアの中心座標を応答する
  **/
  public Point2D.Double centerCoodinate()
  {
    return centerCoodinate;
  }

  /**
  * ドラッグイベントによってモデルをアップデートするメソッド
  * @param aPoint ドラッグ位置
  **/
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
    this.updateTapArea();
    return;
  }

  /**
  * マウスカーソルリリースエベントによってモデルをアップデートするメソッド
  * @param aPoint リリース位置
  **/
  public void updateByRelease(Point aPoint)
  {
    centerMoveEnabled = false;
    radiusAbjustEnabled = false;
    return;
  }

  /**
  * 拡大拡小イベントによって半径をアップデートするメソッド
  * @param aPoint マウス位置
  **/
  protected void updateRadiusByDrag(Point aPoint)
  {
    double x = centerCoodinate.x - aPoint.x;
    double y = centerCoodinate.y - aPoint.y;
    double newRadius = Math.sqrt(x*x+y*y);
    radius = newRadius;
    return;
  }

  /**
  * 中心のドラッグによって中心座標をアップデートする
  * @param aPoint マウス位置
  **/
  public void updateCenterByDrag(Point aPoint)
  {
    System.out.println("updateCenterByDrag");
    centerCoodinate.x = aPoint.x;
    centerCoodinate.y = aPoint.y;
    return;
  }

  public void updateTapArea()
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
        coodinate.y = centerCoodinate.y;
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
