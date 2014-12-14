package br.com.wpattern.observer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import br.com.wpattern.observer.interfaces.ISubject;
import br.com.wpattern.observer.observers.ConsoleObserver;
import br.com.wpattern.observer.observers.ListObserver;
import br.com.wpattern.observer.observers.PaneObserver;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -4581383685178290638L;

	private final ISubject subject;

	/**
	 * Create the application.
	 */
	public MainWindow(ISubject subject) {
		this.subject = subject;
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Observers");
		this.setResizable(false);
		this.setBounds(100, 100, 425, 68);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new MigLayout("", "[53px][115.00px][111px][87.00,grow]", "[30px]"));

		JLabel lblObserver = new JLabel(" Observers");
		this.getContentPane().add(lblObserver, "cell 0 0,alignx left,growy");

		JButton btnPane = new JButton("Pane");
		btnPane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PaneObserver pane = new PaneObserver();
				pane.setVisible(true);
				MainWindow.this.subject.addListener(pane);
			}
		});
		this.getContentPane().add(btnPane, "cell 1 0,grow");

		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListObserver list = new ListObserver();
				list.setVisible(true);
				MainWindow.this.subject.addListener(list);
			}
		});
		this.getContentPane().add(btnList, "cell 2 0,grow");

		JButton btnConsole = new JButton("Console");
		btnConsole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsoleObserver console = new ConsoleObserver();
				MainWindow.this.subject.addListener(console);
			}
		});
		this.getContentPane().add(btnConsole, "cell 3 0,growx");
	}

}
