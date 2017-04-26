package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class PinionModel extends GearModel
{
  // 初期表示時の縦線を格納しておくArrayList
  private ArrayList<Point2D.Double> verticalLineCoodinate;

  // 初期表示時の横線を格納しておくArrayList
  private ArrayList<Point2D.Double> horizontalLineCoodinate;

  // 鉛筆の点の座標を格納しておくプロパティ
  private Point2D.Double pencilCoodinate;

  // コンストラクタ
  public PinionModel()
  {
    super();
    return;
  }

  // 縦線の座標リストを応答する
  public ArrayList<Point2D.Double> verticalLineCoodinate()
  {
    return verticalLineCoodinate;
  }

  // 縦線の座標リストをセットする
  public void verticalLineCoodinate(ArrayList<Point2D.Double> aLineCoodinate)
  {
    verticalLineCoodinate = aLineCoodinate;
    return;
  }

  // 横線の座標リストを応答する
  public ArrayList<Point2D.Double> horizontalLineCoodinate()
  {
    return horizontalLineCoodinate;
  }

  // 横線の座標リストを応答する
  public void horizontalLineCoodinate(ArrayList<Point2D.Double> aLineCoodinate)
  {
    horizontalLineCoodinate = aLineCoodinate;
    return;
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
