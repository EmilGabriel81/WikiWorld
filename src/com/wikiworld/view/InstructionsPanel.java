package com.wikiworld.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wikiworld.constants.Constants;
import com.wikiworld.controller.CountryService;

public class InstructionsPanel extends JPanel{
/**
 * The class in charged with drawing the read me panel
 * pretty straight forward
 */
	private CountryService countryService;
	private List<String> drawList;
	
	public InstructionsPanel(CountryService countryService) {
		this.countryService = countryService;
		drawList = new ArrayList<String>();
		drawList.addAll(countryService.getReadmeList());	
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Constants.PANELWIDHT, Constants.PANELHEIGHT));
		this.setVisible(true);	
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 20;
		int y = 150;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Constants.CUSTOMBACKGROUND);
		g2d.fillRect(0, 0, 912, 762);
		g2d.setColor(Color.BLUE);
		g2d.setFont(Constants.FONTTITLE);
		g2d.drawString(Constants.README, 360, 100);
		g2d.setFont(Constants.FONTREADME);
		for (int i = 0 ; i < drawList.size(); i++){
			g2d.drawString(drawList.get(i), x, y);
			y += 30;
		}
		
	}
	
	
}
