package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

// スピログラフの描かれた軌跡のモデル
public class SpiroLocusModel extends Object
{
  private ArrayList<Point2D.Double> locusList;

  public SpiroLocusModel()
  {
    locusList = new ArrayList<Point2D.Double>();
  }

  public ArrayList<Point2D.Double> locusList()
  {
    return locusList;
  }
  
}
