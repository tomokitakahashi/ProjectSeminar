package spirograph;

import java.awt.Dimension;
import java.awt.Point;


public class SpiroConstruct {
  public static final Dimension SPIRO_WINDOW = new Dimension(750,600);
  public static final Dimension MENU_WINDOW = new Dimension(200,300);
  public static final Point SPIRO_WINDOW_CENTER = new Point(375,300);
  public static final Integer MENU_BUTTON_MARGIN = 10;
  public static final Integer TAP_AREA_RADIUS = 6;


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
