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

public class ContinentOptions extends JFrame {

	private CountryService countryService;
	private JPanel mainPanel;
	private JComboBox continentsNamesCmb;
	private JLabel continentsNamesLbl;
	private JButton showBtn;
	private JPanel displayPanel;
	private JTextArea displayArea;
	private JLabel sortLbl;
	private JComboBox sortByCmb;
	private JButton sortBtn;
	// Strings used for comparing
	private String continentName;
	private String countrySort;

	public ContinentOptions(CountryService countryService) {
		super("Continents");
		this.countryService = countryService;
		mainPanel = new JPanel();
		displayPanel = new JPanel();
		showBtn = new JButton("Show");
		displayPanel.setPreferredSize(new Dimension(600, 300));
		mainPanel.setLayout(null);
		mainPanel.setBackground(Constants.mainColor);

		// Country Names label-----------------------------------------
		continentsNamesLbl = new JLabel("Continents :");
		continentsNamesLbl.setFont(Constants.FONTF);
		continentsNamesLbl.setBounds(100, 100, 150, 30);

		// Country Names ComboBox--------------------------------------
		continentsNamesCmb = new JComboBox(new String[] { "Africa", "Americas", "Asia", "Europe", "Oceania" });
		continentsNamesCmb.addActionListener(e -> {
			JComboBox cb1 = (JComboBox) e.getSource();
			setContinentName((String) cb1.getSelectedItem());
			System.out.println(continentName);
		});
		continentsNamesCmb.setFont(Constants.FONTF);
		continentsNamesCmb.setBounds(200, 100, 150, 30);

		// Show Button ---------------------------------------------------
		showBtn.setBounds(200, 150, 150, 30);
		// showBtn.setBounds(550, 100, 150, 30);
		showBtn.addActionListener(e -> {
			clearTextArea();
			try {
				if (continentName == null) {
					JOptionPane.showMessageDialog(this, "Manually select your fields first");
				}
				appendContinentDetails(countryService.showContinents(getContinentName()));
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "Manually select your fields first");
			}
		});
		showBtn.setFont(Constants.FONTF);

		// Sort Label---------------------------------------------
		sortLbl = new JLabel("Sort by :");
		sortLbl.setFont(Constants.FONTF);
		sortLbl.setBounds(450, 100, 150, 30);

		// Sort by Combo Box ---------------------------------------
		sortByCmb = new JComboBox(new String[] { "Population", "Area" });
		sortByCmb.addActionListener(e -> {
			JComboBox cb2 = (JComboBox) e.getSource();
			setCountrySort((String) cb2.getSelectedItem());
			System.out.println(countrySort);
		});
		sortByCmb.setFont(Constants.FONTF);
		sortByCmb.setBounds(550, 100, 150, 30);
		// Sort Button --------------------------------------------
		sortBtn = new JButton("Sort Countries");
		sortBtn.setBounds(550, 150, 150, 30);
		sortBtn.addActionListener(e -> {
			clearTextArea();
			try {
				String choice = getCountrySort();
				appendContinentDetails(countryService.sortedByChoice(choice));
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

		mainPanel.add(displayPanel);
		mainPanel.add(continentsNamesLbl);
		mainPanel.add(continentsNamesCmb);
		mainPanel.add(showBtn);
		mainPanel.add(sortBtn);
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

	public void appendContinentDetails(String s) {
		if (s != null) {
			this.displayArea.append(s);
		}
	}

	public String getCountrySort() {
		return countrySort;
	}

	public void setCountrySort(String countrySort) {
		this.countrySort = countrySort;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

}
