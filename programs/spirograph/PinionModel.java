package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class PinionModel extends GearModel
{
  private ArrayList<Point2D.Double> lineCoodinate;

  private Point2D.Double pencilCoodinate;

  public PinionModel()
  {
    super();
    return;
  }

  public ArrayList<Point2D.Double> lineCoodinate()
  {
    return lineCoodinate;
  }

  public void lineCoodinate(ArrayList<Point2D.Double> aLineCoodinate)
  {
    lineCoodinate = aLineCoodinate;
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
