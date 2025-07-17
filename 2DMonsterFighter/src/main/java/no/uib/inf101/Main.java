package no.uib.inf101;

import javax.swing.JFrame;

import no.uib.inf101.view.GameView;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Game");

		GameView view = new GameView();
		window.add(view);

		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		view.startGameThread();
	}
}
