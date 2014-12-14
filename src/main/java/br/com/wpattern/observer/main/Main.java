package br.com.wpattern.observer.main;

import java.io.FileNotFoundException;

import br.com.wpattern.observer.FileSubject;
import br.com.wpattern.observer.gui.MainWindow;

public class Main {

	private static final String FILE_PATH = "src\\main\\resources\\text.txt";

	private static final int PERIOD = 1000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSubject subject = null;

		try {
			subject = new FileSubject(PERIOD, FILE_PATH);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		MainWindow mainWindow = new MainWindow(subject);
		mainWindow.setVisible(true);
	}

}
