package br.com.wpattern.observer.observers;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import br.com.wpattern.observer.interfaces.IListener;

public class PaneObserver extends JFrame implements IListener {

	private static final long serialVersionUID = -6349012590639030432L;
	private JTextPane textPane;

	/**
	 * Create the frame.
	 */
	public PaneObserver() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(5, 5));
		this.setSize(500, 350);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.textPane = new JTextPane();
		scrollPane.setViewportView(this.textPane);
		this.getContentPane().add(scrollPane);
	}

	@Override
	public void onValue(int value) {
		this.textPane.setText(String.format("%s\nValue: %s", this.textPane.getText(), value));
	}

	@Override
	public void onMessage(String message) {
		this.textPane.setText(String.format("%s\nMessage: %s", this.textPane.getText(), message));
	}

	@Override
	public void onError(String error) {
		// Do nothing.
	}

}
