
 
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class MainClass extends Application {
	Player mediaPlayer;
	FileChooser mediaChooser;
	MenuBar menu;
	Menu fileMenu;
	MenuItem openItem;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 400);
        menu = new MenuBar();
        fileMenu = new Menu("File");
        openItem = new MenuItem("Open");
        mediaChooser = new FileChooser();
        
        fileMenu.getItems().add(openItem);
        menu.getMenus().add(fileMenu);
        openItem.setOnAction((e)->{
        
        try {
        	File mediaFile = mediaChooser.showOpenDialog(primaryStage);
        	if(mediaPlayer != null) {
        		mediaPlayer.player.dispose();
        	}
			mediaPlayer = new Player(mediaFile.toURI().toURL().toExternalForm());
			System.out.println(mediaFile.getAbsolutePath()+ "   gghkhkhhk  "+mediaFile.toURI().toURL().toExternalForm());
			mediaPlayer.view.setFitWidth(scene.getWidth());
			root.setCenter(mediaPlayer);
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Error occured in Playing");
		}
        
    });
        root.setTop(menu);
        primaryStage.widthProperty().addListener((obs, oldVal, newVal)->{
        	if(mediaPlayer != null)       		
        	mediaPlayer.view.setFitWidth(scene.getWidth());
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}