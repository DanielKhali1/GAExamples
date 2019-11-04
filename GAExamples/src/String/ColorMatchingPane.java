package String;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.Node;

public class ColorMatchingPane extends Pane 
{
	
	
	GridPane grid = new GridPane();
	realNumberGA ga = new realNumberGA(500, 0.01, 16777215);
	ColorPicker colorPicker = new ColorPicker();
	TextField mut = new TextField(ga.mutationRate + "");
	
	public ColorMatchingPane()
	{
		
		grid = new GridPane();
		
		int index = 0;
		for(int i = 0; i < 25; i++)
		{
			for(int j =0; j < 20; j++)
			{
				Button tempbut = new Button(" ");
				tempbut.setPrefSize(8, 5);
				int rand = ga.population[index];
				String hexVal = Integer.toHexString(rand);
				while(hexVal.length() < 6)
				{
					hexVal += "0";
				}
				tempbut.setStyle("-fx-background-color: '#"+ hexVal + "'; -fx-background-radius: 0");
				grid.add(tempbut, i, j);
				index ++;
			}
		}
		getChildren().add(grid);

		colorPicker.setLayoutX(20);
		colorPicker.setLayoutY(20);
		getChildren().add(colorPicker);
		
		mut.setLayoutX(20);
		mut.setLayoutY(60);
		mut.setPrefWidth(75);
		getChildren().add(mut);
		
		Button create = new Button("Create");
		create.setLayoutX(20);
		create.setLayoutY(90);
		getChildren().add(create);
		
		create.setOnAction(e->{
			reset();
		});
		
		Button play = new Button("Start");
		play.setLayoutX(20);
		play.setLayoutY(120);
		getChildren().add(play);
		
		Button stop = new Button("Stop");
		stop.setLayoutX(20);
		stop.setLayoutY(150);
		getChildren().add(stop);
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);

		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(40) , new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent arg0) 
			{
				step();
			}
			
		}));
		
		play.setOnAction(e->{
			timeline.play();

		});
		
		stop.setOnAction(e->{
			timeline.stop();
		});
		

	}
	
	public void reset()
	{
		
		System.out.println( (Integer.toHexString(colorPicker.getValue().hashCode())).substring(0, 6));
		System.out.println(Integer.parseInt( (Integer.toHexString(colorPicker.getValue().hashCode()) ).substring(0, 6), 16));
		ga = new realNumberGA(500, Double.parseDouble(mut.getText()), Integer.parseInt( (Integer.toHexString(colorPicker.getValue().hashCode()) ).substring(0, 6), 16) );
		int index = 0;
		for(Node cell : grid.getChildren())
		{
			
				int rand = ga.population[index];
				String hexVal = Integer.toHexString(rand);
				while(hexVal.length() < 6)
				{
					hexVal += "0";
				}
				cell.setStyle("-fx-background-color: '#"+ hexVal + "'; -fx-background-radius: 0");
				index ++;
		}
	}
	
	public void step()
	{
		ga.Evolve();
		int index = 0;
		for(Node cell : grid.getChildren())
		{
			
				int rand = ga.population[index];
				String hexVal = Integer.toHexString(rand);
				while(hexVal.length() < 6)
				{
					hexVal += "0";
				}
				cell.setStyle("-fx-background-color: '#"+ hexVal + "'; -fx-background-radius: 0");
				index ++;
		}
	}

}
