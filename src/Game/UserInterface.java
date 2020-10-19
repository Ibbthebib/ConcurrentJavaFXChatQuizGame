package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserInterface extends Application {
	
	static int blockSize = 20;
	
	int width = 30, height = 20;
	
	int il = 5;
	
	long then = System.nanoTime();
	
	boolean changed = false;
	int nextUpdate;
	boolean hasNext = false;
	
	SnakeField f;

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		
		f = new SnakeField(width, height);
		f.addSnake(new Snake(il, f));
		
		Label score = new Label("Score: 0");
		score.setFont(Font.font("calibri", 32));
		
		
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				if (now - then > 1000000000 / 6) {
					f.update();
					then = now;
					score.setText("Score: " + f.score);
					changed = false;
					
					if(hasNext) {
						setDirection(f.snake, nextUpdate);
						hasNext = false;
					}
					
					if (f.isDead()) {
						stop();
						
						Alert al = new Alert(AlertType.INFORMATION);
						al.setHeaderText("Dead Snake");
						al.setContentText("Your Score Is " + f.score);
						Platform.runLater(al :: showAndWait);
						
						al.setOnHidden(e -> {
							root.getChildren().clear();
							f = new SnakeField(width, height);
							f.addSnake(new Snake(il, f));
							score.setText("Score: 0");
							root.getChildren().addAll(f, score);
							
							start();
						});
						
					}
				}

			}
		};
		
		timer.start();
		
		root.getChildren().addAll(f, score);
		
		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.UP) && f.snake.getDirection() != Blocks.Down) {
				setDirection(f.snake, Blocks.Up);
			}
			if (e.getCode().equals(KeyCode.DOWN) && f.snake.getDirection() != Blocks.Up) {
				setDirection(f.snake, Blocks.Down);
			}
			if (e.getCode().equals(KeyCode.RIGHT) && f.snake.getDirection() != Blocks.Left) {
				setDirection(f.snake, Blocks.Right);
			}
			if (e.getCode().equals(KeyCode.LEFT) && f.snake.getDirection() != Blocks.Right) {
				setDirection(f.snake, Blocks.Left);
			}
		});
		
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Snake Game");
		
		primaryStage.show();
	}
	
	public void setDirection(Snake s, int d) {
		if (! changed) {
			s.setDirection(d);
			changed = true;
		} else {
			hasNext = true;
			nextUpdate = d;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

}


