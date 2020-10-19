package Game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeFood extends Rectangle {
	
	int posX, posY;
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public SnakeFood(int x, int y) {
		super(UserInterface.blockSize, UserInterface.blockSize);
		
		posX = x;
		posY = y;
		
		setTranslateX(posX * UserInterface.blockSize);
		setTranslateY(posY * UserInterface.blockSize);
		
		setFill(Color.LIGHTGREEN);
		setStroke(Color.GREEN);

		
	}

}


