package com.lukedigiovanna.sorting.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lukedigiovanna.sorting.Array;

public class ColorCircle implements Visualizer {
	@Override
	public void draw(BufferedImage screen, Array array, ColorLoop colorLoop) {
		Graphics2D g = screen.createGraphics();
		Color[] fullLoop = new Color[colorLoop.loop.length + 1];
		for (int i = 0; i < colorLoop.loop.length; i++)
			fullLoop[i] = colorLoop.loop[i];
		fullLoop[colorLoop.loop.length] = colorLoop.loop[0]; 
		ColorLoop fullCircle = new ColorLoop(fullLoop,colorLoop.access);
		double arcAngle = Math.PI * 2 / array.size();
		int radius = (int)(screen.getHeight() * 0.4);
		int cx = screen.getWidth()/2, cy = screen.getHeight()/2;
		for (int i = 0; i < array.size(); i++) {
			double percent = (double)i/array.size();
			double startAngle = percent * Math.PI * 2;
			g.setColor(GraphicsUtils.get(fullCircle, array, i));
			g.fillArc(cx - radius, cy - radius, radius * 2, radius * 2, (int)Math.toDegrees(startAngle), (int)Math.toDegrees(arcAngle) + 1);
		}
	}
}
