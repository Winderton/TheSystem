package com.winderton.app;

import java.awt.Color;

import com.winderton.app.core.Window;
import com.winderton.app.sandbox.Example;

public class Main {

	public static void main(String[] args) {
		Window window = new Window("Window", 960, 640);
		window.addLayer(new Example("Layer-1", Color.BLUE));
		window.addLayer(new Example("Layer-2", Color.RED));
	}
	
}
