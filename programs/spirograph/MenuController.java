package spirograph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController extends Controller implements ActionListener
{
  public MenuController()
  {
    super();
    return;
  }

  public void actionPerformed(ActionEvent aEvent)
  {
    String aCommand = aEvent.getActionCommand();
    if(aCommand.equals("Start")) {
      this.tappedStartButton();
    }
  }

  private void tappedStartButton()
  {
    System.out.println("tappedStartButton");
    return;
  }

  private void tappedStopButton()
  {
    return;
  }

  private void tappedSaveButton()
  {
    return;
  }

  private void tappedLoadButton()
  {
    return;
  }

  private void tappedClearButton()
  {
    return;
  }

}
