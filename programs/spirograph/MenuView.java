package spirograph;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuView extends View
{
  // MenuViewのコンストラクタ
  public MenuView(MenuModel aMenuModel)
  {
    super(aMenuModel);
    model = aMenuModel;
    controller = new MenuController();
    return;
  }

  // MenuViewのコンストラクタ
  public MenuView(MenuModel aMenuModel, MenuController aMenuController)
  {
    super(aMenuModel,aMenuController);
    model = aMenuModel;
    controller = aMenuController;
    return;
  }
  // Componentを表示させるためのメソッドggjyouiji
  @Override
  public void paintComponent(Graphics aGraphics)
  {
    JPanel panel = new JPanel();
    panel.setLayout(null);
    Dimension aMenuViewSize = this.getSize();
    panel.setBounds(0,0,aMenuViewSize.width,aMenuViewSize.height);
    MenuModel menuModel = (MenuModel)model;
    MenuController menuController = (MenuController)controller;
    Integer bottonCount = 5;
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

}
