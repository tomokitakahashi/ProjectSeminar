package spirograph;

import java.util.ArrayList;
import java.awt.Color;

public class MenuModel extends Model
{

  private SpiroModel spiroModel;

  // メニューに表示させるArrayList
  private ArrayList<String> buttonTitleList;

  //スピログラフの奇跡の色を保持しておく
  private Color selectedColor;

  // MenuModel のコンストラクタ
  public MenuModel(SpiroModel aSpiroModel)
  {
    super();
    selectedColor = Color.black;
    spiroModel = aSpiroModel;
    spiroModel.setMenuModel(this);
    buttonTitleList = new ArrayList<String>() {
      {
        add("Start");
        add("Stop");
        add("Save");
        add("Load");
        add("Clear");
        add("Position");
      }
    };
    return;
  }

  public void setSelectedColor(Color aColor)
  {
    selectedColor = aColor;
    return;
  }

  // 選択されている色を応答する
  public Color getSelectedColor()
  {
    return selectedColor;
  }

  // 表示するボタンのタイトルリストを応答する
  public ArrayList<String> getButtonTitleList()
  {
    return buttonTitleList;
  }
}
