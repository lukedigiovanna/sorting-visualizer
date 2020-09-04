package com.lukedigiovanna.sorting.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lukedigiovanna.sorting.Array;

public class ScatterPlot implements Visualizer {

	@Override
	public void draw(BufferedImage screen, Array array, ColorLoop colorLoop) {
		Graphics2D g = screen.createGraphics();
		double barWidth = (double)screen.getWidth()/array.size();
		for (int i = 0; i < array.size(); i++) {
			double percentValue = (double)array.get()[i]/array.size();
			g.setColor(GraphicsUtils.get(colorLoop, array, i));
			double dotHeight = (int)(percentValue*screen.getHeight());
			double x = barWidth * i;
			g.fillOval((int)x, (int)(screen.getHeight()-dotHeight), 10, 10);
		}
	}

}
