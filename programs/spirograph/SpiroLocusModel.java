package spirograph;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

// スピログラフの描かれた軌跡のモデル
public class SpiroLocusModel extends Object
{
  // 鉛筆によって描かれた座標値を格納しておくリスト
  private ArrayList<Point2D.Double> locusList;

  private ArrayList<Color> locusColorList;

  public SpiroLocusModel()
  {
    locusList = new ArrayList<Point2D.Double>();
    locusColorList = new ArrayList<Color>();
  }

  // 軌跡の座標値リストを応答するメソッド
  public ArrayList<Point2D.Double> locusList()
  {
    return locusList;
  }

  // 色のリストを応答するメソッド
  public ArrayList<Color> locusColorList()
  {
    return locusColorList;
  }
}
