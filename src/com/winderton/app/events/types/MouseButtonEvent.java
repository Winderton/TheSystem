package com.winderton.app.events.types;

import com.winderton.app.events.Event;

public class MouseButtonEvent extends Event {

	private int keyCode, x, y;
	
	protected MouseButtonEvent(Type type, int keyCode, int x, int y) {
		super(type);
		this.keyCode = keyCode;
		this.x =x;
		this.y=y;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
	
}
