package com.wikiworld.view;

import java.awt.EventQueue;
import java.util.List;


import com.wikiworld.controller.CountryService;
import com.wikiworld.model.Country;

public class TestApp {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new MainWindow();
			}
		});
		CountryService cService = new CountryService();
	}
}
