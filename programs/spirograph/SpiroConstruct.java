package spirograph;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class SpiroConstruct {
  public static final Integer MENU_BUTTON_MARGIN = 10;

  // ウィンドウサイズ
  public static final Dimension SPIRO_WINDOW = new Dimension(750,600);
  public static final Dimension MENU_WINDOW = new Dimension(200,300);

  //各円の初期中心座標
  public static final Point2D.Double SPIRO_WINDOW_CENTER = new Point2D.Double(375,300);
  public static final Point2D.Double PINION_CENTER = new Point2D.Double(555,300);
  public static final Point2D.Double PENCIL_CENTER = new Point2D.Double(585,290);

  // 各円の初期半径
  public static final Integer TAP_AREA_RADIUS = 4;
  public static final Integer PENCIL_RADIUS = 6;
  public static final Integer SPUR_RADIUS = 240;
  public static final Integer PINION_RADIUS = 60;

  // メニューに表示するボタン群を定値として持っておく
  public static enum MENU_COMMAND {
    Start("Start",0),Stop("Stop",1),Save("Save",2),Load("Load",3),Clear("Clear",4);
    private final String label;
    private final Integer index;
    private MENU_COMMAND(String aLabel,Integer aIndex)
    {
      label = aLabel;
      index = aIndex;
    }
    public String toString()
    {
      return label;
    }
    public Integer getIndex()
    {
      return index;
    }
  }
}
