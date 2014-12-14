package br.com.wpattern.observer.observers;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import br.com.wpattern.observer.interfaces.IListener;

public class ListObserver extends JFrame implements IListener {

	private static final long serialVersionUID = -4971794202865719497L;

	private DefaultListModel model;

	/**
	 * Create the frame.
	 */
	public ListObserver() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(5, 5));
		this.setSize(600, 350);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.model = new DefaultListModel();
		JList list = new JList(this.model);
		scrollPane.setViewportView(list);
		this.getContentPane().add(scrollPane);
	}

	@Override
	public void onValue(int value) {
		this.model.addElement(String.format("OnValue: [%s]", value));
	}

	@Override
	public void onMessage(String message) {
		this.model.addElement(String.format("OnMessage: [%s]", message));
	}

	@Override
	public void onError(String error) {
		// Do nothing.
	}

}
