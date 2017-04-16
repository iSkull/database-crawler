package com.pokemon.go.database.browser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.inject.Inject;

public class PokemonImageCrawler {

	private final String mainUrl = "http://pokemongodatabase.com";
	private final String pokemonUrl = "http://pokemongodatabase.com/pokemon/";

	@Inject
	public PokemonImageCrawler() {

	}

	public void close() {
	}

	public void process(String pokemonName) {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver singleDriver = new ChromeDriver();
		try {

			singleDriver.get(pokemonUrl + pokemonName);
			// System.out.println(driver.getPageSource());

			Document doc;
			// need http protocol
			doc = Jsoup.parse(singleDriver.getPageSource());

			// get all links
			Element mainElement = doc.select("pkgodb-pokemon-details > div > div > div > img").first();

			String imageUrl = mainElement.attr("src");
			
			URL url = new URL(mainUrl + imageUrl);
			BufferedImage image = ImageIO.read(url);
			ImageIO.write(image, "png",
					new File("C://Users//User//Documents//JavaWorkspace20160729//PokemonGoDatabaseCrawler//imge//"
							+ pokemonName + ".png"));

			// doc.remove();
		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			singleDriver.close();

		}
	}
}
