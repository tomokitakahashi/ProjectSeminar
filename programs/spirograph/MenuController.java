package spirograph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.io.File;

public class MenuController extends Controller implements ActionListener, ChangeListener
{
  private MenuActionListener menuActionListener = null;

  private JColorChooser colorChooser = null;

  public MenuController()
  {
    super();
    return;
  }

  /**
  * MenuControllerではMouseListenerを使わないためOverrideしてviewのsetのみ
  **/
  @Override
  public void setView(View aView)
  {
    view = aView;
    return;
  }

  /**
  * メニューのアクションリスナーを登録するメソッド
  **/
  public void setMenuActionListener(MenuActionListener aListener)
  {
    menuActionListener = aListener;
    return;
  }

  /**
  * ボタンのアクションを取得するためのメソッド
  **/
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
    else if(aCommand.equals("Position"))
    {
      this.tappedPositionButton();
    }
    else if(aCommand.equals("Rainbow"))
    {
      this.tappedRainbowButton();
    }
    return;
  }

  /**
  * スタートボタンを押した時のアクション
  **/
  private void tappedStartButton()
  {
    System.out.println("tappedStartButton");
    if(menuActionListener == null ){ return; }
    menuActionListener.animationStart();
    return;
  }

  /**
  * ストップボタンを押した時のアクション
  **/
  private void tappedStopButton()
  {
    System.out.println("tappedStopButton");
    if(menuActionListener == null ){ return; }
    menuActionListener.animationStop();
    return;
  }

  /**
  * セーブボタンを押した時のアクション
  **/
  private void tappedSaveButton()
  {
    if(!this.getMenuModel().showDialogEnabled()) return;
    File file = this.getMenuView().showSaveDialog();
    this.getMenuModel().save(file);
    return;
  }

  /**
  * ロードボタンを押した時のアクション
  **/
  private void tappedLoadButton()
  {
    if(!this.getMenuModel().showDialogEnabled()) return;
    File file = this.getMenuView().showLoadDialog();
    this.getMenuModel().load(file);
    return;
  }

  /**
  * クリアボタンを押した時のアクション
  **/
  private void tappedClearButton()
  {
    System.out.println("tappedClearButton");
    if(menuActionListener == null) return;
    menuActionListener.clear();
    return;
  }

  /**
  * 内接・外接切り替えボタンを押した時のアクション
  **/
  private void tappedPositionButton()
  {
    System.out.println("tappedPositionButton");
    if(menuActionListener == null){ return ;}
    menuActionListener.changedPosition();
    return;
  }

  /**
  * スピログラフの色を虹色に設定したときのアクション
  **/
  private void tappedRainbowButton()
  {
    System.out.println("tappedRainbowButton");
    if(menuActionListener == null){ return; }
    menuActionListener.changeColorRainbow();
    return;
  }

  // 色が選択され設定色が変化した時に呼ばれるメソッド
  public void stateChanged(ChangeEvent aEvent)
  {
    if(menuActionListener == null ){ return; }
    menuActionListener.changedColor(colorChooser.getColor());
    return;
  }

  public MenuModel getMenuModel()
  {
    return (MenuModel)model;
  }

  public MenuView getMenuView()
  {
    return (MenuView)view;
  }

  // 色の選択があった時にColorChooserから最新の色を取得するために外部からColorChooserを登録するメソッド
  public void setColorChooser(JColorChooser aColorChooser)
  {
    colorChooser = aColorChooser;
    return;
  }
}
