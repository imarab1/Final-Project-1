import java.awt.Desktop;
import java.awt.Desktop.*;
import java.io.*;
import java.net.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.util.*;

public class Demo extends Application {
	public static boolean flag = true;
	public static MediaPlayer mediaPlayer;//Prevent GC from killing media
	@Override
	public void start(Stage stage) {
		//Creating image view files
		ImageView fileView = new ImageView("open-iconic-master/png/folder-2x.png");
		ImageView saveView = new ImageView("open-iconic-master/png/file-2x.png");
		ImageView exitView = new ImageView("open-iconic-master/png/x-2x.png");
		ImageView helpView = new ImageView("open-iconic-master/png/question-mark-2x.png");
		ImageView aboutView= new ImageView("open-iconic-master/png/info-2x.png");
		
		ImageView photoView= new ImageView("open-iconic-master/png/camera-slr-8x.png");
		photoView.setX(40);
		photoView.setY(50);
		photoView.setFitWidth(200);
		photoView.setPreserveRatio(true);
		
		Text photoText = new Text();
		photoText.setFont(new Font(20));
		photoText.setText("Select an Image.");
		photoText.setX(20);
		photoText.setY(45);
		
		Text keyText = new Text();
		keyText.setFont(new Font(20));
		keyText.setText("Press ANY key:");
		keyText.setX(350);
		keyText.setY(45);
		
		Text keyText2 = new Text();
		keyText2.setFont(new Font(20));
		keyText2.setText("");
		keyText2.setX(500);
		keyText2.setY(45);
		
		Text attribution = new Text();
		attribution.setFont(new Font(20));
		attribution.setText("Water Music Suite in F major (Air)\nGeorge Frideric Handel, CC BY-SA 3.0 \n<https://creativecommons.org/licenses/by-sa/3.0>,\n via Wikimedia Commons");
		attribution.setX(20);
		attribution.setY(325);
		
		TextField textField = new TextField();
		textField.setLayoutX(350);
		textField.setLayoutY(70);
		textField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent keyEvent){
				keyText2.setText(keyEvent.getCharacter());
			}
		});

		//Creating menus
		Menu fileMenu = new Menu("File");
		Menu helpMenu = new Menu("Help");

		//Creating menu Items
		MenuItem fileItem = new MenuItem("Open File", fileView);
		MenuItem saveItem = new MenuItem("Save file", saveView);
		MenuItem exitItem = new MenuItem("Exit", exitView);
		MenuItem helpItem = new MenuItem("Help", helpView);
		MenuItem aboutItem= new MenuItem("About", aboutView);

		//Adding all the menu items to the menus
		fileMenu.getItems().addAll(fileItem, saveItem, exitItem);
		helpMenu.getItems().addAll(helpItem, aboutItem);

		//Creating a menu bar and adding menu to it.
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, helpMenu);
		VBox vBox = new VBox(menuBar);
		vBox.prefWidthProperty().bind(stage.widthProperty());
				
		//Setting actions for menu items
		exitItem.setOnAction((ActionEvent t) -> {
			System.exit(0);
		});
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		fileItem.setOnAction((ActionEvent t) -> {
			try{
					File file = fileChooser.showOpenDialog(stage);
					InputStream input = new FileInputStream(file);
					photoView.setImage(new Image(input));
				} catch(FileNotFoundException fnfe){
					fnfe.printStackTrace();
				} catch(NullPointerException npe){
					// Cancled file open.
				}
			});
		
		helpItem.setOnAction((ActionEvent t) -> {
			String url = "http://www.google.com";
			if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)){
				Desktop desktop = Desktop.getDesktop();
				try{
					desktop.browse(new URI(url));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}else{
				Runtime runtime = Runtime.getRuntime();
				try{
					runtime.exec("xdg-open " + url);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		aboutItem.setOnAction((ActionEvent t) -> {
			Alert alert = new Alert(AlertType.INFORMATION, "This is demo code.");
			alert.setTitle("About");
			alert.setHeaderText("Demo code");
			alert.show();
		});
		
		//Setting up Circle
		Circle circle = new Circle();
		circle.setCenterX(350);
		circle.setCenterY(300);
		circle.setRadius(25);
		circle.setFill(Color.ORCHID);
		circle.setStrokeWidth(20);
		
		circle.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e){
				if(flag){
					circle.setFill(Color.INDIGO);
				} else{
					circle.setFill(Color.ORCHID);
				}
				flag = !flag;
			}
		});
		
		//Setting up the Path
		Path path = new Path();
		MoveTo moveTo = new MoveTo(350, 300);
		LineTo line1 = new LineTo(550, 450);
		LineTo line2 = new LineTo(300, 450);
		LineTo line3 = new LineTo(350, 300);
		path.getElements().addAll(moveTo, line1, line2, line3);
		
		PathTransition pathTransition  = new PathTransition();
		pathTransition.setNode(circle);
		pathTransition.setPath(path);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(10);
		pathTransition.setAutoReverse(false);
		pathTransition.setRate(0.5);
		
		//Setup buttons
		Button play = new Button("Play");
		play.setLayoutX(350);
		play.setLayoutY(400);
		
		
		Button stop = new Button("Stop");
		stop.setLayoutX(425);
		stop.setLayoutY(400);
		stop.setDisable(true);
		stop.setOnAction((ActionEvent t) -> {
				pathTransition.stop();
				stop.setDisable(true);
				play.setDisable(false);
		});
		play.setOnAction((ActionEvent t) -> {
			pathTransition.play();
			play.setDisable(true);
			stop.setDisable(false);
		});
		pathTransition.setOnFinished((ActionEvent t) -> {
			play.setDisable(false);
			stop.setDisable(true);
		});
		
		//Setting the stage
		Group root = new Group(vBox, photoView, photoText, keyText, keyText2, attribution, textField, circle, play, stop);
		Scene scene = new Scene(root, 600, 500, Color.LAVENDER);
		stage.setTitle("GUI Demo Code");
		stage.setScene(scene);
		stage.getIcons().add(new Image("open-iconic-master/png/aperture-2x.png"));
		stage.show();
		
		//Add music
		try{
			String musicFile = "Handel_Water_Music_Air.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		launch(args);
	}
}

/* All the cites
 * https://useiconic.com/open
 * https://www.tutorialspoint.com/javafx/javafx_quick_guide.htm
 * https://www.tutorialspoint.com/javafx-example-to-set-action-behavior-to-the-menu-item
 * http://tutorials.jenkov.com/javafx/menubar.html
 * https://www.tutorialspoint.com/how-to-create-a-menubar-in-javafx
 * https://www.geeksforgeeks.org/javafx-menubar-and-menu/
 * https://stackoverflow.com/questions/9408244/how-to-set-vbox-size-to-window-size-in-javafx
 * http://www.java2s.com/Code/Java/JDK-6/UsingtheDesktopclasstolaunchaURLwithdefaultbrowser.htm
 * https://www.geeksforgeeks.org/javafx-alert-with-examples/
 * https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
 * https://stackoverflow.com/questions/10121991/javafx-application-icon
 * https://examples.javacodegeeks.com/desktop-java/javafx/javafx-input-event-example/
 * https://examples.javacodegeeks.com/desktop-java/javafx/javafx-input-event-example/
 * https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
 * https://stackoverflow.com/questions/23202272/how-to-play-sounds-with-javafx
 * https://stackoverflow.com/questions/29870368/javafx-mediaplayer-music-stops-after-10-seconds
 * https://commons.wikimedia.org/wiki/File:5-George_Frideric_Handel_-_Water_Music_Suite_in_F_major_(Air)_HWV348.ogg
 */
