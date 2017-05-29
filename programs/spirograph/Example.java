package spirograph;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;

/**
 * 例題プログラム。
 */
public class Example extends Object
{
	/**
	 * メニュー画面、スピログラフ画面、色選択画面を生成し、表示させる。
	 * MVCパアーンを適用しており、MenuControllerをSpiroControllerはデリゲートを用いてアクションの伝達を行っている。
	 * バグ（2017年5月20日）
	 * バグ（2017年5月28日）
	 * 修正（2017年5月29日）
	 * @param arguments 引数の文字列の配列
	 */
	public static void main(String[] arguments)
	{

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SpiroController aSpiroController = new SpiroController();
		SpiroModel aSpiroModel = new SpiroModel();
		Integer x = (screenSize.width / 2) - (SpiroConstruct.SPIRO_WINDOW.width / 2);
		Integer y = (screenSize.height / 2) - (SpiroConstruct.SPIRO_WINDOW.height / 2);
		Point displayPoint = new Point(x, y);

		// 上記のモデルのビューとコンピュローラのペアを作り、ウィンドウに乗せる。
		SpiroView aSpiroView = new SpiroView(aSpiroModel,aSpiroController);

		JFrame aWindow = new JFrame("SpiroGraph");
		aWindow.getContentPane().add(aSpiroView);

		// 高さはタイトルバーの高さを考慮してウィンドウの大きさを決定する。
		aWindow.addNotify();
		Integer titleBarHeight = aWindow.getInsets().top;
		aWindow.setSize(SpiroConstruct.SPIRO_WINDOW.width, SpiroConstruct.SPIRO_WINDOW.height+titleBarHeight);

		// ウィンドウに各種の設定を行って出現させる。
		aWindow.setMinimumSize(new Dimension(400, 300 + titleBarHeight));
		aWindow.setResizable(false);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x = displayPoint.x;
		y = displayPoint.y;
		aWindow.setLocation(x, y);
		aWindow.toFront();

		Thread aThread = new Thread(aSpiroView);
		aThread.start();

		// メニュー用 テスト
		MenuController aMenuController = new MenuController();
		aMenuController.setMenuActionListener(aSpiroController);
		MenuModel aMenuModel = new MenuModel(aSpiroModel);
		MenuView aMenuView = new MenuView(aMenuModel,aMenuController);
		JFrame aMenuWindow = new JFrame("Menu");
		aMenuWindow.getContentPane().add(aMenuView);
		aMenuWindow.addNotify();
		aMenuWindow.setSize(SpiroConstruct.MENU_WINDOW.width,SpiroConstruct.MENU_WINDOW.height);
		aMenuWindow.setResizable(false);
		aMenuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aMenuWindow.setBounds(x - SpiroConstruct.MENU_WINDOW.width,y,SpiroConstruct.MENU_WINDOW.width,SpiroConstruct.MENU_WINDOW.height);
		aMenuWindow.toFront();

		// for JColorChooser
		JFrame aColorMenuWindow = new JFrame("スピログラフの色選択");
		JColorChooser colorchooser = new JColorChooser();
		colorchooser.getSelectionModel().addChangeListener(aMenuController);
		aColorMenuWindow.getContentPane().add(colorchooser);
		aColorMenuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aColorMenuWindow.setBounds(x + SpiroConstruct.SPIRO_WINDOW.width, y, 450, 300);

		// JColorChooserをMenuControllerに登録
		aMenuController.setColorChooser(colorchooser);

		// Swingのコーディング規約によってshow()が非推奨となり(setVisibleでshowを駆動させている)、
		// invokeLaterを用いてsetVisible(true)を実行することを推奨しているため
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				aWindow.setVisible(true);
				aMenuWindow.setVisible(true);
				aColorMenuWindow.setVisible(true);
			}
		});
	}
}
