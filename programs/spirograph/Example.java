package spirograph;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JColorChooser;

/**
 * 例題プログラム。
 */
public class Example extends Object
{
	/**
	 * 画面をキャプチャして画像化し、ビューとコントローラの3つのペア
	 *（MVC-1, MVC-2, MVC-3のウィンドウたち）から1つのモデルを観測している状態を作り出す。
	 * その後、モデルの内容物を先ほどキャプチャした画像にして、
	 * 自分が変化したと騒いだ瞬間、MVC-1, MVC-2, MVC-3のすべてのウィンドウが更新される。
	 * そして、モデルの内容物をnull化して、自分が変化したと騒ぎ、すべてのウィンドウが空に更新される。
	 * この過程を何回か繰り返すことで、MVC: Model-View-Controller（Observerデザインパターン）が
	 * きちんと動いているかを確かめる例題プログラムである。
	 * @param arguments 引数の文字列の配列
	 * バグ（2010年7月25日）
	 * 良好（2010年7月25日）
	 * 修正（2015年10月16日）
	 */
	public static void main(String[] arguments)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension aSpiroDimension = new Dimension(SpiroConstruct.SPIRO_WINDOW.width, SpiroConstruct.SPIRO_WINDOW.height);
		SpiroController aSpiroController = new SpiroController();
		SpiroModel aSpiroModel = new SpiroModel();

		Point offsetPoint = new Point(80, 60); // ウィンドウを出現させる時のオフセット(ズレ：ずらし)
		Integer width = aSpiroDimension.width;
		Integer height = aSpiroDimension.height;
		Integer x = (screenSize.width / 2) - (width / 2);
		Integer y = (screenSize.height / 2) - (height / 2);
		Point displayPoint = new Point(x, y);

			// 上記のモデルのビューとコンピュローラのペアを作り、ウィンドウに乗せる。
		SpiroView aSpiroView = new SpiroView(aSpiroModel,aSpiroController);
		JFrame aWindow = new JFrame("SpiroGraph");
		aWindow.getContentPane().add(aSpiroView);

	// 高さはタイトルバーの高さを考慮してウィンドウの大きさを決定する。
		aWindow.addNotify();
		Integer titleBarHeight = aWindow.getInsets().top;
		width = aSpiroDimension.width;
		height = aSpiroDimension.height + titleBarHeight;
		Dimension windowSize = new Dimension(width, height);
		aWindow.setSize(windowSize.width, windowSize.height);
		// ウィンドウに各種の設定を行って出現させる。
		aWindow.setMinimumSize(new Dimension(400, 300 + titleBarHeight));
		aWindow.setResizable(false);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x = displayPoint.x;
		y = displayPoint.y;
		aWindow.setLocation(x, y);
		aWindow.setVisible(true);
		aWindow.toFront();
		Thread aThread = new Thread(aSpiroView);
		aThread.start();

		// メニュー用 テスト
		Dimension aMenuDimension = new Dimension(SpiroConstruct.MENU_WINDOW.width,SpiroConstruct.MENU_WINDOW.height);
		MenuController aMenuController = new MenuController();
		aMenuController.setMenuActionListener(aSpiroController);
		MenuModel aMenuModel = new MenuModel();
		MenuView aMenuView = new MenuView(aMenuModel,aMenuController);
		JFrame aMenuWindow = new JFrame("Menu");
		aMenuWindow.getContentPane().add(aMenuView);
		aMenuWindow.addNotify();
		aMenuWindow.setSize(aMenuDimension.width,aMenuDimension.height);
		aMenuWindow.setResizable(false);
		aMenuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aMenuWindow.setBounds(0,0,aMenuDimension.width,aMenuDimension.height);
		aMenuWindow.setVisible(true);
		aMenuWindow.toFront();

		// for JColorChooser
		JFrame aColorMenuWindow = new JFrame("ColorChooser");
		JColorChooser colorchooser = new JColorChooser();
		colorchooser.getSelectionModel().addChangeListener(aMenuController);
		aColorMenuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aColorMenuWindow.setBounds(10, 10, 300, 200);
		aColorMenuWindow.setTitle("スピログラフの色選択");
		aColorMenuWindow.setVisible(true);
		aColorMenuWindow.getContentPane().add(colorchooser);

	}
}
