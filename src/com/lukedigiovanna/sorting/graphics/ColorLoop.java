package com.lukedigiovanna.sorting.graphics;

import java.awt.Color;

public class ColorLoop {
	public static ColorLoop 
		RAINBOW = new ColorLoop(new Color[] {Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.MAGENTA}, Color.WHITE),
		WHITE = new ColorLoop(new Color[] {Color.WHITE}, Color.RED),
		GRAY = new ColorLoop(new Color[] {Color.WHITE,Color.BLACK}, Color.RED),
		RGB = new ColorLoop(new Color[] {Color.RED,Color.GREEN,Color.BLUE}, Color.WHITE),
		CYP = new ColorLoop(new Color[] {Color.CYAN, Color.YELLOW, Color.PINK}, Color.RED);
	
	public Color[] loop;
	public Color access;
	
	public ColorLoop(Color[] loopArray, Color accessColor) {
		this.loop = loopArray;
		this.access = accessColor;
	}
}
