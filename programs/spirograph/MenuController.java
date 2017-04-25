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

  // MenuControllerではMouseListenerを使わないためOverrideしてviewのsetのみ
  @Override
  public void setView(View aView)
  {
    view = aView;
    return;
  }

  // ボタンのアクションを取得するためのメソッド
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

  // スタートボタンを押した時のアクションゲッター
  private void tappedStartButton()
  {
    System.out.println("tappedStartButton");
    //MenuModel aMenuModel = (MenuModel)model;
    // if(aMenuModel.isStop())
    // {
    //   aMenuModel.setStop(false);
    // }
    return;
  }

  // ストップボタンを押した時のアクションゲッター
  private void tappedStopButton()
  {
    System.out.println("tappedStopButton");
    //MenuModel aMenuModel = (MenuModel)model;
    //aMenuModel.setStop(true);
    return;
  }

  // セーブボタンを押した時のアクションゲッター
  private void tappedSaveButton()
  {
    System.out.println("tappedSaveButton");
    // MenuModel aMenuModel = (MenuModel)model;
    // aMenuModel.save();
    return;
  }

  // クリアボタンを押した時のアクションゲッター
  private void tappedLoadButton()
  {
    System.out.println("tappedLoadButton");
    // MenuModel aMenuModel = (MenuModel)model;
    // aMenuModel.load();
    return;
  }

  // クリアボタンを押した時のアクションゲッター
  private void tappedClearButton()
  {
    System.out.println("tappedClearButton");
    // MenuModel aMenuModel = (MenuModel)model;
    // aMenuModel.clear();
    return;
  }

}
