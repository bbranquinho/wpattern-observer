package br.com.wpattern.observer.observers;

import br.com.wpattern.observer.interfaces.IListener;

public class ConsoleObserver implements IListener {

	@Override
	public void onValue(int value) {
		System.out.println(String.format("OnValue: %s", value));
	}

	@Override
	public void onMessage(String message) {
		System.out.println(String.format("OnMessage: %s", message));
	}

	@Override
	public void onError(String error) {
		System.out.println(String.format("OnError: %s", error));
	}

}
