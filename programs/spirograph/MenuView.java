package spirograph;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuView extends View
{
  public MenuView(MenuModel aMenuModel)
  {
    super(aMenuModel);
    this.setLayout(null);
    controller = new MenuController();
    return;
  }

  public MenuView(MenuModel aMenuModel, MenuController aMenuController)
  {
    super(aMenuModel,aMenuController);
    this.setLayout(null);
    return;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    Dimension aMenuViewSize = this.getSize();
    JButton aStartButton = new JButton("Start");
    aStartButton.setBounds(aMenuViewSize.width/4,aMenuViewSize.height/5,aMenuViewSize.width - 20,aMenuViewSize.height/8);
    MenuController menuController = (MenuController)controller;
    aStartButton.addActionListener(menuController);
    aStartButton.setActionCommand("Start");
    this.add(aStartButton);
    return;
  }
}
