package spirograph;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;

public class MenuView extends View
{
  /**
  *  MenuViewのコンストラクタ
  * @param aMenuModel メニューモデル
  **/
  public MenuView(MenuModel aMenuModel)
  {
    super(aMenuModel);
    model = aMenuModel;
    model.addDependent(this);
    controller = new MenuController();
    return;
  }

  /**
  * MenuViewのコンストラクタ
  * @param aMenuModel メニューモデル
  * @param aMenuController メニューコントローラ
  **/
  public MenuView(MenuModel aMenuModel, MenuController aMenuController)
  {
    super(aMenuModel,aMenuController);
    model = aMenuModel;
    model.addDependent(this);
    controller = aMenuController;
    return;
  }
  /**
  * Componentを表示させるためのメソッド
  * @param aGraphics グラフィックス・コンテキスト
  **/
  @Override
  public void paintComponent(Graphics aGraphics)
  {
    JPanel panel = new JPanel();
    panel.setLayout(null);
    Dimension aMenuViewSize = this.getSize();
    panel.setBounds(0,0,aMenuViewSize.width,aMenuViewSize.height);
    MenuModel menuModel = this.getMenuModel();
    MenuController menuController = this.getMenuController();
    Integer bottonCount = 7;
    for (Integer index = 0;index < menuModel.getButtonTitleList().size() ;index++ ) {
      String aTitle = menuModel.getButtonTitleList().get(index);
      Integer width = aMenuViewSize.width - SpiroConstruct.MENU_BUTTON_MARGIN * 2;
      Integer height =  (aMenuViewSize.height - SpiroConstruct.MENU_BUTTON_MARGIN*(bottonCount+1))/bottonCount;
      JButton aButton = new JButton(aTitle);
      aButton.setBounds(SpiroConstruct.MENU_BUTTON_MARGIN,
                        SpiroConstruct.MENU_BUTTON_MARGIN + index*(height+SpiroConstruct.MENU_BUTTON_MARGIN),
                        width,
                        height);
      aButton.addActionListener(menuController);
      aButton.setActionCommand(aTitle);
      panel.add(aButton);
      this.add(panel);
    }
    return;
  }

  /**
  * ロード用のダイアログ表示
  **/
  public File showLoadDialog()
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int option = fileChooser.showOpenDialog(this);
    if(option == JFileChooser.APPROVE_OPTION)
    {
      return fileChooser.getSelectedFile();
    }
    return null;
  }

  /**
  * セーブ用のダイアログ表示
  **/
  public File showSaveDialog()
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int option = fileChooser.showSaveDialog(this);
    if(option == JFileChooser.APPROVE_OPTION)
    {
      return fileChooser.getSelectedFile();
    }
    return null;
  }

  // 追加
  public MenuModel getMenuModel()
  {
    if(model instanceof MenuModel)
    {
      return (MenuModel)model;
    }
    return null;
  }

  public MenuController getMenuController()
  {
    if(controller instanceof MenuController)
    {
      return (MenuController)controller;
    }
    return null;
  }

}
