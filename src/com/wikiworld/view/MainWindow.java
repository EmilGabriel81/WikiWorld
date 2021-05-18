package com.wikiworld.view;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	public MainWindow() {
		initGui();
	}

	private void initGui() {
		add(new MainPanel());
		setTitle("Wiki World");
		pack();// JFrame will be as large as the JPanel(GameMainPanel)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		// setSize(600,400);
		setVisible(true);

	}
}
