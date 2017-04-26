package spirograph;

import java.awt.Graphics;
import java.awt.Dimension;

public class SpiroView extends View
{
  public SpiroView(SpiroModel aSpiroModel)
  {
    super(aSpiroModel);
    return;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    SpiroModel spiroModel = (SpiroModel)model;
    this.drawCircle(aGraphics,SpiroConstruct.SPIRO_WINDOW.width/2,SpiroConstruct.SPIRO_WINDOW.width/2,SpiroConstruct.SPIRO_WINDOW.width/2);
    return;
  }


  private void drawCircle(Graphics aGraphics,Integer x,Integer y,Integer radius)
  {
    x = x - radius;
    y = y - radius;
    aGraphics.drawOval(x,y,radius*2,radius*2);
  }
}
