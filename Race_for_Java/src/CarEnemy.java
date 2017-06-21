import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class CarEnemy {

	int x;
	int y;
	int speed;
	Image image = new ImageIcon("res/car_enemy_centr.png").getImage();
	Road road;
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 100, 219);
		
	}
	
	public CarEnemy(int x, int y, int speed, Road road){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.road = road;
	}
	public void move(){
		y = y + road.p.speed - speed;
	}
}
