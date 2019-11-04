import Ant.AntPane;
import Color.StringMatchingPane;
import String.ColorMatchingPane;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;


public class Main extends Application
{
	TabPane tabPane = new TabPane();
	Tab tab = new Tab();
	
	Pane pane1 = new StringMatchingPane();
	Pane pane2 = new AntPane();
	Pane pane3 = new ColorMatchingPane();
	
	
	@Override
	public void start(Stage primaryStage)
	{
		TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("String Matching", pane1);
        Tab tab2 = new Tab("Ant GA"  , pane2);
        Tab tab3 = new Tab("Color Matching GA" , pane3);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.setTitle("GA Examples");

        primaryStage.show();
	}
	
	public static void main(String[] args) 
	{
		launch(args);	
	}
	

}
