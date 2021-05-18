package com.wikiworld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.ConstantCallSite;

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

public class CountryOptions extends JFrame {

	private CountryService countryService;
	private JPanel mainPanel;
	private JComboBox countryNamesCmb;
	private JLabel countryNamesLbl;
	private JLabel showLbl;
	private JLabel sortLbl;
	private JComboBox countryDetailsCmb;
	private JComboBox sortByCmb;
	private JPanel displayPanel;
	private JTextArea displayArea;
	private JButton showBtn;
	private JButton sortBtn;
	// Strings used for comparison
	private String countryName;
	private String countryShow;
	private String countrySort;

	public CountryOptions(CountryService countryService) {
		super("Countries");
		this.countryService = countryService;
		mainPanel = new JPanel();
		displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(600, 300));
		mainPanel.setLayout(null);
		mainPanel.setBackground(Constants.mainColor);

		// Country Names label-----------------------------------------
		countryNamesLbl = new JLabel("Countries :");
		countryNamesLbl.setFont(Constants.FONTF);
		countryNamesLbl.setBounds(100, 100, 150, 30);

		// Country Names ComboBox--------------------------------------
		countryNamesCmb = new JComboBox(countryService.getCountryName());
		countryNamesCmb.addActionListener(e -> {
			JComboBox cb1 = (JComboBox) e.getSource();
			setCountryName((String) cb1.getSelectedItem());
			System.out.println(countryName);
		});
		countryNamesCmb.setFont(Constants.FONTF);
		countryNamesCmb.setBounds(200, 100, 150, 30);

		// Show info label----------------------------------------
		showLbl = new JLabel("Show by :");
		showLbl.setFont(Constants.FONTF);
		showLbl.setBounds(100, 150, 150, 30);

		// Country Details ComboBox---------------------------------
		countryDetailsCmb = new JComboBox(new String[] { "Population", "Neighbours", "Capital", "Area" });
		countryDetailsCmb.addActionListener(e -> {
			JComboBox cb2 = (JComboBox) e.getSource();
			setCountryShow((String) cb2.getSelectedItem());
			System.out.println(countryShow);
		});
		countryDetailsCmb.setFont(Constants.FONTF);
		countryDetailsCmb.setBounds(200, 150, 150, 30);

		// Sort Label---------------------------------------------
		sortLbl = new JLabel("Sort all :");
		sortLbl.setFont(Constants.FONTF);
		sortLbl.setBounds(450, 100, 150, 30);

		// Sort by Combo Box ---------------------------------------
		sortByCmb = new JComboBox(new String[] { "Population", "Area" });
		sortByCmb.addActionListener(e -> {
			JComboBox cb3 = (JComboBox) e.getSource();
			setCountrySort((String) cb3.getSelectedItem());
			System.out.println(countrySort);
		});
		sortByCmb.setFont(Constants.FONTF);
		sortByCmb.setBounds(550, 100, 150, 30);

		// Show Button --------------------------------------------
		showBtn = new JButton("Show");
		showBtn.setBounds(200, 200, 150, 30);
		showBtn.addActionListener(e -> {
			clearTextArea();
			try {
				String result = countryService.getCountryinfo(this.getCountryName(), this.getCountryShow());
				appendCountryDetails(result);
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "Manually select your fields first");
			}
		});
		showBtn.setFont(Constants.FONTF);

		// Sort Button --------------------------------------------
		sortBtn = new JButton("Sort Countries");
		sortBtn.setBounds(550, 150, 150, 30);
		sortBtn.addActionListener(e -> {
			clearTextArea();
			try {
				String choice = getCountrySort();
				appendCountryDetails(countryService.sortedByChoice(choice));
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "Manually select your fields first");
			}

		});
		sortBtn.setFont(Constants.FONTF);

		// Display Text Area -------------------------------------
		displayArea = new JTextArea(13, 42);
		displayArea.setFont(new Font("Helvetica", Font.BOLD, 16));
		displayArea.setEditable(false);

		// Display text panel
		displayPanel.setBackground(Constants.mainColor);
		displayPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
		displayPanel.setBounds(100, 250, 600, 300);

		// Main Panel ---------------------------------------------

		mainPanel.add(sortBtn);
		mainPanel.add(showBtn);
		mainPanel.add(displayPanel);
		mainPanel.add(countryNamesLbl);
		mainPanel.add(countryNamesCmb);
		mainPanel.add(showLbl);
		mainPanel.add(countryDetailsCmb);
		mainPanel.add(sortLbl);
		mainPanel.add(sortByCmb);

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

	public void appendCountryDetails(String s) {
		if (s != null) {
			this.displayArea.append(s);
		}
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryShow() {
		return countryShow;
	}

	public void setCountryShow(String countryShow) {
		this.countryShow = countryShow;
	}

	public String getCountrySort() {
		return countrySort;
	}

	public void setCountrySort(String countrySort) {
		this.countrySort = countrySort;
	}
}
