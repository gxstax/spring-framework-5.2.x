package com.ant.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * <p>
 * {@link Observer} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/26 5:31 下午
 */
public class ObserverDemo {
	public static void main(String[] args) {
		EventObservable observable = new EventObservable();

		// 添加观察者（其实相当于 Observable 组合了 Observers[] ）
		observable.addObserver(new EventObserver());

		// 发布消息
		observable.notifyObservers("Hello World");
	}

	// java 不成文的规定 可被观察者一般实现 Observable
	static class EventObservable extends Observable {
		@Override
		protected synchronized void setChanged() {
			super.setChanged();
		}

		@Override
		public void notifyObservers(Object arg) {
			setChanged();
			super.notifyObservers(new EventObject(arg));
			clearChanged();
		}
	}

	// java 不成文的规定 观察者一般实现 Observer
	static class EventObserver implements Observer, EventListener {

		@Override
		public void update(Observable o, Object event) {
			EventObject eventObject = (EventObject) event;
			System.out.println("收到事件：" + eventObject);
		}
	}
}
