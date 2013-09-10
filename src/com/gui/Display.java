package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;

import com.mechanics.Processor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JProgressBar;

public class Display extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	private static JTextArea wordDisplay;
	private static JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
					frame.setResizable(false);
					Processor.setWordReference();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(100, 5, 325, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterLetters = new JLabel("Enter Letters:");
		lblEnterLetters.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnterLetters.setBounds(10, 5, 90, 20);
		contentPane.add(lblEnterLetters);
		
		JButton btnGetWords = new JButton("Get Words!");
		btnGetWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Processor.process();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGetWords.setBounds(10, 28, 110, 25);
		contentPane.add(btnGetWords);
		
		JLabel lblSortingMethod = new JLabel("Sorting Method:");
		lblSortingMethod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSortingMethod.setBounds(130, 28, 90, 22);
		contentPane.add(lblSortingMethod);
		
		JButton btnHighLow = new JButton("High -> Low");
		btnHighLow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Processor.sortAndDisplay(Processor.possibleWords.toArray(new String[Processor.possibleWords.size()]), "HIGH_LOW");
			}
		});
		btnHighLow.setBounds(223, 28, 100, 25);
		contentPane.add(btnHighLow);
		
		JButton btnLowHigh = new JButton("Low -> High");
		btnLowHigh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Processor.sortAndDisplay(Processor.possibleWords.toArray(new String[Processor.possibleWords.size()]), "LOW_HIGH");
			}
		});
		btnLowHigh.setBounds(325, 28, 100, 25);
		contentPane.add(btnLowHigh);
		
		wordDisplay = new JTextArea(1, 1);
		wordDisplay.setWrapStyleWord(true);
		wordDisplay.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(wordDisplay);
		scrollPane.setBounds(10, 64, 415, 190);
		contentPane.add(scrollPane);
		
		/*
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 265, 415, 15);
		contentPane.add(progressBar);
		*/
	}
	
	public static char[] getLetters(){
		char[] letters = textField.getText().toCharArray();
		return letters;
	}
	
	public static void displayWords(String[] words){
		wordDisplay.setText(null);
		for(int i = 0; i < words.length; i++){
			wordDisplay.append(words[i] + ": " + words[i].length() + "\n");
		}
	}
	
	/*
	public static void setProgressBar(int progress){
		progressBar.setValue(progress);
		progressBar.setStringPainted(true);
	}
	*/
}
