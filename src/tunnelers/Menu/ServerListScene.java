package tunnelers.Menu;

import generic.BackPasser;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import tunnelers.Settings;
import tunnelers.network.NetWorks;

/**
 *
 * @author Stepan
 */
public class ServerListScene extends AMenuScene {

	BackPasser<String[]> lobbyPasser;

	public static ServerListScene getInstance() {
		BorderPane root = new BorderPane();
		Settings settings = Settings.getInstance();

		ServerListScene scene = new ServerListScene(root, settings.getWidth(), settings.getHeight());
		scene.lobbyPasser = new BackPasser<String[]>() {
			@Override
			public void run() {
				scene.parseAndInsertLobbies(this.get());
			}
		};

		addComponents(root, scene, settings);
		return scene;
	}

	private static void addComponents(BorderPane root, ServerListScene scene, Settings settings) {

		scene.tf_clientName = new TextField("Faggot");

		scene.serverList = new ListView<>(scene.serverListItems);
		scene.serverList.setOnMouseClicked((MouseEvent e) -> {
			if (e.getClickCount() == 2) {
				scene.connectToGame(scene.serverList.getSelectionModel().getSelectedItem());
			}
		});
		for (byte i = 0; i < 32; i++) {
			scene.serverListItems.add(new GameRoom(i));
		}
		scene.lbl_conInfo = new Label();
		scene.lbl_conInfo.setText("Waiting...");

		scene.but_getLobbies = new Button("Výpis místností");
		scene.but_getLobbies.setOnAction((ActionEvent event) -> {
			scene.refreshServerList();
		});

		root.setTop(createTopBar(scene, settings));
		root.setCenter(scene.serverList);
		root.setBottom(createBottomBar(scene));
	}

	private static HBox createTopBar(ServerListScene scene, Settings settings) {
		HBox top = new HBox(4);
		top.setStyle("-fx-background-color: #A8A8A8");

		top.setAlignment(Pos.CENTER);
		
		scene.topButtons = new HBox();
		scene.topLabels = new HBox();

		Label lblName = new Label("Přezdívka:"),
				lblServer = new Label(String.format("%s:%d", settings.getServerAddress(), settings.getServerPort()));
		
		scene.topLabels.getChildren().add(lblName);
		scene.topLabels.getChildren().add(scene.tf_clientName);
		scene.topLabels.getChildren().add(lblServer);
		

		scene.topButtons.getChildren().add(scene.but_getLobbies);

		top.getChildren().add(scene.topLabels);
		top.getChildren().add(scene.topButtons);

		return top;
	}

	private static HBox createBottomBar(ServerListScene scene) {
		HBox bottom = new HBox();

		Button but_goBack = new Button("Zpět..");
		but_goBack.setOnAction((ActionEvent event) -> {
			scene.getStage().prevScene();
		});

		HBox bottomLabel = new HBox();
		bottomLabel.setAlignment(Pos.CENTER);
		bottomLabel.getChildren().add(scene.lbl_conInfo);

		bottom.getChildren().add(but_goBack);
		bottom.getChildren().add(bottomLabel);

		return bottom;
	}

	protected TextField tf_clientName;
	protected Button but_getLobbies,
			but_join;
	protected Label lbl_conInfo;

	protected ListView<GameRoom> serverList;
	protected ObservableList serverListItems;

	protected HBox topButtons,
			topLabels;

	public ServerListScene(Parent root, double width, double height) {
		super(root, width, height, "Join Game");
		root.setStyle("-fx-background-color: #" + Integer.toHexString(Color.BLUEVIOLET.hashCode()));

		serverListItems = FXCollections.observableArrayList();
	}

	@Override
	public Class getPrevScene() {
		return MainMenuScene.class;
	}

	private void refreshServerList() {
		serverListItems.clear();
		int n = Settings.getRandInt(12);
		String[] lobbies = new String[n];
		for (byte i = 0; i < n; i++) {
			int players = Settings.getRandInt(Settings.MAX_PLAYERS) + 1;
			byte flags = 0;
			if (i % 2 == 0) {
				flags |= GameRoom.FLAG_RUNNING;
			}
			if (i % 3 == 1) {
				flags |= GameRoom.FLAG_SPECTATABLE;
			}
			if (players == Settings.MAX_PLAYERS) {
				flags |= GameRoom.FLAG_FULL;
			}
			String s = String.format("%02X%02X%02X%02X", i, Settings.MAX_PLAYERS, players, flags);
			/*String s = Integer.toHexString(i) + Integer.toHexString(Settings.MAX_PLAYERS) +
			 Integer.toHexString(players) + Integer.toHexString(flags);*/
			System.out.format("ID=%d, MP=%d, CP=%d, F=%d\t%s%n", i, Settings.MAX_PLAYERS, players, flags, s);
			lobbies[i] = s;
		}
		parseAndInsertLobbies(lobbies);

		NetWorks.fetchLobbies(lobbyPasser);
	}

	private void parseAndInsertLobbies(String[] lobbies) {
		for (String l : lobbies) {
			GameRoom gr = GameRoom.fromString(l);
			if (gr == null) {
				continue;
			}
			serverListItems.add(gr);
		}
	}

	private void connectToGame(GameRoom gr) {
		System.out.println("Connection attempt to " + gr + " stopped.");
		if (true) {
			return;
		}
		try {
			String address = settings.getServerAddress(),
					clientName = tf_clientName.getText();
			int port = settings.getServerPort();
			System.out.format("Connecting to: %s:%d%n", address, port);

			NetWorks nw = NetWorks.connectTo(address, port, clientName);
			if (nw.canConnect()) {
				this.getStage().gotoLobby(nw);
			} else {
				this.lbl_conInfo.setText(nw.getStatusLabel());
			}
		} catch (IOException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}
