package com.lukedigiovanna.sorting.main;

import javax.swing.JFrame;

import com.lukedigiovanna.sorting.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sorting");
		frame.setSize(720,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.setVisible(true);
	}
}
