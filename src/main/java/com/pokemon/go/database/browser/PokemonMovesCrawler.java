package com.pokemon.go.database.browser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.pokemon.go.database.impl.MongoTableNonQueryImpl;
import com.pokemon.go.database.interfaces.IMongoConfiguration;
import com.pokemon.go.database.interfaces.ITableNonQueryStatement;
import com.pokemon.go.database.model.Move;

public class PokemonMovesCrawler {

	private final ITableNonQueryStatement<Move> databaseTable;
	// private final WebDriver driver;
	private final String pokemonUrl = "http://pokemongodatabase.com/move/";

	private Move move;

	@Inject
	public PokemonMovesCrawler(@Named("mongoMoveImpl") ITableNonQueryStatement<Move> databaseTable,
			@Named("mongoTableMoves") IMongoConfiguration mongoConfig) {
		// TODO Auto-generated constructor stub
		this.databaseTable = databaseTable;
		((MongoTableNonQueryImpl<Move>) databaseTable).initMongo(mongoConfig);
		// this.driver = driver;
	}

	public void close() {
		// if (driver != null)
		// driver.close();
	}

	public void process(String pokemonMove) {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver singleDriver = new ChromeDriver();
		try {

			singleDriver.get(pokemonUrl + pokemonMove);
			// System.out.println(driver.getPageSource());

			Document doc;
			// need http protocol
			doc = Jsoup.parse(singleDriver.getPageSource());

			// get all links
			Element mainElement = doc.select("pkgodb-move-details").first();

			Elements statElement = mainElement.select("div > div[class=col-sm-6 col-md-3]");

			move = new Move();

			move.setMoveName(pokemonMove);

			parseFirstColumn(statElement.get(0));
			parseSecondColumn(statElement.get(1));

			Elements effectiveNessElement = mainElement.select("div > div[class=col-md-6]");
			parseEffective(effectiveNessElement.get(0).child(1));
			parseNotEffective(effectiveNessElement.get(0).child(4));

			parseUsedBy(mainElement.child(3));

			databaseTable.create(move);

			// doc.remove();
		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			singleDriver.close();

		}
	}

	private boolean parseFirstColumn(Element main) {

		Elements elements = main.select("table > tbody > tr");
		for (Element item : elements) {

			if (item.child(0).text().equals("Move Type:")) {
				if (Strings.isNullOrEmpty(item.child(1).text()) || item.child(1).text().equals("#"))
					return false;

				move.setMoveType(item.child(1).text());
			}

			if (item.child(0).text().equals("Type:")) {
				move.setType(item.child(1).text());
			}

			if (item.child(0).text().equals("Power:")) {
				move.setPower(item.child(1).text());
			}

			if (item.child(0).text().equals("Energy:")) {
				move.setEnergy(item.child(1).text());
			}

			if (item.child(0).text().equals("Duration:")) {
				move.setDuration(item.child(1).text());
			}
		}

		return true;
	}

	private boolean parseSecondColumn(Element main) {

		Elements elements = main.select("table > tbody > tr");
		for (Element item : elements) {

			if (item.child(0).text().equals("Damage:")) {
				move.setDamage(item.child(1).text());
			}

			if (item.child(0).text().equals("DPS:")) {
				move.setDPS(item.child(1).text());
			}

			if (item.child(0).text().equals("STAB DPS:")) {
				move.setSTABDPS(item.child(1).text());
			}

			if (item.child(0).text().equals("Critical Chance:")) {
				move.setCriticalChance(item.child(1).text());
			}

			if (item.child(0).text().equals("Accuracy:")) {
				move.setAccuracy(item.child(1).text());
			}
		}

		return true;
	}

	private boolean parseEffective(Element main) {

		Elements elements = main.select("p > span");

		List<String> effective = new ArrayList<String>();

		for (Element item : elements) {

			if (!Strings.isNullOrEmpty(item.text()))
				effective.add(item.text());
		}

		if (effective.size() > 0)
			move.setEffective(effective);

		return true;
	}

	private boolean parseNotEffective(Element main) {

		Elements elements = main.select("p > span");

		List<String> notEffective = new ArrayList<String>();

		for (Element item : elements) {

			if (!Strings.isNullOrEmpty(item.text()))
				notEffective.add(item.text());
		}

		if (notEffective.size() > 0)
			move.setNotEffective(notEffective);

		return true;
	}

	private boolean parseUsedBy(Element main) {

		Elements elements = main.select("p");

		List<String> usedBy = new ArrayList<String>();

		for (Element item : elements) {

			if (!Strings.isNullOrEmpty(item.text()))
				usedBy.add(item.text());
		}

		if (usedBy.size() > 0)
			move.setUsedBy(usedBy);

		return true;
	}
}
