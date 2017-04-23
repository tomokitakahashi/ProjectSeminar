package spirograph;

import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuView extends View
{
  private ArrayList<String> buttonTitleList;
  public MenuView(MenuModel aMenuModel)
  {
    super(aMenuModel);
    buttonTitleList = new ArrayList<String>(){
      {
        add("Start");
        add("Stop");
        add("Save");
        add("Load");
        add("Clear");
      }
    };
    return;
  }

  public MenuView(MenuModel aMenuModel, MenuController aMenuController)
  {
    super(aMenuModel,aMenuController);
    buttonTitleList = new ArrayList<String>(){
      {
        add("Start");
        add("Stop");
        add("Save");
        add("Load");
        add("Clear");
      }
    };
    return;
  }

  private void createButtonTitles() {

  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    this.setLayout(null);
    Dimension aMenuViewSize = this.getSize();
    MenuController menuController = (MenuController)controller;
    Integer bottonCount = 5;
    for (Integer index = 0;index < buttonTitleList.size() ;index++ ) {
      String aTitle = buttonTitleList.get(index);
      Integer width = aMenuViewSize.width - SpiroConstruct.MENU_BUTTON_MARGIN * 2;
      Integer height =  (aMenuViewSize.height - SpiroConstruct.MENU_BUTTON_MARGIN*(bottonCount+1))/bottonCount;
      JButton aButton = new JButton(aTitle);
      aButton.setBounds(SpiroConstruct.MENU_BUTTON_MARGIN,
                        SpiroConstruct.MENU_BUTTON_MARGIN + index*(height+SpiroConstruct.MENU_BUTTON_MARGIN),
                        width,
                        height);

      aButton.addActionListener(menuController);
      aButton.setActionCommand(aTitle);
      this.add(aButton);
    }
    return;
  }
}
