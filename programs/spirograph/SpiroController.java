package spirograph;

import java.awt.event.MouseEvent;
import java.awt.Color;

public class SpiroController extends Controller implements MenuActionListener
{
  /**
  * スピロコントローラーのコンストラクタ
  **/
  public SpiroController()
  {
    super();
    return;
  }

  /**
  * マウスドラッグのゲッター
  * @param aMouseEvent マウスイベント
  **/
  @Override
  public void mouseDragged(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().updateByDrag(aMouseEvent.getPoint());
    this.getSpiroView().update();
    return;
  }

  /**
  * マウスプレスのゲッター
  * @param aMouseEvent マウスイベント
  **/
  @Override
  public void mousePressed(MouseEvent aMouseEvent)
	{
    this.getSpiroModel().updateByPress(aMouseEvent.getPoint());
		return;
	}

  /**
  * マウスリリースのゲッター
  * @param aMouseEvent マウスイベント
  **/
  @Override
  public void mouseReleased(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().updateByRelease(aMouseEvent.getPoint());
    return;
  }

  /**
  * アニメーションスタートのゲッター
  **/
  public void animationStart()
  {
    System.out.println("animationStart");
    this.getSpiroModel().setStop(false);
    this.getSpiroModel().restartModel();
    return;
  }

  /**
  * アニメーションストップのゲッター
  **/
  public void animationStop()
  {
    this.getSpiroModel().setStop(true);
    return;
  }

  /**
  * カラー変更のセッター
  * @param aColor カラー
  **/
  public void changedColor(Color aColor)
  {
    this.getSpiroModel().setSpiroRainbowColor(false);
    this.getSpiroModel().setSpiroColor(aColor);
    return;
  }

  /**
  * スピログラフの色が虹色に設定された時のメソッド
  **/
  public void changeColorRainbow()
  {
    this.getSpiroModel().setSpiroRainbowColor(true);
    return;
  }

  /**
  * 内接・外接の変更ゲッター
  **/
  public void changedPosition()
  {
    if(!this.getSpiroModel().isStop()) return;
    this.getSpiroModel().changePinionPosition();
    return;
  }

  /**
  * クリアのゲッター
  **/
  public void clear()
  {
    if(!this.getSpiroModel().isStop()) return;
    this.getSpiroModel().clearLocus();
    return;
  }

  /**
  * スピロモデルを応答するメソッド
  **/
  private SpiroModel getSpiroModel()
  {
    SpiroModel spiroModel = (SpiroModel)model;
    return spiroModel;
  }

  /**
  * スピロビューを応答するメソッド
  **/
  private SpiroView getSpiroView()
  {
    SpiroView spiroView = (SpiroView)view;
    return spiroView;
  }
}
