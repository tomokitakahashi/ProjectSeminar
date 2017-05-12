package spirograph;

import java.awt.event.MouseEvent;
import java.awt.Color;

public class SpiroController extends Controller implements MenuActionListener
{
  public SpiroController()
  {
    super();
    return;
  }

  @Override
  public void mouseDragged(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().updateByDrag(aMouseEvent.getPoint());
    this.getSpiroView().update();
    return;
  }

  @Override
  public void mousePressed(MouseEvent aMouseEvent)
	{
    this.getSpiroModel().updateByPress(aMouseEvent.getPoint());
		return;
	}

  @Override
  public void mouseReleased(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().updateByRelease(aMouseEvent.getPoint());
    return;
  }

  // メニューからスタートが押された際にデリゲートを通じて通知される
  public void animationStart()
  {
    System.out.println("animationStart");
    this.getSpiroModel().setStop(false);
    this.getSpiroModel().restartModel();
    return;
  }
  // メニューからストップが押された際にデリゲートを通じて通知される
  public void animationStop()
  {
    this.getSpiroModel().setStop(true);
    return;
  }
  public void changedColor(Color aColor)
  {
    this.getSpiroModel().setSpiroColor(aColor);
    return;
  }

  // スピロモデルを応答するメソッド
  private SpiroModel getSpiroModel()
  {
    SpiroModel spiroModel = (SpiroModel)model;
    return spiroModel;
  }

  // スピロビューを応答するメソッド
  private SpiroView getSpiroView()
  {
    SpiroView spiroView = (SpiroView)view;
    return spiroView;
  }

}
