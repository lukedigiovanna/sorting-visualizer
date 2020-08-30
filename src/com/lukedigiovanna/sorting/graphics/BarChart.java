package com.lukedigiovanna.sorting.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lukedigiovanna.sorting.Array;

public class BarChart implements Visualizer {
	@Override
	public void draw(BufferedImage screen, Array array, ColorLoop colorLoop) {
		Graphics2D g = screen.createGraphics();
		double barWidth = (double)screen.getWidth()/array.size();
		for (int i = 0; i < array.size(); i++) {
			double percentValue = (double)array.get()[i]/array.size();
			g.setColor(GraphicsUtils.get(colorLoop, array, i));
			double barHeight = (int)(percentValue*screen.getHeight());
			double x = barWidth * i;
			g.fillRect((int)x, (int)(screen.getHeight()-barHeight), (int)barWidth+1, (int)barHeight);
		}
	}
}
