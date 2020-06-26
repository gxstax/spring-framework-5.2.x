package com.ant.event;

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

		// 添加观察者
		observable.addObserver(new EventObserver());

		// 发布消息
		observable.notifyObservers("Hello World");
	}

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

	static class EventObserver implements Observer {

		@Override
		public void update(Observable o, Object event) {
			System.out.println("收到事件：" + event);
		}
	}
}
