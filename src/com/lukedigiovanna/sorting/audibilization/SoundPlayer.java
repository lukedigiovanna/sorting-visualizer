package com.lukedigiovanna.sorting.audibilization;

import javax.sound.midi.*;

import com.lukedigiovanna.sorting.Array;

public class SoundPlayer {
	private static Synthesizer synth;
	private static MidiChannel channel;
	
	public static void initialize() {
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		channel = synth.getChannels()[0];
		
		int index = 0;
		Instrument[] instruments = synth.getAvailableInstruments();
		for (int i = 0; i < instruments.length; i++) {
			System.out.println(i+": "+instruments[i].getName());
			if (instruments[i].getName().contentEquals("Saw Wave"));
				index = i;
		}
		channel.programChange(instruments[4].getPatch().getProgram());
	}
	
	private static boolean enabled = true;
	public static void setEnabled(boolean e) {
		enabled = e;
	}
	
	public static void play(int value, int maxValue) {
		if (!enabled) return;
		int note = (int)((double)value/maxValue*108);
		channel.noteOn(note, 25);
	}
	
	public static void play(Array array, int index) {
		play(array.get()[index], array.size());
	}
	
	public static void halt() {
		channel.allNotesOff();
	}
}
