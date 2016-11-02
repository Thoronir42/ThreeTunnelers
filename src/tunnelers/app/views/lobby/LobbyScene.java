package tunnelers.app.views.lobby;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import tunnelers.app.ATunnelersScene;
import tunnelers.app.TunnelersStage;
import tunnelers.app.render.colors.AColorScheme;
import tunnelers.app.views.serverList.ServerListScene;
import tunnelers.core.chat.Chat;

/**
 *
 * @author Stepan
 */
public class LobbyScene extends ATunnelersScene {

	private static LobbyScene instance;

	public static LobbyScene getInstance(Chat chat, AColorScheme colors) throws IllegalStateException {
		if (instance == null) {
			instance = createInstance(chat, colors);
		}
		
		return instance;
	}
	
	public static void clearInstance(){
		instance = null;
	}
	
	private static LobbyScene createInstance(Chat chat, AColorScheme colors) {
		GridPane content = new GridPane();
		content.setHgap(4);
		content.setVgap(20);
		content.setAlignment(Pos.CENTER);

		content.setBackground(new Background(new BackgroundFill(new Color(0.11, 0.17, 0.69, 0.2), CornerRadii.EMPTY, Insets.EMPTY)));
		
		LobbyScene scene = new LobbyScene(content, settings.getWindowWidth(), settings.getWindowHeight(), chat, colors);
		addComponents(content, scene);

		return scene;
	}

	private static void addComponents(GridPane root, LobbyScene scene) {
		WebView he_chatBox = new WebView();
		he_chatBox.setPrefSize(400, 260);
		
		scene.wv_chatBox = he_chatBox;
		root.add(scene.wv_chatBox, 0, 0, 2, 1);
		
		TextField tf_chatIn = new TextField();
		
		scene.tf_chatIn = tf_chatIn;
		root.add(scene.tf_chatIn, 0, 1);

		Button but_send = new Button("Odeslat");
		but_send.setOnAction((ActionEvent event) -> {
			scene.sendChatMessage();
		});
		root.add(but_send, 1, 1);

		
		Button but_start = new Button("Vyzkoušet");
		but_start.setOnAction((ActionEvent event) -> {
			scene.getStage().beginGame();
		});
		root.add(but_start, 1, 2);

		Button but_back = new Button("Odejít do menu");
		but_back.setOnAction((ActionEvent event) -> {
			scene.getStage().prevScene();
		});
		root.add(but_back, 1, 3);
	}
	
	protected WebView wv_chatBox;
	protected TextField tf_chatIn;
	
	protected ChatPrinter chatPrinter;

	public LobbyScene(Parent root, double width, double height, Chat chat, AColorScheme colors) {
		super(root, width, height, "Join Game");
		this.chatPrinter = new ChatPrinter(chat, colors);
	}
	
	@Override
	public void handleKeyPressed(KeyCode code) {
		switch (code) {
			case ENTER:
				sendChatMessage();
				break;
		}
	}

	public void updateChatbox() {
		this.wv_chatBox.getEngine().loadContent(this.chatPrinter.getHtml());
	}
	
	protected void sendChatMessage(String message){
		if(message.length() > 0){
			this.getEngine().sendPlainText(message);
		}
		this.tf_chatIn.setText("");
	}

	private void sendChatMessage() {
		this.sendChatMessage(tf_chatIn.getText());
	}

	@Override
	public Class getPrevScene() {
		return ServerListScene.class;
	}
	
}