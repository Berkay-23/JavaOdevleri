package minesweeper;

public class Main  implements Runnable {
	
	//StartMenu startMenu = new StartMenu();
	GUI gui = new GUI();
	
	
	public static void main(String[] args) {
		
		new Thread (new Main()).start();
		
	}

	@Override
	public void run() {
		
		while (true) {
			gui.repaint();
			//startMenu.repaint();
			if(gui.resetter == false) {
				gui.checkVictoryStatus();
				//System.out.println("kazanma = " + gui.victory + "\t kaybetme = " + gui.defeat);
			}
		}
	}
}