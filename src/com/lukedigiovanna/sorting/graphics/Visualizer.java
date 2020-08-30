package com.lukedigiovanna.sorting.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.lukedigiovanna.sorting.Array;

public interface Visualizer {
	public void draw(BufferedImage screen, Array array, ColorLoop colorLoop);
}
