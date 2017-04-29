package spirograph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class SpiroView extends View implements Runnable
{
  public SpiroView(SpiroModel aSpiroModel)
  {
    super(aSpiroModel);
    return;
  }
  public SpiroView(SpiroModel aSpiroModel,SpiroController aSpiroController)
  {
    super(aSpiroModel,aSpiroController);
    return;
  }

  public void run()
  {
    double spurRadian = 0.000;
    SpurModel spurModel = this.getSpiroModel().getSpurModel();
    while(true)
    {
      if (!this.getSpiroModel().isStop())
      {
        System.out.println("aaa");
        double radian = Math.toRadians(sparRadian);
        this.getSpiroModel().updateByRadian(radian);
        this.update();
        spurRadian += 0.1;
      } else {

      }
      try {
        Thread.sleep(1);
      } catch (Exception anException) {
      }
    }
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    SpurModel spurModel = this.getSpiroModel().getSpurModel();
    PinionModel pinionModel = this.getSpiroModel().getPinionModel();
    aGraphics.setColor(Color.black);
    this.drawAxis(aGraphics);
    this.drawSpurGear(aGraphics);
    this.drawPinionGear(aGraphics);
    return;
  }

  private void drawPinionGear(Graphics aGraphics)
  {
    PinionModel pinionModel = this.getSpiroModel().getPinionModel();
    aGraphics.setColor(Color.black);
    aGraphics.drawOval((int)pinionModel.drawGearCoodinate().x,(int)pinionModel.drawGearCoodinate().y,pinionModel.drawGearDimension().width,pinionModel.drawGearDimension().height);
    aGraphics.drawOval((int)pinionModel.drawGearCenterCoodinate().x,(int)pinionModel.drawGearCenterCoodinate().y,pinionModel.drawGearCenterDimension().width,pinionModel.drawGearCenterDimension().height);
    aGraphics.drawOval((int)pinionModel.drawPencilCoodinate().x,(int)pinionModel.drawPencilCoodinate().y,SpiroConstruct.PENCIL_RADIUS*2,SpiroConstruct.PENCIL_RADIUS*2);
    aGraphics.drawLine((int)pinionModel.tapAreaCoodinateList().get(0).x,(int)pinionModel.tapAreaCoodinateList().get(0).y,(int)pinionModel.tapAreaCoodinateList().get(2).x,(int)pinionModel.tapAreaCoodinateList().get(2).y);
    aGraphics.drawLine((int)pinionModel.tapAreaCoodinateList().get(1).x,(int)pinionModel.tapAreaCoodinateList().get(1).y,(int)pinionModel.tapAreaCoodinateList().get(3).x,(int)pinionModel.tapAreaCoodinateList().get(3).y);
    this.drawTapArea(aGraphics,pinionModel);
    return;
  }

  private void drawSpurGear(Graphics aGraphics)
  {
    SpurModel spurModel = this.getSpiroModel().getSpurModel();
    aGraphics.setColor(Color.black);
    aGraphics.drawOval((int)spurModel.drawGearCoodinate().x,(int)spurModel.drawGearCoodinate().y, spurModel.drawGearDimension().width,spurModel.drawGearDimension().height);
    aGraphics.drawOval((int)spurModel.drawGearCenterCoodinate().x,(int)spurModel.drawGearCenterCoodinate().y,spurModel.drawGearCenterDimension().width,spurModel.drawGearCenterDimension().height);
    this.drawTapArea(aGraphics,spurModel);
    return;
  }

  private void drawAxis(Graphics aGraphics)
  {
    SpurModel spurModel = this.getSpiroModel().getSpurModel();
    PinionModel pinionModel = this.getSpiroModel().getPinionModel();
    aGraphics.drawLine((int)spurModel.centerCoodinate().x,(int)spurModel.centerCoodinate().y,(int)pinionModel.centerCoodinate().x,(int)pinionModel.centerCoodinate().y);
    return;
  }

  private void drawTapArea(Graphics aGraphics, GearModel aGear)
  {
    for(Integer index = 0; index < aGear.tapAreaCoodinateList().size();index++)
    {
      Point2D.Double areaCoodinate = aGear.drawTapAreaCoodinate(index);
      if(index == 1){
        aGraphics.setColor(Color.black);
      } else {
        aGraphics.setColor(Color.white);
      }
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
