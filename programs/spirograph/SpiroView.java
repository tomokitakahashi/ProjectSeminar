package spirograph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class SpiroView extends View implements Runnable
{
  public double pinionGearX;
  public double pinionGearY;

  public SpiroView(SpiroModel aSpiroModel)
  {
    super(aSpiroModel);
    return;
  }
  public SpiroView(SpiroModel aSpiroModel,SpiroController aSpiroController)
  {
    super(aSpiroModel,aSpiroController);
    pinionGearX = this.getSpiroModel().pinionModel.centerCoodinate().x;
    pinionGearY = this.getSpiroModel().pinionModel.centerCoodinate().y;
    return;
  }

  public void run()
  {
    Integer ra = 0;
    SpurModel spurModel = this.getSpiroModel().spurModel;
    while(true)
    {
      double radian = Math.toRadians(ra);
      pinionGearX = Math.cos(radian) * (250*2/3) + spurModel.centerCoodinate().x;
      pinionGearY = Math.sin(radian) * (250*2/3) + spurModel.centerCoodinate().y;
      this.update();
      ra ++;
      try{
        Thread.sleep(10);
      } catch (Exception e) {
      }

    }
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    Point2D.Double spurCenterCoodinate = this.getSpiroModel().spurModel.centerCoodinate();
    Point2D.Double pinionCenterCoodinate = this.getSpiroModel().pinionModel.centerCoodinate();
    aGraphics.setColor(Color.black);
    aGraphics.drawLine((int)spurCenterCoodinate.x,(int)spurCenterCoodinate.y,(int)pinionGearX,(int)pinionGearY);
    this.drawSpurGear(aGraphics);
    this.drawPinionGear(aGraphics);
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
    int radius = (int)this.getSpiroModel().pinionModel.radius();
    aGraphics.setColor(Color.black);
    aGraphics.drawOval((int)pinionGearX - radius,(int)pinionGearY - radius, radius*2, radius*2);
    aGraphics.drawOval((int)pinionGearX - SpiroConstruct.TAP_AREA_RADIUS,(int)pinionGearY - SpiroConstruct.TAP_AREA_RADIUS,SpiroConstruct.TAP_AREA_RADIUS*2,SpiroConstruct.TAP_AREA_RADIUS*2);
    this.drawTapArea(aGraphics,this.getSpiroModel().pinionModel.tapAreaCoodinateList());
    return;
  }

  private void drawSpurGear(Graphics aGraphics)
  {
    int x = (int)this.getSpiroModel().spurModel.centerCoodinate().x;
    int y = (int)this.getSpiroModel().spurModel.centerCoodinate().y;
    int radius = (int)this.getSpiroModel().spurModel.radius();
    aGraphics.setColor(Color.black);
    aGraphics.drawOval(x - radius,y - radius, radius*2, radius*2);
    aGraphics.drawOval(x - SpiroConstruct.TAP_AREA_RADIUS,y - SpiroConstruct.TAP_AREA_RADIUS,SpiroConstruct.TAP_AREA_RADIUS*2,SpiroConstruct.TAP_AREA_RADIUS*2);
    this.drawTapArea(aGraphics,this.getSpiroModel().spurModel.tapAreaCoodinateList());
    return;
  }

  private void drawTapArea(Graphics aGraphics, ArrayList<Point2D.Double> aTapAreaCoodinateList)
  {
    for(Integer index = 0; index < aTapAreaCoodinateList.size();index++)
    {
      Point2D.Double areaCoodinate = aTapAreaCoodinateList.get(index);
      aGraphics.setColor(Color.white);
      aGraphics.fillOval((int)areaCoodinate.x,(int)areaCoodinate.y,SpiroConstruct.TAP_AREA_RADIUS*2,SpiroConstruct.TAP_AREA_RADIUS*2);
    }
    return;
  }

  public SpiroModel getSpiroModel()
  {
    SpiroModel spiroModel = (SpiroModel)model;
    return spiroModel;
  }
}
