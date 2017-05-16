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
    MenuModel menuModel = (MenuModel)model;
    MenuController menuController = (MenuController)controller;
    Integer bottonCount = 6;
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
  * ファイル選択のダイアログを表示するメソッド
  **/
  public void showDialog(int aMode)
  {
    JFileChooser filechooser = new JFileChooser();
    filechooser.setFileSelectionMode(aMode);
    int selected = filechooser.showOpenDialog(this);
    return;
  }

}
