import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable {
	
	Timer mainTimer = new Timer(20, this);				   //Создаём таймер который будет запускать метод actionPerformed, каждые 20 милисекунд в объекте this.

	Image image = new ImageIcon("res/road.jpg").getImage();//Изображение дороги.
	
	CarPlayer p = new CarPlayer();						   //Создаём объект,  машину Player.
	
	Thread enemiesFactory = new Thread(this);
	
	ArrayList<CarEnemy> enemies = new ArrayList<CarEnemy>();
		
	public Road(){										   //Конструктор.
		mainTimer.start(); 								   //Запускаем Таймер в конструкторе.
		addKeyListener(new MyKeyAdaptor());				   //Регистрируем слушателя событий клавиш в конструкторе.
		setFocusable(true);								   //Устанавливаем фокус на дороге.
		enemiesFactory.start();
	}
	
	private class MyKeyAdaptor extends KeyAdapter{		   //Класс обработки нажатия клавиш (через класс Адаптор).
		public void keyPressed(KeyEvent e){				   //Обработка нажатия клавиш.
			p.keyPressed(e);							   //Вызываем метод из класса CarPlayer (Реализовывать его будем в классе CarPlayer).
		}
		public void keyReleased(KeyEvent e){			   //Обработка отпускания клавиши.
			p.keyReleased(e);							   //Вызываем метод из класса CarPlayer (Реализовывать его будем в классе CarPlayer).
		}
	}

	public void paintComponent (Graphics g){			 	//Переопределяем метод рисования.
		g = (Graphics2D) g;								 	//Приводим g к Graphics2D.
		g.drawImage(image, 0, (p.layer1), null);		   	//Отрисовываем изображение дороги (первый слой).
		g.drawImage(image, 0, (p.layer2), null);			//Отрисовываем изображение дороги (второй слой).
		g.drawImage(p.image, (p.x), p.y, null);				//Отрисовываем изображение машины.

		double speed = (200/CarPlayer.MAX_SPEED)*p.speed;
		g.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.BOLD, 14);
		g.setFont(font);
		g.drawString("Скорость: " + speed + " км/ч", 240, 950);
		g.drawString("Киллометраж: " + p.mileage + " px", 20, 950);
		
		Iterator<CarEnemy> i = enemies.iterator();
		while(i.hasNext()){
			CarEnemy e = i.next();
			if(e.y >= 3000 || e.y <= -3000 ){
				i.remove();
			}
			if(e.x <= 300 || e.x >= 900) {
				i.remove();
			
			}else{
				e.move();
				g.drawImage(e.image, e.x, e.y, null);
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) { 			//Метод который запускается каждые 20 милисекунд, запускается Таймером.
		p.move();											//Вызеваем метод ехать.
		repaint();											//Перерисовываем.
		testCollisionWithEnemies();
		testWin();
	}
	
	private void testWin() {
		if(p.mileage > 100000){
			JOptionPane.showMessageDialog(null, "Вы выиграли");
			System.exit(0);
		}
		
	}

	private void testCollisionWithEnemies(){
		Iterator<CarEnemy> i = enemies.iterator();
		while(i.hasNext()){
			CarEnemy e = i.next();
			if(p.getRect().intersects(e.getRect())){
				
				JOptionPane.showMessageDialog(null, "Вы проиграли");
				System.exit(1);
				
					
			}
			
		}
	}

	@Override
	public void run() {
		
		while(true){
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(100));
				enemies.add(new CarEnemy(rand.nextInt(1215), -1000, rand.nextInt(45), this));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
