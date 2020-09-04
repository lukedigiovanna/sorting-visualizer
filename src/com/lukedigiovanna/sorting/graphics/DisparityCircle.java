package com.lukedigiovanna.sorting.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lukedigiovanna.sorting.Array;

public class DisparityCircle implements Visualizer {
	@Override
	public void draw(BufferedImage screen, Array array, ColorLoop colorLoop) {
		Graphics2D g = screen.createGraphics();
		g.setStroke(new BasicStroke(2));
		Color[] fullLoop = new Color[colorLoop.loop.length + 1];
		for (int i = 0; i < colorLoop.loop.length; i++)
			fullLoop[i] = colorLoop.loop[i];
		fullLoop[colorLoop.loop.length] = colorLoop.loop[0]; 
		ColorLoop fullCircle = new ColorLoop(fullLoop,colorLoop.access);
		int radius = (int)(screen.getHeight() * 0.4);
		int cx = screen.getWidth()/2, cy = screen.getHeight()/2;
		for (int i = 0; i < array.size(); i++) {
			double percent = (double)i/array.size();
			double startAngle = Math.PI * 2 - percent * Math.PI * 2;
			g.setColor(GraphicsUtils.get(fullCircle, array, i));
			double offPercent = GraphicsUtils.percentOff(array, i);
			int smallRadius = (int)(offPercent * radius);
			g.drawLine(cx, cy, (int)(cx + smallRadius * Math.cos(startAngle)), (int)(cy + smallRadius * Math.sin(startAngle)));
		}
	}
}
