package com.pokemon.go.database.browser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.pokemon.go.database.model.BaseStat;
import com.pokemon.go.database.model.Info;
import com.pokemon.go.database.model.Physique;
import com.pokemon.go.database.model.Pokemon;
import com.pokemon.go.database.model.SpecialMove;

public class PokemonCrawler {

	private final ITableNonQueryStatement<Pokemon> databaseTable;
	// private final WebDriver driver;
	private final String pokemonUrl = "http://pokemongodatabase.com/pokemon/";

	@Inject
	public PokemonCrawler(@Named("mongoPokemonImpl") ITableNonQueryStatement<Pokemon> databaseTable,
			@Named("mongoTablePokemons") IMongoConfiguration mongoConfig) {
		// TODO Auto-generated constructor stub
		this.databaseTable = databaseTable;
		((MongoTableNonQueryImpl<Pokemon>) databaseTable).initMongo(mongoConfig);
		// this.driver = driver;
	}

	public void close() {
		// if (driver != null)
		// driver.close();
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
			Element mainElement = doc.select("pkgodb-pokemon-details").first();

			Pokemon pokemon = new Pokemon();

			Info info = getInfo(mainElement.child(0).child(2).child(0));
			if (info == null) {
				return;
			}

			BaseStat baseStat = getBaseStat(mainElement.child(0).child(2).child(1));
			Physique physique = getPhysique(mainElement.child(0).child(2).child(2));
			List<SpecialMove> specialMoves = getSpecialMoves(mainElement.child(2));

			pokemon.setPokemonName(pokemonName.trim().toLowerCase());
			pokemon.setInfo(info);
			pokemon.setBaseStat(baseStat);
			pokemon.setPhysique(physique);
			pokemon.setSpecialMove(specialMoves);
			
			databaseTable.create(pokemon);

			// doc.remove();
		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			singleDriver.close();

		}
	}

	private Info getInfo(Element main) {

		Info info = new Info();

		Elements infoElement = main.select("table[class=table table-center] > tbody > tr");
		for (Element item : infoElement) {

			if (item.child(0).text().equals("Dex Number:")) {
				if (Strings.isNullOrEmpty(item.child(1).text()) || item.child(1).text().equals("#"))
					return null;

				info.setDevNumberValue(item.child(1).text());
			}

			if (item.child(0).text().equals("Type:")) {
				List<String> type = new ArrayList<String>();
				Elements childItem = item.child(1).children();
				for (Element spanItem : childItem) {
					type.add(spanItem.text());
				}
				info.setPokemonType(type);
			}

			if (item.child(0).text().equals("Species:")) {
				info.setPokemonSpeciesType(item.child(1).text());
			}

			if (item.child(0).text().equals("Rarity:")) {
				info.setPokemonRarityType(item.child(1).text());
			}

			if (item.child(0).text().equals("Evolve Candy:")) {
				info.setEvolveCandyValue(item.child(1).text());
			}

			if (item.child(0).text().equals("Egg:")) {
				info.setPokemonEggType(item.child(1).text());
			}
		}

		return info;
	}

	private BaseStat getBaseStat(Element main) {
		BaseStat baseStat = new BaseStat();

		Elements baseStatElement = main.select("table[class=table] > tbody > tr");
		for (Element item : baseStatElement) {

			if (item.child(0).text().equals("Base Attack:")) {
				baseStat.setBaseAttackValue(item.child(1).text());
			}

			if (item.child(0).text().equals("Base Defense:")) {
				baseStat.setBaseDefenceValue(item.child(1).text());
			}

			if (item.child(0).text().equals("Base Stamina:")) {
				baseStat.setBaseStaminaValue(item.child(1).text());
			}

			if (item.child(0).text().equals("Base Capture Rate:")) {
				baseStat.setBaseCaptureRateValue(item.child(1).text());
			}

			if (item.child(0).text().equals("Base Flee Rate:")) {
				baseStat.setBaseFleeRateValue(item.child(1).text());
			}
		}

		return baseStat;
	}

	private Physique getPhysique(Element main) {
		Physique physique = new Physique();

		Elements physiqueElement = main.select("table[class=table] > tbody > tr");
		for (Element item : physiqueElement) {

			if (item.child(0).text().equals("Height:")) {
				physique.setHeightValue(item.child(1).text());
			}

			if (item.child(0).text().equals("XL Height:")) {
				physique.setXLHeightValue(parseHeight(item.child(1).text()));
			}

			if (item.child(0).text().equals("XS Height:")) {
				physique.setXSHeightValue(parseHeight(item.child(1).text()));
			}

			if (item.child(0).text().equals("Weight:")) {
				physique.setWeightValue(item.child(1).text());
			}

			if (item.child(0).text().equals("XL Weight:")) {
				physique.setXLWeightValue(parseWeight(item.child(1).text()));
			}

			if (item.child(0).text().equals("XS Weight:")) {
				physique.setXSWeightValue(parseWeight(item.child(1).text()));
			}
		}

		return physique;
	}

	private List<SpecialMove> getSpecialMoves(Element main) {

		List<SpecialMove> specialMoves = new ArrayList<SpecialMove>();

		List<String> moves = new ArrayList<String>();
		Elements titleElements = main.select("div > div > a");
		for (Element item : titleElements) {
			moves.add(item.text());
		}


		int index = 0;
		Elements tableElements = main.select("div > table");
		for (Element tableItem : tableElements) {
			Elements detailElements = tableItem.select("tbody > tr");
			SpecialMove specialMove = new SpecialMove();
			specialMove.setSpecialMoveName(moves.get(index));
			for (Element item : detailElements) {

				if (item.child(0).text().equals("Move Type:")) {
					specialMove.setSpecialMoveMoveType(item.child(1).text());
				}

				if (item.child(0).text().equals("Type:")) {
					List<String> type = new ArrayList<String>();
					Elements childItem = item.child(1).children();
					for (Element spanItem : childItem) {
						type.add(spanItem.text());
					}
					specialMove.setSpecialMoveType(type);
				}

				if (item.child(0).text().equals("DPS:")) {
					specialMove.setDPSValue(item.child(1).text());
				}

				if (item.child(0).text().equals("Accuracy:")) {
					specialMove.setAccuracyValue(item.child(1).text());
				}

				if (item.child(0).text().equals("Critical Chance:")) {
					specialMove.setCriticalChanceValue(item.child(1).text());
				}

				if (item.child(0).text().equals("Energy:")) {
					specialMove.setEnergyValue(item.child(1).text());
				}

				if (item.child(0).text().equals("STAB DPS:")) {
					specialMove.setSTABDPSValue(item.child(1).text());
				}

				if (item.child(0).children().size() > 0) {
					if (item.child(0).getElementsByTag("span").text().equals("STAB Bonus")) {
						specialMove.setSTABBonusValue(item.child(1).text());
					}
				}
			}

			index++;
			specialMoves.add(specialMove);
		}

		return specialMoves;
	}

	private String parseHeight(String height) {
		Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?m");
		Matcher matcher = pattern.matcher(height);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	private String parseWeight(String weight) {
		Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?kg");
		Matcher matcher = pattern.matcher(weight);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
}
