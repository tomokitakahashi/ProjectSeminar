package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class SpiroModel extends Model
{
  // スピログラフで描かれた軌跡モデル
  public SpiroLocusModel spiroLocusModel;

  // スピログラフのスパーギアモデル
  public SpurModel spurModel;

  // スピログラフのピニオンモデル
  public PinionModel pinionModel;

  private Boolean isStop;

  public SpiroModel()
  {
    super();
    Point2D.Double aCenterCoodinate = new Point2D.Double(SpiroConstruct.SPIRO_WINDOW_CENTER.x,SpiroConstruct.SPIRO_WINDOW_CENTER.y);
    double aRadius = 250.0;
    spurModel = new SpurModel(aCenterCoodinate,aRadius);
    return;
  }

  public void setStop()
  {
    isStop = true;
    return;
  }

  public void setStart()
  {
    isStop = false;
    return;
  }

}
