package Game;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Snake {
	
	ArrayList <Blocks> block = new ArrayList<Blocks>();
	
	Blocks head;
	Blocks tail;
	
	public Snake(int il, SnakeField f) {
		int ipx = f.getW() / 2;
		int ipy = f.getH() / 2;
		
		head = new Blocks(ipx, ipy, null, f);
		block.add(head);
		
		head.setFill(Color.BROWN.desaturate());
		
		tail = head;
		
		for(int i = 1; i < il; i++) {
			Blocks b = new Blocks(ipx + i, ipy, tail, f);
			block.add(b);
			tail = b;
		}
	}
	
	public int getDirection() {
		return head.direction;
	}
	
	public void setDirection(int d) {
		head.direction = d;
	}
	
	

}
