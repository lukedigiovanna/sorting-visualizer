package com.lukedigiovanna.sorting.main;

import javax.swing.JFrame;

import com.lukedigiovanna.sorting.*;
import com.lukedigiovanna.sorting.audibilization.SoundPlayer;

import javax.sound.midi.*;

public class Main {
	public static void main(String[] args) {
		SoundPlayer.initialize();
		JFrame frame = new JFrame("Sorting");
		frame.setSize(720,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.setVisible(true);
	}
}
