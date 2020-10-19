package Game;

import javafx.scene.shape.Rectangle;

public class Blocks extends Rectangle {
	
	static final int Up = 0, Right = 1, Down = 2, Left = 3;
	
	int posX, posY, oldPosX, oldPosY;
	
	Blocks previous;
	
	int direction = Left;
	
	int maxX, maxY;
	
	public Blocks(int x, int y, Blocks p, SnakeField f) {
		super(UserInterface.blockSize, UserInterface.blockSize);
		posX = x;
		posY = y;
		
		setTranslateX(posX * UserInterface.blockSize);
		setTranslateY(posY * UserInterface.blockSize);
		
		previous = p;
		
		maxX = f.getW();
		maxY = f.getH();
	}
	
	public void update() {
		oldPosX = posX;
		oldPosY = posY;
		
		if(previous == null) {
			switch(direction) {
			case Up: 
				moveUp(); 
				break;
			
			case Right: 
				moveRight(); 
				break;
			
			case Down: 
				moveDown(); 
				break;
			
			case Left: 
				moveLeft(); 
				break;
			}
		} else {
			posX = previous.oldPosX;
			posY = previous.oldPosY;
		}
		updatePosition();
	}
	
	public void moveUp() {
		posY--;
		if (posY < 0) {
			posY = maxY - 1;
		}
	}
	
	public void moveDown() {
		posY++;
		if (posY >= maxY) {
			posY = 0;
		}
	}
	
	public void moveLeft() {
		posX--;
		if (posX < 0) {
			posX = maxX - 4;
		}
	}
	
	public void moveRight() {
		posX++;
		if (posX >= maxX) {
			posX = 0;
		}
	}

	public void updatePosition() {
		setTranslateX(posX * UserInterface.blockSize);
		setTranslateY(posY * UserInterface.blockSize);
	}
	
	
	
	
	
	
	
	
}

