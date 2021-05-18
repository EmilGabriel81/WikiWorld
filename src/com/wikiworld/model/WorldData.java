package com.wikiworld.model;

import java.util.ArrayList;
import java.util.List;

public class WorldData {
	/**
	 * The data is being stored here, the actual backend of the app
	 */
	private List<Country> worldCountries = new ArrayList<Country>();

	public List<Country> getWorldCountries() {
		return worldCountries;
	}

	public boolean addCountry(Country country) {
		if (country != null) {
			this.worldCountries.add(country);
			return true;
		}
		return false;
	}

	public Country getCountryByName(String name) {
		Country c = null;
		for (Country ct : this.worldCountries) {
			if (ct.getName().equals(name)) {
				return ct;
			}
		}
		return c;
	}

}
