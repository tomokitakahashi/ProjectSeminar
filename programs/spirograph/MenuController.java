package spirograph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

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
    if(aCommand.equals("Start"))
    {
      this.tappedStartButton();
    }
    else if(aCommand.equals("Stop"))
    {
      this.tappedStopButton();
    }
    else if(aCommand.equals("Save"))
    {
      this.tappedSaveButton();
    }
    else if(aCommand.equals("Load"))
    {
      this.tappedLoadButton();
    }
    else if(aCommand.equals("Clear"))
    {
      this.tappedClearButton();
    }
  }

  private void tappedStartButton()
  {
    System.out.println("tappedStartButton");
    return;
  }

  private void tappedStopButton()
  {
    System.out.println("tappedStopButton");
    return;
  }

  private void tappedSaveButton()
  {
    System.out.println("tappedSaveButton");
    return;
  }

  private void tappedLoadButton()
  {
    System.out.println("tappedLoadButton");
    return;
  }

  private void tappedClearButton()
  {
    System.out.println("tappedClearButton");
    return;
  }

}
