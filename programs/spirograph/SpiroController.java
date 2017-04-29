package spirograph;

import java.awt.event.MouseEvent;

public class SpiroController extends Controller implements MenuActionListener
{
  private Thread animationThread;

  public SpiroController()
  {
    super();
    return;
  }

  @Override
  public void mouseDragged(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().draggedSpur(aMouseEvent.getPoint());
    this.getSpiroView().update();
    return;
  }

  @Override
  public void mousePressed(MouseEvent aMouseEvent)
	{
    this.getSpiroModel().updateTapArea(aMouseEvent.getPoint());
		return;
	}

  @Override
  public void mouseReleased(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().resetEnabledValue();
    return;
  }

  // メニューからスタートが押された際にデリゲートを通じて通知される
  public void animationStart()
  {
    this.getSpiroModel().setStop(false);
    this.getSpiroView().restart();
    return;
  }
  // メニューからストップが押された際にデリゲートを通じて通知される
  public void animationStop()
  {
    this.getSpiroModel().setStop(true);
    return;
  }

  public void setAnimationThread(Thread aThread)
  {
    animationThread = aThread;
    return;
  }

  private SpiroModel getSpiroModel()
  {
    SpiroModel spiroModel = (SpiroModel)model;
    return spiroModel;
  }

  private SpiroView getSpiroView()
  {
    SpiroView spiroView = (SpiroView)view;
    return spiroView;
  }

}
