import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pokemon");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JLabel label = new JLabel("PokemonFighter");
		frame.add(label);
	}
}
