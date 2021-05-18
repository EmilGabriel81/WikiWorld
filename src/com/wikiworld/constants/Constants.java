package com.wikiworld.constants;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

public class Constants {
	/**
	 * A class that holds all the constants of the app
	 * Private Constructor
	 */
	private Constants() {
	}
	
	public static final int PANELWIDHT = 800;
	public static final int PANELHEIGHT = 600;
	public static final String README = "ReadMe";
	public static final Font FONTF = new Font("Helvetica", Font.BOLD, 16);
	public static final Font FONTTITLE = new Font("Helvetica", Font.BOLD, 24);
	public static final Font FONTREADME = new Font("Helvetica", Font.BOLD, 16);
	public static final Color mainColor = new Color(153, 255, 204);
	public static  final GradientPaint CUSTOMBACKGROUND = new 
	GradientPaint(0, 0, Color.YELLOW,Constants.PANELWIDHT/2,Constants.PANELHEIGHT/2+200,
			new Color(153, 255, 255));
	
			                               
	
}
