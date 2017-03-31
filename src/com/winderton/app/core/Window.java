package com.winderton.app.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.winderton.app.events.Event;
import com.winderton.app.events.types.MouseMotionEvent;
import com.winderton.app.events.types.MousePressedEvent;
import com.winderton.app.events.types.MouseReleasedEvent;
import com.winderton.app.layers.Layer;

@SuppressWarnings("serial")
public class Window extends Canvas {
	
	private BufferStrategy bs;
	private Graphics g;
	private JFrame frame;
	private List<Layer> layers = new ArrayList<Layer>();
	
	
	public Window(String name, int width, int height) {
		setPreferredSize(new Dimension(width, height));
		init(name);
		
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
				onEvent(event);
			}
			
			public void mouseReleased(MouseEvent e) {
				MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
				onEvent(event);
			}
			
		});
		
		
		addMouseMotionListener(new MouseMotionListener() {
			

			public void mouseMoved(MouseEvent e) {
				MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), false);
				onEvent(event);
			}
			

			public void mouseDragged(MouseEvent e) {
				MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), true);
				onEvent(event);
				
			}
			
		});
		
		render();
	}
	
	private void init(String name) {
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void render() {
		if (bs == null)
			createBufferStrategy(3);
		bs = getBufferStrategy();
		
		g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		onRender(g);
		g.dispose();
		bs.show();
		
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
		}
		
		EventQueue.invokeLater(() -> render());
	}
	
	private void onRender(Graphics g) {
		for (int i = 0; i < layers.size(); i++)
			layers.get(i).onRender(g);
	}
	
	private void onEvent(Event event) {
		for (int i = layers.size() - 1; i >= 0 ; i--)
			layers.get(i).onEvent(event);
	}
	
	public void addLayer(Layer layer) {
		layers.add(layer);
	}
	
	
	
}
