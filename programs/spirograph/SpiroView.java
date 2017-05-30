package spirograph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import javax.swing.JPanel;
import java.awt.Component;
import java.io.File;

public class SpiroView extends View implements Runnable
{
  /**
  * スピロビューのコンストラクタ
  * 良好（2017年5月20日）
  * @param aSpiroModel スピロモデル
  */

  public SpiroView(SpiroModel aSpiroModel)
  {
    super(aSpiroModel);
    return;
  }

  /**
  * スピロビューのコンストラクタ
  * 良好（2017年5月20日）
  * @param aSpiroModel スピロモデル
  * @param aSpiroController スピロコントローラー
  */
  public SpiroView(SpiroModel aSpiroModel,SpiroController aSpiroController)
  {
    super(aSpiroModel,aSpiroController);
    return;
  }

  /**
  * アニメーションをするスレッドのためのメソッド
  * 良好（2017年5月20日）
  */
  public void run()
  {
    while(true)
    {
      if (!this.getSpiroModel().isStop())
      {
        this.getSpiroModel().updateByAnimation();
        this.getSpiroModel().setDegree();
      }
      this.update();
      try {
        Thread.sleep(1);
      } catch (Exception anException) {
      }
    }
  }

  /**
  * JFrameに描画するためのメソッド
  * 良好（2017年5月20日）
  * @param aGraphics グラフィックス・コンテキスト
  */
  @Override
  public void paintComponent(Graphics aGraphics)
  {
    super.paintComponent(aGraphics);
    this.drawLocus(aGraphics);
    aGraphics.setColor(Color.black);
    this.drawAxis(aGraphics);
    this.drawSpurGear(aGraphics);
    this.drawPinionGear(aGraphics);
    return;
  }

  /**
  * スピログラフの軌跡描画メソッド
  * 良好（2017年5月20日）
  * @param aGraphics グラフィックス・コンテキスト
  */
  private void drawLocus(Graphics aGraphics)
  {
    SpiroLocusModel spiroLocusModel = this.getSpiroModel().getSpiroLocusModel();
    if(spiroLocusModel.isEmpty()) return;
    for(Integer index = 0; index < spiroLocusModel.locusList().size();index ++)
    {
      Point2D.Double coodinate = spiroLocusModel.locusList().get(index);
      Color color = spiroLocusModel.locusColorList().get(index);
      aGraphics.setColor(color);
      aGraphics.fillOval((int)coodinate.x - 3,(int)coodinate.y - 3,5,5);
    }
    return;
  }

  /**
  * ピニオンギア描写メソッド
  * 良好（2017年5月20日）
  * @param aGraphics グラフィックス・コンテキスト
  */
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

  /**
  * スパーギア描写メソッド
  * 良好（2017年5月20日）
  * @param aGraphics グラフィックス・コンテキスト
  */
  private void drawSpurGear(Graphics aGraphics)
  {
    SpurModel spurModel = this.getSpiroModel().getSpurModel();
    aGraphics.setColor(Color.black);
    aGraphics.drawOval((int)spurModel.drawGearCoodinate().x,(int)spurModel.drawGearCoodinate().y, spurModel.drawGearDimension().width,spurModel.drawGearDimension().height);
    aGraphics.drawOval((int)spurModel.drawGearCenterCoodinate().x,(int)spurModel.drawGearCenterCoodinate().y,spurModel.drawGearCenterDimension().width,spurModel.drawGearCenterDimension().height);
    this.drawTapArea(aGraphics,spurModel);
    return;
  }

  /**
  * 軸線描写メソッド
  * 良好（2017年5月20日）
  * @param aGraphics グラフィックス・コンテキスト
  */
  private void drawAxis(Graphics aGraphics)
  {
    SpurModel spurModel = this.getSpiroModel().getSpurModel();
    PinionModel pinionModel = this.getSpiroModel().getPinionModel();
    aGraphics.drawLine((int)spurModel.centerCoodinate().x,(int)spurModel.centerCoodinate().y,(int)pinionModel.centerCoodinate().x,(int)pinionModel.centerCoodinate().y);
    return;
  }

  /**
  * マウスイベント取得エリア描写メソッド
  * 良好（2017年5月20日）
  * @param aGraphics グラフィックス・コンテキスト
  * @param aGear ギアモデル
  */
  private void drawTapArea(Graphics aGraphics, GearModel aGear)
  {
    for(Integer index = 0; index < aGear.tapAreaCoodinateList().size();index++)
    {
      Point2D.Double areaCoodinate = aGear.drawTapAreaCoodinate(index);
      aGraphics.setColor(Color.white);
      if(index == 1)
      {
        aGraphics.setColor(Color.black);
      }
      aGraphics.fillOval((int)areaCoodinate.x,(int)areaCoodinate.y,SpiroConstruct.TAP_AREA_RADIUS*2,SpiroConstruct.TAP_AREA_RADIUS*2);
    }
    return;
  }

  /**
  * スピロモデルのローダー
  * 良好（2017年5月20日）
  * @param aSpiroModel スピロモデル
  */
  public void loadSpiroModel(SpiroModel aSpiroModel)
  {
    model = aSpiroModel;
    return;
  }

  /**
  * SpiroModelにキャストして応答するメソッド
  * 良好（2017年5月20日）
  */
  public SpiroModel getSpiroModel()
  {
    if(model instanceof SpiroModel)
    {
      return (SpiroModel)model;
    }
    return null;
  }

}
