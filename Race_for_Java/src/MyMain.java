import javax.swing.JFrame;

public class MyMain {

	public static void main(String[] args) {
		
		Road road = new Road();									//������ ������ ������
		
		JFrame frame = new JFrame("�����2D"); 					//������ �����.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //�������� ��������� �� ������� �� "�������" ������.
		frame.setSize(1215, 1000);								//����� ������ ������.
		frame.setLocationRelativeTo(null);						//���������� ���� �� �������� ����.
		
		frame.add(road);										//��������� ������ �� �����.
		
		frame.setVisible(true);									//������ ����� �������.
		
		
		
		
	}

}
