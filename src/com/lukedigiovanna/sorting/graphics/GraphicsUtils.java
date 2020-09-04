package com.lukedigiovanna.sorting.graphics;

import java.awt.Color;

import com.lukedigiovanna.sorting.Array;

public class GraphicsUtils {
	public static Color loop(Color[] colorLoop, double percent) {
		if (percent >= 1 || colorLoop.length < 2)
			return colorLoop[colorLoop.length-1];
		int f = (int)(percent * (colorLoop.length-1));
		int l = f + 1;
		double s = 1.0/(colorLoop.length-1);
		double p = (percent % s)/s;
		int dr = colorLoop[l].getRed() - colorLoop[f].getRed(), dg = colorLoop[l].getGreen() - colorLoop[f].getGreen(), db = colorLoop[l].getBlue() - colorLoop[f].getBlue();
		int r = colorLoop[f].getRed() + (int)(dr * p), g = colorLoop[f].getGreen() + (int)(dg * p), b = colorLoop[f].getBlue() + (int)(db * p);
		return new Color(r, g, b);
	}
	
	public static Color get(ColorLoop loop, Array array, int index) {
		double percentValue = (double)array.get()[index]/array.size();
		Color c = GraphicsUtils.loop(loop.loop, percentValue);
		if (array.isMostRecentAccess(index)) 
			c = loop.access;
		if (array.isSorted(index))
			c = Color.GREEN;
		return c;
	}
	
	public static double percentOff(Array array, int index) {
		int maxSeparation = array.size() - index;
		if (maxSeparation < array.size() / 2) 
			maxSeparation = array.size() - maxSeparation;
		double offPercent = 1.0-(double)Math.abs(array.get()[index] - index)/maxSeparation;
		return offPercent;
	}
}
