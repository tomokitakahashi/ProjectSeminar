package spirograph;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

/**
* スピログラフの軌跡モデル
*/
public class SpiroLocusModel extends Object
{
  /**
  * 鉛筆によって描かれた座標値を格納しておくリスト
  * 良好（2017年5月20日）
  */
  private ArrayList<Point2D.Double> locusList;

  /**
  * 軌跡の色を格納しておくリスト
  * 良好（2017年5月20日）
  */
  private ArrayList<Color> locusColorList;

  /**
  * SpiroLocusModelのコンストラクタ
  * 良好（2017年5月20日）
  */
  public SpiroLocusModel()
  {
    locusList = new ArrayList<Point2D.Double>();
    locusColorList = new ArrayList<Color>();
  }

  /**
  * 軌跡の座標値リストを応答するメソッド
  * 良好（2017年5月20日）
  */
  public ArrayList<Point2D.Double> locusList()
  {
    return locusList;
  }

  /**
  * 色のリストを応答するメソッド
  * 良好（2017年5月20日）
  */
  public ArrayList<Color> locusColorList()
  {
    return locusColorList;
  }

  /**
  * 座標リストが空かどうかを応答する
  * 良好（2017年5月20日）
  */
  public Boolean isEmpty()
  {
    return locusList.size() == 0;
  }

  /**
  * 軌跡データ全てをクリアする
  * 良好（2017年5月20日）
  */
  public void clear()
  {
    locusList.clear();
    locusColorList.clear();
    return;
  }
}
