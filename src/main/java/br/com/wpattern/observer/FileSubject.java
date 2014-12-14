package br.com.wpattern.observer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import br.com.wpattern.observer.interfaces.IListener;
import br.com.wpattern.observer.interfaces.ISubject;

public class FileSubject implements ISubject {

	private static final String MESSAGE_HEADER = "message";

	private static final String VALUE_HEADER = "value";

	private static final String DELIMITER = ":";

	private List<IListener> listeners = new ArrayList<IListener>();
	private Timer timer;
	private FileInputStream file;
	private DataInputStream in;
	private BufferedReader buffer;

	public FileSubject(int period, String filePath) throws FileNotFoundException {
		this.file = new FileInputStream(filePath);
		this.in = new DataInputStream(this.file);
		this.buffer = new BufferedReader(new InputStreamReader(this.in));
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new FileTimerTask(), 0, period);
	}

	@Override
	public void addListener(IListener listener) {
		this.listeners.add(listener);
	}

	private void notifyListeners(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text, DELIMITER);
		String errorMessage = null;

		try {
			String header = tokenizer.nextToken().toLowerCase();
			String value = tokenizer.nextToken();

			if (header.startsWith(MESSAGE_HEADER)) {
				for (IListener listener : this.listeners) {
					listener.onMessage(text);
				}
				return;
			}

			if (header.startsWith(VALUE_HEADER)) {
				for (IListener listener : this.listeners) {
					listener.onValue(Integer.valueOf(value));
				}
				return;
			}

			errorMessage = String.format("Unknown message type [%s].", header);
		} catch(Exception e) {
			errorMessage = e.getMessage();
		}

		if (errorMessage != null) {
			for (IListener listener : this.listeners) {
				listener.onError(errorMessage);
			}
		}
	}

	private class FileTimerTask extends TimerTask {

		@Override
		public void run() {
			String line = null;

			try {
				line = FileSubject.this.buffer.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			if (line == null) {
				FileSubject.this.timer.cancel();
			} else {
				FileSubject.this.notifyListeners(line);
			}
		}

	}

}
