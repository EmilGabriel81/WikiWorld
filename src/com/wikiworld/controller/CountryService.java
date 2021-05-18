package com.wikiworld.controller;

import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.util.*;

import com.wikiworld.model.Country;
import com.wikiworld.model.WorldData;

public class CountryService {

	/**
	 * The class that acts as a controller of the app takes as a source the data
	 * from the WorlData class
	 * 
	 */

	private WorldData worldData;
	private List<String> readmeList;

	public CountryService() {
		worldData = new WorldData();
		readmeList = new ArrayList<String>();
		initCountries();
		initReadMe();

	}

	public List<Country> getCountries() {
		return worldData.getWorldCountries();
	}

	public List<String> getReadmeList() {
		return readmeList;
	}

	public String getCountryinfo(String name, String show) {
		StringBuilder sBuilder = new StringBuilder();
		Country country = worldData.getCountryByName(name);
		sBuilder.append("Country Name : " + country.getName() + "\n");
		switch (show) {
		case "Population":
			sBuilder.append(" Population : " + country.getPopulation() + "\n");
			break;
		case "Neighbours":
			sBuilder.append(" Neighbours : " + country.getNeighbours().toString() + "\n");
			break;
		case "Capital":
			sBuilder.append(" Capital : " + country.getCapital() + "\n");
			break;
		case "Area":
			sBuilder.append(" Area : " + country.getArea() + "\n");
			break;
		default:
			break;
		}
		return sBuilder.toString();
	}

	public String[] getCountryName() {
		// returnes only the name of each country
		int size = getCountries().size();
		String[] cNames = new String[size];
		for (int i = 0; i < size; i++) {
			cNames[i] = getCountries().get(i).getName();
		}
		return cNames;
	}

	public void initCountries() {
		// The parsing is being made here
		Country country;
		try (BufferedReader bReader = new BufferedReader(new FileReader("myFiles/countries2.txt"))) {
			String line = null;
			while ((line = bReader.readLine()) != null) {
				country = prepareCountry(line);// we send the line String to this method
				this.worldData.addCountry(country);
			}
		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
	}

	private Country prepareCountry(String line) {

		String[] countryDetails = line.split("\\|");
		List<String> neighbours = new ArrayList<String>();
		if (countryDetails.length > 5) {
			neighbours = prepareNeighbours(countryDetails[5]);
			// very important. if the country has any neighbours we pass
			// the string from index 5 to another method that returns a List of Strings with
			// the neighbours
		}
		String name = countryDetails[0];
		String capital = countryDetails[1];
		long population = Long.parseLong(countryDetails[2]);
		long area = Long.parseLong(countryDetails[3]);
		String continent = countryDetails[4];
		return new Country(name, capital, population, area, continent, neighbours);

	}

	private List<String> prepareNeighbours(String line) {
		// we get the neighbours and put them into a List
		String[] neighbours = line.split("~");
		List<String> countryNeighbours = new ArrayList<String>();
		List<String> theList = Arrays.asList(neighbours);
		return theList;
	}

	public String sortedByChoice(String s) {
		// We sort according to the string of choice
		StringBuilder sb = new StringBuilder();
		Country[] arr = sortByChoice(s);
		for (int i = 0; i < arr.length; i++) {
			if (s.equals("Population")) {
				sb.append(arr[i].getName() + " " + arr[i].getPopulation() + "\n");
			}
			if (s.equals("Area")) {
				sb.append(arr[i].getName() + " is " + arr[i].getArea() + " km² \n");
			}
		}
		return sb.toString();
	}

	public String showContinents(String continent) {
		StringBuilder sb = new StringBuilder();
		List<Country> cList = listCountriesbyContinents(continent);// the method down bellow
		for (int i = 0; i < cList.size(); i++) {
			sb.append(cList.get(i).getName() + " is in " + continent + "\n");
		}
		return sb.toString();
	}

	public List<Country> listCountriesbyContinents(String continent) {
		List<Country> countriesByContinents = new ArrayList<Country>();
		Country c = null;
		Iterator<Country> countryIterator = getCountries().iterator();
		while (countryIterator.hasNext()) {
			c = countryIterator.next();
			if (c.getContinent().equals(continent)) {
				countriesByContinents.add(c);
			}
		}
		return countriesByContinents;// returns a list of counytries by continents
	}

	public String compareCountriesBy(String s1, String s2, String choice) {
		String str = "";
		Country c1 = null;
		Country c2 = null;

		for (Country c : getCountries()) {
			if (c.getName().equals(s1)) {
				c1 = c;
			}
			if (c.getName().equals(s2)) {
				c2 = c;
			}
		}

		if (choice.equals("Population")) {
			str += c1.getName() + "`s population is " + c1.getPopulation() + ", " + c2.getName() + "`s population is "
					+ c2.getPopulation();
		}
		if (choice.equals("Area")) {
			str += c1.getName() + "`s Area is " + c1.getArea() + ", " + c2.getName() + "`s Area is " + c2.getArea();
		}

		return str;
	}

	private Country[] sortByChoice(String choice) {
		// Bubble sort used, slower(ON2) but good for practice
		List<Country> sortedList = new ArrayList<Country>();
		sortedList.addAll(worldData.getWorldCountries());
		int n = sortedList.size();
		Country[] sortedArray = new Country[n];
		for (int i = 0; i < n; i++) {
			sortedArray[i] = sortedList.get(i);
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (choice.equals("Population")) {
					if (sortedArray[j].getPopulation() < sortedArray[j + 1].getPopulation()) {
						// swap arr[j+1] and arr[j]
						Country temp = sortedArray[j];
						sortedArray[j] = sortedArray[j + 1];
						sortedArray[j + 1] = temp;
					}
				}
				if (choice.equals("Area")) {
					if (sortedArray[j].getArea() < sortedArray[j + 1].getArea()) {
						// swap arr[j+1] and arr[j]
						Country temp = sortedArray[j];
						sortedArray[j] = sortedArray[j + 1];
						sortedArray[j + 1] = temp;
					}
				}
			}
		}
		return sortedArray;
	}

	public void initReadMe() {
		// We feed the list from the readme file
		try (BufferedReader bReader = new BufferedReader(new FileReader("myFiles/ReadMeFile.txt"))) {
			String line = null;
			while ((line = bReader.readLine()) != null) {
				readmeList.add(line);
			}
		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
	}

	// Alternate paths
	// -------------------------------------------------------------------------------------

	/**
	 * 
	 * @param choice
	 * @returns a sorted list but instead of bubble sort we use the methods from
	 *          Collections Feel free to keep it or delete it
	 * 
	 *          public List<Country>sortedWithCompare(String choice){ List<Country>
	 *          sortedList = new ArrayList<Country>();
	 *          sortedList.addAll(worldData.getWorldCountries()); if
	 *          (choice.equals("Population")) { Collections.sort(sortedList , new
	 *          Comparator<Country>() {
	 * 
	 * @Override public int compare(Country c1, Country c2) { // TODO Auto-generated
	 *           method stub if(c1.getPopulation() > c2.getPopulation()) return 1;
	 *           if(c2.getPopulation() < c2.getPopulation())return -1; return 0; }
	 *           }); }// if by population if (choice.equals("Area")) {
	 *           Collections.sort(sortedList , new Comparator<Country>() {
	 * 
	 * @Override public int compare(Country c1, Country c2) { // TODO Auto-generated
	 *           method stub if(c1.getArea() > c2.getArea()) return 1;
	 *           if(c2.getArea() < c2.getArea())return -1; return 0; } }); } return
	 *           sortedList; }
	 */

}
