package tunnelers.Game;

import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tunnelers.Game.CanvasLayouts.CanvasLayout;
import tunnelers.Game.structure.Container;
import tunnelers.Game.structure.Direction;

/**
 *
 * @author Stepan
 */
public class PlayScene extends AGameScene{
	
    public static PlayScene getInstance(Container c){
        return createInstance(c);
    }
    
    private static PlayScene createInstance(Container c){
        BorderPane root = new BorderPane();
        
        root.setStyle("-fx-background-color: #" + Integer.toHexString(Color.DIMGRAY.hashCode()));
        PlayScene scene = new PlayScene(root, settings.getWidth(), settings.getHeight());
        
        addComponents(root, scene);
        
        scene.setOnKeyPressed((KeyEvent e) -> {
            System.out.println("Playscene keypressed:" + e.getCode());
            scene.handleKeyPressed(e.getCode());
        });
        scene.setCanvasLayout(c);
        
        return scene;
        
    }

    protected TextArea ta_chatBox;
    protected TextField tf_chatIn;
    protected Canvas ca_drawArea;
    protected CanvasLayout canvasLayout;
    
    
    public PlayScene(Parent root, double width, double height) {
        super(root, width, height, "Battlefield");
        
    }
    
    private static void addComponents(BorderPane root, PlayScene scene){
        int chatWidth = 160;
        
        scene.ca_drawArea = new Canvas(settings.getWidth() - chatWidth, settings.getHeight());
        root.setCenter(scene.ca_drawArea);
        
        VBox vertical = new VBox();
        
        scene.ta_chatBox = new TextArea();
        scene.ta_chatBox.setWrapText(true);
        scene.ta_chatBox.setPrefWidth(chatWidth);
        scene.ta_chatBox.setPrefRowCount(10);
        scene.ta_chatBox.setEditable(false);
        scene.ta_chatBox.setBackground(Background.EMPTY);
        vertical.getChildren().add(scene.ta_chatBox);
        
        scene.tf_chatIn = new TextField();
        scene.tf_chatIn.setPrefWidth(chatWidth);
        scene.tf_chatIn.setDisable(true);
        vertical.getChildren().add(scene.tf_chatIn);
        
        //root.setRight(vertical);
        root.setOnMouseClicked((MouseEvent e) -> {
            scene.drawScene();
        });
    }
    
    private void setCanvasLayout(Container container){
        this.canvasLayout = CanvasLayout.choseIdeal(container, new Dimension2D(this.ca_drawArea.getWidth(), this.ca_drawArea.getHeight()));
    }
    
    @Override
    public void updateChatbox() {
        GameStage stage = this.getStage();
        this.ta_chatBox.setText(stage.getGamechat().getLog());
    }
    
    @Override
    public void drawScene(){
        GraphicsContext g = this.ca_drawArea.getGraphicsContext2D();
        this.canvasLayout.drawLayout(g);
    }
    
    public void handleKeyPressed(KeyCode code){
        System.out.println(code);
        switch(code){
            default: return;
            case UP:
                this.getStage().movePlayer(0, Direction.North);
            break;
                
            case LEFT:
                this.getStage().movePlayer(0, Direction.West);
            break;
                
            case RIGHT:
                this.getStage().movePlayer(0, Direction.East);
            break;
                
            case DOWN:
                this.getStage().movePlayer(0, Direction.South);
            break;
        }
        this.drawScene();
    }
    
}
