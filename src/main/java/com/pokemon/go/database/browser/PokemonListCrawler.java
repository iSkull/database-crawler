package com.pokemon.go.database.browser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;

public class PokemonListCrawler {
	private final WebDriver driver;
	private final String pokemonListUrl = "http://www.telegraph.co.uk/gaming/what-to-play/pokemon-go-full-list-of-original-151-pokemon-available-to-catch/";

	@Inject
	public PokemonListCrawler(WebDriver driver) {
		this.driver = driver;
	}

	public void close() {
		if (driver != null)
			driver.close();
	}

	public List<String> getPokemonList() {

		List<String> pokemons = null;
		try {
			driver.get(pokemonListUrl);

			Document doc;
			// need http protocol
			doc = Jsoup.parse(driver.getPageSource());

			// get all links
			Elements mainElement = doc.select(
					"article > div > div > div > ol > li");

			pokemons = new ArrayList<String>();
			for (Element item : mainElement) {
				pokemons.add(item.text());
			}

			doc.remove();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return pokemons;
	}
}
