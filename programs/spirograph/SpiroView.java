package spirograph;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

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
    this.drawSpurGear(aGraphics);
    this.drawTapArea(aGraphics);
    //this.drawCircle(aGraphics,SpiroConstruct.SPIRO_WINDOW.width/2,SpiroConstruct.SPIRO_WINDOW.width/2,SpiroConstruct.SPIRO_WINDOW.width/2);
    return;
  }

  private void drawCircle(Graphics aGraphics,Integer x,Integer y,Integer radius)
  {
    x = x - radius;
    y = y - radius;
    aGraphics.drawOval(x,y,radius*2,radius*2);
  }

  private void drawPinionGear(Graphics aGraphics)
  {

    return;
  }

  private void drawSpurGear(Graphics aGraphics)
  {
    int x = (int)this.getSpiroModel().spurModel.centerCoodinate().x;
    int y = (int)this.getSpiroModel().spurModel.centerCoodinate().y;
    int radius = (int)this.getSpiroModel().spurModel.radius();
    aGraphics.drawOval(x - radius,y - radius, radius*2, radius*2);
    return;
  }

  private void drawTapArea(Graphics aGraphics)
  {
    ArrayList<Point2D.Double> tapAreaCoodinateList = this.getSpiroModel().spurModel.tapAreaCoodinateList();
    for(Integer index = 0; index < tapAreaCoodinateList.size();index++)
    {
      Point2D.Double areaCoodinate = tapAreaCoodinateList.get(index);
      aGraphics.drawOval((int)areaCoodinate.x,(int)areaCoodinate.y,5,5);
    }
    return;
  }

  public SpiroModel getSpiroModel()
  {
    SpiroModel spiroModel = (SpiroModel)model;
    return spiroModel;
  }
}
