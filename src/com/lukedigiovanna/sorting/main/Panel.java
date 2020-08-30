package com.lukedigiovanna.sorting.main;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.lukedigiovanna.sorting.*;
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
	
	private JLabel title;
	private JLabel accessesLabel, comparisonsLabel;
	private JComboBox algorithmSelection, visualizerSelection, colorSelection;
	private JButton shuffleButton;
	private JLabel sliderLabel, arraySizeLabel;
	private JSlider speedSlider, arraySlider;
	
	private static final String[] 
			algorithms = new String[] {"Selection", "Insertion", "Merge", "Quick Sort", "Count", "Radix LSD (Count)", "Radix MSD (Count)"},
			visualizers = new String[] {"Bar Chart", "Color Circle"},
			colorPalettes = new String[] {"Rainbow", "White", "Gray", "RGB", "CYP"};
	
	public Panel() {
		screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		this.setLayout(null);
		
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
		
		accessesLabel = new JLabel("Array Accesses: 0");
		comparisonsLabel = new JLabel("Comparisons: 0");
		accessesLabel.setBounds(320, 5, 150, 20);
		comparisonsLabel.setBounds(475, 5, 150, 20);
		accessesLabel.setFont(font);
		comparisonsLabel.setFont(font);
		accessesLabel.setForeground(Color.WHITE);
		comparisonsLabel.setForeground(Color.WHITE);
		add(accessesLabel);
		add(comparisonsLabel);
		
		shuffleButton = new JButton("Run Algorithm");
		shuffleButton.setBounds(5, 30, 120, 20);
		shuffleButton.setFont(font);
		shuffleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runAlgorithm();
			}
		});
		add(shuffleButton);
		
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
		
		arraySlider = new JSlider(10, 1000, 500);
		arraySlider.setBounds(80, 82, 100, 20);
		arraySlider.setBackground(new Color(0,0,0,0));
		add(arraySlider);
		
		array = new Array(1000);
		
		Thread updateThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					int speedValue = speedSlider.getValue();
					double speedPercent = speedValue/100.0;
					double delay = (-0.005 * Math.log(speedPercent) + 0.0001);
					array.setDelay(delay);
					
					if (array.size() != arraySlider.getValue()) {
						array = new Array(arraySlider.getValue());
					}
					
					accessesLabel.setText("Array Accesses: "+array.getAccesses());
					comparisonsLabel.setText("Comparisons: "+array.getComparisons());
					
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
	private void runAlgorithm() {
		array.shuffle();
		Thread t = new Thread(new Runnable() {
			public void run() {
				Algorithm algorithm = null;
				switch (algorithmSelection.getSelectedItem().toString()) {
				case "Selection":
					algorithm = Algorithms.SELECTION;
					break;
				case "Insertion":
					algorithm = Algorithms.INSERTION;
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
				
				shuffleButton.setEnabled(false);
				shuffleButton.setText("running...");
				algorithmSelection.setEnabled(false);
				arraySlider.setEnabled(false);
				runningAlgorithm = true;
				
				array.resetMetrics();
				
				algorithm.sort(array);
				
				shuffleButton.setEnabled(true);
				shuffleButton.setText("Run Algorithm");
				algorithmSelection.setEnabled(true);
				arraySlider.setEnabled(true);
				runningAlgorithm = false;
			}
		});
		t.start();
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
		case "Color Circle":
			visualizer = Visualizers.COLOR_CIRCLE;
			break;
		}
		visualizer.draw(screen, array, loop);
		if (this.runningAlgorithm) {
		try {
		    // retrieve image
		    File outputfile = new File("merge/"+index+".png");
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
