package spirograph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;

public class MenuController extends Controller implements ActionListener, ChangeListener
{
  private MenuActionListener menuActionListener = null;

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

  // メニューのアクションリスナーを登録するメソッド
  public void setMenuActionListener(MenuActionListener aListener)
  {
    menuActionListener = aListener;
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
    return;
  }

  // スタートボタンを押した時のアクションゲッター
  private void tappedStartButton()
  {
    System.out.println("tappedStartButton");
    if(menuActionListener == null ){ return; }
    menuActionListener.animationStart();
    return;
  }

  // ストップボタンを押した時のアクションゲッター
  private void tappedStopButton()
  {
    System.out.println("tappedStopButton");
    if(menuActionListener == null ){ return; }
    menuActionListener.animationStop();
    return;
  }

  // セーブボタンを押した時のアクションゲッター
  private void tappedSaveButton()
  {
    System.out.println("tappedSaveButton");
    return;
  }

  // クリアボタンを押した時のアクションゲッター
  private void tappedLoadButton()
  {
    System.out.println("tappedLoadButton");
    return;
  }

  // クリアボタンを押した時のアクションゲッター
  private void tappedClearButton()
  {
    System.out.println("tappedClearButton");
    return;
  }

  // 色が選択され設定色が変化した時に呼ばれるメソッド
  public void stateChanged(ChangeEvent aEvent)
  {
    System.out.println("selected any color");
    return;
  }

}
