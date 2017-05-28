package spirograph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;

public class SpiroConstruct {
  public static final Integer MENU_BUTTON_MARGIN = 10;

  // ウィンドウサイズ
  public static final Dimension SPIRO_WINDOW = new Dimension(750,600);
  public static final Dimension MENU_WINDOW = new Dimension(200,400);
  public static final Dimension FILE_WINDOW = new Dimension(750,600);

  //各円の初期中心座標
  public static final Point2D.Double SPIRO_WINDOW_CENTER = new Point2D.Double(375,300);
  public static final Point2D.Double PINION_CENTER = new Point2D.Double(555,300);
  public static final Point2D.Double PENCIL_CENTER = new Point2D.Double(585,300);

  // 各円の初期半径
  public static final Integer TAP_AREA_RADIUS = 4;
  public static final Integer PENCIL_RADIUS = 4;
  public static final Integer SPUR_RADIUS = 240;
  public static final Integer PINION_RADIUS = 60;

  // イベント関係定数
  public static final double TAP_AREA_RANGE = 64;

  // 色とりどりにする際の初期値
  public static final Color RGB_INIT_COLOR = new Color(255,0,0);

  // package protected
  static final String BUTTON_TITLES[] = { "Start","Stop","Save","Load","Clear","Position","Rainbow"};
}
