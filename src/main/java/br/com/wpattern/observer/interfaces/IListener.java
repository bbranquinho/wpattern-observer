package br.com.wpattern.observer.interfaces;

public interface IListener {

	void onValue(int value);

	void onMessage(String message);

	void onError(String error);

}
