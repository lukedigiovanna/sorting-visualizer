package com.lukedigiovanna.sorting.main;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.swing.*;

import com.lukedigiovanna.sorting.*;
import com.lukedigiovanna.sorting.audibilization.SoundPlayer;
import com.lukedigiovanna.sorting.graphics.BarChart;
import com.lukedigiovanna.sorting.graphics.ColorLoop;
import com.lukedigiovanna.sorting.graphics.Visualizer;
import com.lukedigiovanna.sorting.graphics.Visualizers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel {
	public static final int WIDTH = 1080, HEIGHT = 720;
	
	private BufferedImage screen;
	
	private Array array;
	
	private JLabel infoLabel;
	private JComboBox algorithmSelection, visualizerSelection, colorSelection;
	private JButton shuffleButton;
	private JCheckBox startReversed, audibilize; 
	private JLabel reversedLabel, audibilizeLabel;
	private JLabel sliderLabel, arraySizeLabel;
	private JSlider speedSlider, arraySlider;
	
	private static final String[] 
			algorithms = new String[] {"Selection", "Heap", "Insertion", "Shell", "Bubble", "Comb", "Cocktail Shaker", "Merge", "Quick Sort", "Count", "Radix LSD (Count)", "Radix MSD (Count)"},
			visualizers = new String[] {"Bar Chart", "Scatter Plot", "Color Circle", "Disparity Circle", "Disparity Dots", "Dot Spiral"},
			colorPalettes = new String[] {"Rainbow", "White", "Gray", "RGB", "CYP"};
	
	public Panel() {
		screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		this.setLayout(null);
		
		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
		    MidiChannel chan[] = synth.getChannels(); 
		    // Check for null; maybe not all 16 channels exist.
		    if (chan[4] != null) {
		         chan[4].noteOn(60, 93); 
		    }
			} catch (Exception e) {}
		
		Font font = new Font("Courier", Font.BOLD, 10);
		algorithmSelection = new JComboBox(algorithms);
		algorithmSelection.setBounds(5, 5, 100, 20);
		algorithmSelection.setFont(font);
		add(algorithmSelection);
		
		visualizerSelection = new JComboBox(visualizers);
		visualizerSelection.setBounds(110, 5, 100, 20);
		visualizerSelection.setFont(font);
		add(visualizerSelection);
		
		colorSelection = new JComboBox(colorPalettes);
		colorSelection.setBounds(215, 5, 100, 20);
		colorSelection.setFont(font);
		add(colorSelection);
		
		infoLabel = new JLabel("Array Accesses: 0 Comparisons: 0 Array Size: 1000");
		infoLabel.setBounds(320, 5, 500, 20);
		infoLabel.setFont(font);;
		infoLabel.setForeground(Color.WHITE);
		add(infoLabel);
		
		shuffleButton = new JButton("Run Algorithm");
		shuffleButton.setBounds(5, 30, 120, 20);
		shuffleButton.setFont(font);
		shuffleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shuffleButton.getText().equals("Run Algorithm"))
					runAlgorithm();
				else
					cancelAlgorithm();
			}
		});
		add(shuffleButton);
		
		startReversed = new JCheckBox();
		startReversed.setBounds(130, 30, 20, 20);
		startReversed.setBackground(new Color(0,0,0,0));
		add(startReversed);
		
		reversedLabel = new JLabel("Start Reversed?");
		reversedLabel.setBounds(155, 30, 100, 20);
		reversedLabel.setFont(font);
		reversedLabel.setForeground(Color.WHITE);
		add(reversedLabel);
		
		audibilize = new JCheckBox("",true);
		audibilize.setBounds(260, 30, 20, 20);
		audibilize.setBackground(new Color(0,0,0,0));
		add(audibilize);
		
		audibilizeLabel = new JLabel("Audibilize?");
		audibilizeLabel.setBounds(285, 30, 150, 20);
		audibilizeLabel.setFont(font);
		audibilizeLabel.setForeground(Color.WHITE);
		add(audibilizeLabel);
		
		sliderLabel = new JLabel("Sort Speed: ");
		sliderLabel.setFont(font);
		sliderLabel.setBounds(5, 55, 80, 20);
		sliderLabel.setForeground(Color.WHITE);
		add(sliderLabel);
		
		speedSlider = new JSlider(1, 100, 50);
		speedSlider.setBounds(80, 57, 100, 20);
		speedSlider.setBackground(new Color(0,0,0,0));
		add(speedSlider);
		
		arraySizeLabel = new JLabel("Array Size: ");
		arraySizeLabel.setFont(font);
		arraySizeLabel.setBounds(5, 80, 80, 20);
		arraySizeLabel.setForeground(Color.WHITE);
		add(arraySizeLabel);
		
		arraySlider = new JSlider(50, 2000, 1000);
		arraySlider.setBounds(80, 82, 100, 20);
		arraySlider.setBackground(new Color(0,0,0,0));
		add(arraySlider);
		
		array = new Array(1000);
		
		Thread updateThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					int speedValue = speedSlider.getValue();
					double speedPercent = speedValue/100.0;
					double delay = (-0.005 * Math.log(speedPercent) + 0.00005);
					array.setDelay(delay);
					
					if (array.size() != arraySlider.getValue()) {
						array = new Array(arraySlider.getValue());
					}
					
					SoundPlayer.setEnabled(audibilize.isSelected());
					
					infoLabel.setText("Array Accesses: "+array.getAccesses()+" Comparisons: "+array.getComparisons()+" Array Size: "+array.size());
					
					draw();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						
					}
				}
			}
		});
		updateThread.start();
	}
	
	private boolean runningAlgorithm = false;
	Thread algorithmThread;
	private void runAlgorithm() {
		algorithmThread = new Thread(new Runnable() {
			public void run() {
				if (startReversed.isSelected())
					array.reverse();
				else
					array.shuffle();
				Algorithm algorithm = null;
				switch (algorithmSelection.getSelectedItem().toString()) {
				case "Selection":
					algorithm = Algorithms.SELECTION;
					break;
				case "Heap":
					algorithm = Algorithms.HEAP;
					break;
				case "Insertion":
					algorithm = Algorithms.INSERTION;
					break;
				case "Shell":
					algorithm = Algorithms.SHELL;
					break;
				case "Bubble":
					algorithm = Algorithms.BUBBLE;
					break;
				case "Comb":
					algorithm = Algorithms.COMB;
					break;
				case "Cocktail Shaker":
					algorithm = Algorithms.COCKTAIL_SHAKER;
					break;
				case "Merge":
					algorithm = Algorithms.MERGE;
					break;
				case "Quick Sort":
					algorithm = Algorithms.QUICK_SORT;
					break;
				case "Count":
					algorithm = Algorithms.COUNT;
					break;
				case "Radix LSD (Count)":
					algorithm = Algorithms.RADIX_LSD;
					break;
				case "Radix MSD (Count)":
					algorithm = Algorithms.RADIX_MSD;
					break;
				}
				
				shuffleButton.setText("Cancel");
				algorithmSelection.setEnabled(false);
				arraySlider.setEnabled(false);
				runningAlgorithm = true;
				
				array.resetMetrics();
				
				algorithm.sort(array);
				
				array.test();
				
				shuffleButton.setText("Run Algorithm");
				algorithmSelection.setEnabled(true);
				arraySlider.setEnabled(true);
				runningAlgorithm = false;
			}
		});
		algorithmThread.start();
	}
	
	public void cancelAlgorithm() {
		algorithmThread.stop(); // although this is deprecated, this is a small application, so hopefully it won't cause too many problems
		
		shuffleButton.setText("Run Algorithm");
		algorithmSelection.setEnabled(true);
		arraySlider.setEnabled(true);
		runningAlgorithm = false;
	}
	
	int index = 0;
	public void draw() {
		Graphics2D g = screen.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		ColorLoop loop;
		switch (colorSelection.getSelectedItem().toString()) {
		case "Rainbow":
			loop = ColorLoop.RAINBOW;
			break;
		case "White":
			loop = ColorLoop.WHITE;
			break;
		case "Gray":
			loop = ColorLoop.GRAY;
			break;
		case "RGB":
			loop = ColorLoop.RGB;
			break;
		case "CYP":
			loop = ColorLoop.CYP;
			break;
		default:
			loop = ColorLoop.WHITE;
		}
		
		Visualizer visualizer = Visualizers.BAR_CHART;
		switch (visualizerSelection.getSelectedItem().toString()) {
		case "Bar Chart":
			visualizer = Visualizers.BAR_CHART;
			break;
		case "Scatter Plot":
			visualizer = Visualizers.SCATTER_PLOT;
			break;
		case "Color Circle":
			visualizer = Visualizers.COLOR_CIRCLE;
			break;
		case "Disparity Circle":
			visualizer = Visualizers.DISPARITY_CIRCLE;
			break;
		case "Disparity Dots":
			visualizer = Visualizers.DISPARITY_DOTS;
			break;
		case "Dot Spiral":
			visualizer = Visualizers.DOT_SPIRAL;
		}
		visualizer.draw(screen, array, loop);
		if (this.runningAlgorithm) {
		try {
		    // retrieve image
		    File outputfile = new File("latest_sort/"+index+".png");
		    index++;
		    ImageIO.write(screen, "png", outputfile);
		} catch (IOException e) {
		    
		}
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);
	}
}
