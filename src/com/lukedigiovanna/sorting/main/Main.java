package com.lukedigiovanna.sorting.main;

import javax.swing.JFrame;

import com.lukedigiovanna.sorting.*;

import javax.sound.midi.*;

public class Main {
	public static void main(String[] args) {
//		Synthesizer synth;
//		try {
//			synth = MidiSystem.getSynthesizer();
//			Instrument[] instruments = synth.getAvailableInstruments();
//			for (Instrument i : instruments) {
//				System.out.println(i.getName());
//			}
//		} catch (MidiUnavailableException e) {
//			e.printStackTrace();
//		}
		
		
		JFrame frame = new JFrame("Sorting");
		frame.setSize(720,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.setVisible(true);
	}
}
