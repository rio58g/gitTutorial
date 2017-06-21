import javax.swing.JFrame;

public class MyMain {

	public static void main(String[] args) {
		
		Road road = new Road();									//Создаём объект дороги
		
		JFrame frame = new JFrame("Гонка2D"); 					//Создаём фрейм.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Закрытие программы по нажатию на "крестик" фрейма.
		frame.setSize(1215, 1000);								//Задаём размер фрейма.
		frame.setLocationRelativeTo(null);						//Отображать фреём по середине окна.
		
		frame.add(road);										//Добавляем дорогу на фрейм.
		
		frame.setVisible(true);									//Делаем фрейм видимым.
		
		
		
		
	}

}
