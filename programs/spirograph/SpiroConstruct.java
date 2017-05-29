package spirograph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;

public class SpiroConstruct {
  /**
  * メニューボタンのマージン
  * 良好（2017年5月20日）
  */
  public static final Integer MENU_BUTTON_MARGIN = 10;

  /**
  * スピログラフのウィンドウサイズ
  * 良好（2017年5月20日）
  */
  public static final Dimension SPIRO_WINDOW = new Dimension(750,600);

  /**
  * メニューのウィンドウサイズ
  * 良好（2017年5月20日）
  */
  public static final Dimension MENU_WINDOW = new Dimension(200,400);

  /**
  * ファイルチューザーのウィンドウサイズ
  * 良好（2017年5月20日）
  */
  public static final Dimension FILE_WINDOW = new Dimension(750,600);

  /**
  * スピログラフの初期中心座標
  * 良好（2017年5月20日）
  */
  public static final Point2D.Double SPIRO_WINDOW_CENTER = new Point2D.Double(375,300);

  /**
  * ピニオンギアの初期中心半径
  * 良好（2017年5月20日）
  */
  public static final Point2D.Double PINION_CENTER = new Point2D.Double(555,300);

  /**
  * 鉛筆の初期中心座標
  * 良好（2017年5月20日）
  */
  public static final Point2D.Double PENCIL_CENTER = new Point2D.Double(585,300);

  /**
  * タップエリアの半径
  * 良好（2017年5月20日）
  */
  public static final Integer TAP_AREA_RADIUS = 4;

  /**
  * 鉛筆の半径
  * 良好（2017年5月20日）
  */
  public static final Integer PENCIL_RADIUS = 4;

  /**
  * スパーギアの初期半径
  * 良好（2017年5月20日）
  */
  public static final Integer SPUR_RADIUS = 240;

  /**
  * ピニオンギアの初期半径
  * 良好（2017年5月20日）
  */
  public static final Integer PINION_RADIUS = 60;

  /**
  * タップエリアの有効範囲
  * 良好（2017年5月20日）
  */
  public static final double TAP_AREA_RANGE = 64;

  /**
  * スピログラフの初期選択色
  * 良好（2017年5月20日）
  */
  public static final Color RGB_INIT_COLOR = new Color(255,0,0);

  /**
  * メニューボタンのタイトルすべてを格納しておく配列
  * 良好（2017年5月20日）
  */
  public static final String BUTTON_TITLES[] = { "Start","Stop","Save","Load","Clear","Position","Rainbow"};
}
