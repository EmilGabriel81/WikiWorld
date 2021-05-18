package com.wikiworld.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wikiworld.constants.Constants;
import com.wikiworld.controller.CountryService;

public class MainPanel extends JPanel {

	private CountryService countryService;
	private JButton listOfCountriesBtn;
	private JButton showContinentsBtn;
	private JButton showComparisonBtn;
	private JButton readMeBtn;

	public MainPanel() {

		countryService = new CountryService();
		listOfCountriesBtn = new JButton("Countries");
		listOfCountriesBtn.setFont(Constants.FONTF);
		listOfCountriesBtn.setFocusable(false);
		showContinentsBtn = new JButton("Continents");
		showContinentsBtn.setFont(Constants.FONTF);
		showContinentsBtn.setFocusable(false);
		showComparisonBtn = new JButton("Compare");
		showComparisonBtn.setFont(Constants.FONTF);
		showComparisonBtn.setFocusable(false);
		readMeBtn = new JButton("ReadMe");
		readMeBtn.setFont(Constants.FONTF);
		readMeBtn.setFocusable(false);
		//ActionListeners with lambdas
		showComparisonBtn.addActionListener(e -> {
			new CompareOptions(countryService);
		});
		listOfCountriesBtn.addActionListener(e -> {
			new CountryOptions(countryService);
		});
		showContinentsBtn.addActionListener(e -> {
			new ContinentOptions(countryService);
		});
		readMeBtn.addActionListener(e -> {
			JFrame jf = new JFrame("ReadMe");
			jf.add(new InstructionsPanel(countryService));
			jf.pack();
			jf.setResizable(false);
			jf.setLocationRelativeTo(this);
			jf.setVisible(true);

		});

		this.setLayout(null);
		this.add(listOfCountriesBtn);
		this.add(showContinentsBtn);
		this.add(showComparisonBtn);
		this.add(readMeBtn);

		listOfCountriesBtn.setBounds(325, 50, 150, 30);
		showContinentsBtn.setBounds(325, 100, 150, 30);
		showComparisonBtn.setBounds(325, 150, 150, 30);
		readMeBtn.setBounds(325, 200, 150, 30);
		this.setPreferredSize(new Dimension(Constants.PANELWIDHT, Constants.PANELHEIGHT));
		this.setVisible(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Constants.CUSTOMBACKGROUND);
		g2d.fillRect(0, 0, 912, 762);
	}

}
