package Color;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StringMatchingPane extends Pane 
{
	StringMatchingGA ga = new StringMatchingGA(13, 0.03, "match me", false);
	TextField inv[] = new TextField[ga.population.length];
	TextField fit[] = new TextField[ga.population.length];
	TextField gen = new TextField(ga.generation + "");

	
	public StringMatchingPane()
	{
		Button runBt = new Button("Next Generation");
		runBt.setLayoutX(80);
		runBt.setLayoutY(20);
		getChildren().add(runBt);
		
		
		TextField word = new TextField(ga.word);
		word.setLayoutX(200);
		word.setLayoutY(20);
		word.setPrefWidth(100);
		getChildren().add(word);
		
		TextField mut = new TextField(ga.mutationRate + "");
		mut.setLayoutX(310);
		mut.setLayoutY(20);
		mut.setPrefWidth(75);
		getChildren().add(mut);
		
		gen.setLayoutX(395);
		gen.setLayoutY(20);
		gen.setPrefWidth(50);
		getChildren().add(gen);
		
		Button create = new Button("Create");
		create.setLayoutX(20);
		create.setLayoutY(20);
		getChildren().add(create);
		
		Button play = new Button("Auto Gen");
		play.setLayoutX(350);
		play.setLayoutY(100);
		getChildren().add(play);
		
		Button stop = new Button("Stop");
		stop.setLayoutX(350);
		stop.setLayoutY(140);
		getChildren().add(stop);
		
		create.setOnAction(e->{
			
			gen.setText(ga.generation+"");

			for(int i = 0; i < inv.length; i++)
			{
				ga = new StringMatchingGA(13, Double.parseDouble(mut.getText()), word.getText(), false);

				
				inv[i].setText(ga.population[i]);
				fit[i].setText(ga.fitness(ga.population[i]) + "");
			}
		});
		
		
		
		for(int i = 0; i < inv.length; i++)
		{
			TextField tempInd = new TextField(ga.population[i]);
			tempInd.setLayoutY(30* i + 60);
			tempInd.setLayoutX(20);
			inv[i] = tempInd;
			getChildren().add(inv[i]);
			
			TextField tempFit = new TextField( ga.fitness(ga.population[i]) + "");
			tempFit.setLayoutY(30* i + 60);
			tempFit.setLayoutX(250);
			fit[i] = tempFit;
			fit[i].setPrefWidth(40);
			getChildren().add(fit[i]);
		}


		
		runBt.setOnAction(e->
		{
			updateTFs();
		});
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);

		
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(35) , new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent arg0) 
			{
				updateTFs();
			}
			
		}));
		
		play.setOnAction(e->{
			timeline.play();

		});
		
		stop.setOnAction(e->{
			timeline.stop();
		});
		

	}
	
	public void updateTFs()
	{
		ga.Evolve();
		
		for(int i = 0; i < inv.length; i++)
		{
			inv[i].setText(ga.population[i]);
			fit[i].setText(ga.fitness(ga.population[i]) + "");
		}
		gen.setText(ga.generation+"");

	}
	
}
