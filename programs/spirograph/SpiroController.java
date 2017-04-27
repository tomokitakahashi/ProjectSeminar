package spirograph;

import java.awt.event.MouseEvent;

public class SpiroController extends Controller
{
  public SpiroController()
  {
    super();
    return;
  }

  @Override
  public void mouseDragged(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().draggedSpur(aMouseEvent.getPoint());
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
  private SpiroModel getSpiroModel()
  {
    SpiroModel spiroModel = (SpiroModel)model;
    return spiroModel;
  }

}
