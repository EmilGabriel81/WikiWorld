package com.wikiworld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.wikiworld.constants.Constants;
import com.wikiworld.controller.CountryService;

public class CompareOptions extends JFrame {

	private CountryService countryService;
	private JPanel mainPanel;
	private JComboBox countryNamesCmb1;
	private JComboBox countryNamesCmb2;
	private JComboBox compareByCmb;
	private JLabel countryNamesLbl1;
	private JLabel countryNamesLbl2;
	private JPanel displayPanel;
	private JTextArea displayArea;
	private JButton showCompareBtn;
	private JLabel compareLbl;
	// The strings used for comparing
	private String countryName1;
	private String countryName2;
	private String compareBy;

	public CompareOptions(CountryService countryService) {
		super("Compare Countries");
		this.countryService = countryService;
		mainPanel = new JPanel();
		displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(600, 300));
		mainPanel.setLayout(null);
		mainPanel.setBackground(Constants.mainColor);
		// Country Names label 1-----------------------------------------
		countryNamesLbl1 = new JLabel("Country 1 :");
		countryNamesLbl1.setFont(Constants.FONTF);
		countryNamesLbl1.setBounds(100, 100, 150, 30);

		// Country Names ComboBox--------------------------------------
		countryNamesCmb1 = new JComboBox(countryService.getCountryName());
		countryNamesCmb1.addActionListener(e -> {
			JComboBox cb1 = (JComboBox) e.getSource();
			setCountryName1((String) cb1.getSelectedItem());
			System.out.println(countryName1);
		});
		countryNamesCmb1.setFont(Constants.FONTF);
		countryNamesCmb1.setBounds(200, 100, 150, 30);

		// Show info label----------------------------------------
		countryNamesLbl2 = new JLabel("Country 2 :");
		countryNamesLbl2.setFont(Constants.FONTF);
		countryNamesLbl2.setBounds(100, 150, 150, 30);

		// Country Details ComboBox---------------------------------
		countryNamesCmb2 = new JComboBox(countryService.getCountryName());
		countryNamesCmb2.addActionListener(e -> {
			JComboBox cb2 = (JComboBox) e.getSource();
			setCountryName2((String) cb2.getSelectedItem());
			System.out.println(countryName2);
		});
		countryNamesCmb2.setFont(Constants.FONTF);
		countryNamesCmb2.setBounds(200, 150, 150, 30);

		// Show Button --------------------------------------------
		showCompareBtn = new JButton("Show");
		showCompareBtn.setBounds(200, 200, 150, 30);
		showCompareBtn.addActionListener(e -> {
			try {
				clearTextArea();
				System.out.println(getCountryName1());
				System.out.println(getCountryName2());
				System.out.println(getCompareBy());
				String result = countryService.compareCountriesBy(getCountryName1(), getCountryName2(), getCompareBy());
				appendCompareDetails(result);
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "Manually select all fields first");
				// ex.printStackTrace();
			}
		});
		showCompareBtn.setFont(Constants.FONTF);

		// Sort Label---------------------------------------------
		compareLbl = new JLabel("Compare by :");
		compareLbl.setFont(Constants.FONTF);
		compareLbl.setBounds(440, 100, 150, 30);

		// Sort by Combo Box ---------------------------------------
		compareByCmb = new JComboBox(new String[] { "Population", "Area" });
		compareByCmb.addActionListener(e -> {
			JComboBox cb3 = (JComboBox) e.getSource();
			setCompareBy((String) cb3.getSelectedItem());
			System.out.println(compareBy);
		});
		compareByCmb.setFont(Constants.FONTF);
		compareByCmb.setBounds(550, 100, 150, 30);

		// Display Text Area -------------------------------------
		displayArea = new JTextArea(13, 42);
		displayArea.setFont(new Font("Helvetica", Font.BOLD, 16));
		displayArea.setEditable(false);
		// Display text panel
		displayPanel.setBackground(Constants.mainColor);
		displayPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
		displayPanel.setBounds(100, 250, 600, 300);

		mainPanel.add(countryNamesLbl1);
		mainPanel.add(countryNamesCmb1);
		mainPanel.add(countryNamesLbl2);
		mainPanel.add(countryNamesCmb2);
		mainPanel.add(showCompareBtn);
		mainPanel.add(compareLbl);
		mainPanel.add(compareByCmb);
		mainPanel.add(displayPanel);

		this.add(mainPanel);
		mainPanel.setPreferredSize(new Dimension(Constants.PANELWIDHT, Constants.PANELHEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	// Getters and Setters

	public void clearTextArea() {
		this.displayArea.setText("");
	}

	public void appendCompareDetails(String s) {
		if (s != null) {
			this.displayArea.append(s);
		}
	}

	public void setCountryName1(String countryName1) {
		this.countryName1 = countryName1;
	}

	public void setCountryName2(String countryName2) {
		this.countryName2 = countryName2;
	}

	public void setCompareBy(String compareBy) {
		this.compareBy = compareBy;
	}

	public String getCountryName1() {
		return countryName1;
	}

	public String getCountryName2() {
		return countryName2;
	}

	public String getCompareBy() {
		return compareBy;
	}

}
