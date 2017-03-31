package com.winderton.app.events.types;

import com.winderton.app.events.Event;

public class MouseMotionEvent extends Event {
	
	private int x, y;
	private boolean dragged;

	public MouseMotionEvent(int x, int y, boolean dragged) {
		super(Event.Type.MOUSE_MOVED);
		this.x = x;
		this.y = y;
		this.dragged = dragged;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDragged() {
		return dragged;
	}

}
