package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class GearModel extends Object
{
  private Point2D.Double centerCoodinate;

  private Double radius;

  public GearModel()
  {
    super();
    centerCoodinate = new Point2D.Double();
    radius = new Double();
  }
  // ギアの半径を設定する
  public void radius(Double aRadius)
  {
    radius = aRadius;
    return;
  }

  // ギアの半径を応答する
  public Double radius()
  {
    return radius;
  }

  // ギアの中心座標を設定する
  public void centerCoodinate(Point2D.Double aCenterCoodinate)
  {
    centerCoodinate = aCenterCoodinate;
    return;
  }

  // ギアの中心座標を応答する
  public Point2D.Double centerCoodinate()
  {
    return centerCoodinate;
  }

  public String toString()
  {
    StringBuffer aBuffer = new StringBuffer();
    Class aClass = this.getClass();
    aBuffer.append(aClass.getName());
    aBuffer.append("[centerCoodinate=");
    aBuffer.append(centerCoodinate);
    aBuffer.append(",radius=");
    aBuffer.append(radius);
    aBuffer.append("]");
    return aBuffer.toString();
  }
}
