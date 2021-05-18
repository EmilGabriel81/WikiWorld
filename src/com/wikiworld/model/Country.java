package com.wikiworld.model;

import java.util.*;

public class Country implements Comparable<Object> {

	private final String name, capital, continent;
	private final long area, population;
	private final List<String> neighbours = new ArrayList<String>();

	public Country(String name, String capital, long population, long area, String continent, List<String> neighbours) {

		this.name = name;
		this.capital = capital;
		this.population = population;
		this.area = area;
		this.continent = continent;
		this.neighbours.addAll(neighbours);

	}

	public List<String> getNeighbours() {
		return neighbours;
	}

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}

	public long getPopulation() {
		return population;
	}

	public long getArea() {
		return area;
	}

	public String getContinent() {
		return continent;
	}

	public String toString() {

		String country = " Name " + name + "\tCapital " + capital + "\tPopulation " + population + "\tArea " + area
				+ "\tContinent " + continent + "\tNeighbours" + neighbours + "\n";
		return country;
	}

	@Override
	public boolean equals(Object obj) {

		Country country = (Country) obj;

		if (this == country) {
			return true;
		}
		if (country == null || this.getClass() != country.getClass()) {
			return false;
		}

		return this.getName().equalsIgnoreCase(country.getName())
				&& this.getCapital().equalsIgnoreCase(country.getCapital())
				&& this.getContinent().equalsIgnoreCase(country.getContinent())
				&& this.getPopulation() == country.getPopulation() && this.getArea() == country.getArea();

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	

	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		Country country = (Country) obj;
		if (this.getPopulation() > country.getPopulation())
			return 1;
		if (this.getPopulation() < country.getPopulation())
			return -1;
		return 0;
	}

}
