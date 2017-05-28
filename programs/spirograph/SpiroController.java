package spirograph;

import java.awt.event.MouseEvent;
import java.awt.Color;

public class SpiroController extends Controller implements MenuActionListener
{
  /**
  * スピロコントローラーのコンストラクタ
  * 良好（2017年5月20日)
  */
  public SpiroController()
  {
    super();
    return;
  }

  /**
  * マウスドラッグのゲッター
  * 良好（2017年5月20日)
  * @param aMouseEvent マウスイベント
  */
  @Override
  public void mouseDragged(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().updateByDrag(aMouseEvent.getPoint());
    this.getSpiroView().update();
    return;
  }

  /**
  * マウスプレスのゲッター
  * 良好（2017年5月20日)
  * @param aMouseEvent マウスイベント
  */
  @Override
  public void mousePressed(MouseEvent aMouseEvent)
	{
    this.getSpiroModel().updateByPress(aMouseEvent.getPoint());
		return;
	}

  /**
  * マウスリリースのゲッター
  * 良好（2017年5月20日)
  * @param aMouseEvent マウスイベント
  */
  @Override
  public void mouseReleased(MouseEvent aMouseEvent)
  {
    this.getSpiroModel().updateByRelease(aMouseEvent.getPoint());
    return;
  }

  /**
  * アニメーションスタートのゲッター
  * 良好（2017年5月20日)
  */
  public void animationStart()
  {
    System.out.println("animationStart");
    this.getSpiroModel().setStop(false);
    this.getSpiroModel().restartModel();
    return;
  }

  /**
  * アニメーションストップのゲッター
  * 良好（2017年5月20日)
  */
  public void animationStop()
  {
    this.getSpiroModel().setStop(true);
    return;
  }

  /**
  * カラー変更のセッター
  * 良好（2017年5月22日)
  * @param aColor カラー
  */
  public void changedColor(Color aColor)
  {
    this.getSpiroModel().setSpiroRainbowColor(false);
    this.getSpiroModel().setSpiroColor(aColor);
    return;
  }

  /**
  * スピログラフの色が虹色に設定された時のメソッド
  * 良好（2017年5月26日)
  */
  public void changeColorRainbow()
  {
    this.getSpiroModel().setSpiroRainbowColor(true);
    return;
  }

  /**
  * 内接・外接の変更ゲッター
  * 良好（2017年5月23日)
  */
  public void changedPosition()
  {
    if(!this.getSpiroModel().isStop()) return;
    this.getSpiroModel().changePinionPosition();
    return;
  }

  /**
  * クリアのゲッター
  * 良好（2017年5月20日)
  */
  public void clear()
  {
    if(!this.getSpiroModel().isStop()) return;
    this.getSpiroModel().clearLocus();
    return;
  }

  /**
  * スピロモデルを応答するメソッド
  * 良好（2017年5月20日)
  */
  private SpiroModel getSpiroModel()
  {
    if(model instanceof SpiroModel)
    {
      return (SpiroModel)model;
    }
    return null;
  }

  /**
  * スピロビューを応答するメソッド
  * 良好（2017年5月20日)
  */
  private SpiroView getSpiroView()
  {
    if(view instanceof SpiroView)
    {
      return (SpiroView)view;
    }
    return null;
  }
}
