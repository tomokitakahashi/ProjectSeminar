package spirograph;

import java.awt.Color;
/**
* メニューのアクションをスピログラフのコントローラに伝達するインターフェース
**/
public interface MenuActionListener {
  public void animationStart();
  public void animationStop();
  public void changedColor(Color aColor);
  public void changedPosition();
}
