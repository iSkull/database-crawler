package com.pokemon.go.database.browser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://pokemongodatabase.com/pokemon/Pikachu");
			// System.out.println(driver.getPageSource());

			Document doc;
			// need http protocol
			doc = Jsoup.parse(driver.getPageSource());

			// get page title
			String title = doc.title();
			System.out.println("title : " + title);

			// get all links
			Element mainElement = doc.select("pkgodb-pokemon-details").first();

			getInfo(mainElement.child(0).child(2).child(0));
			getBaseStat(mainElement.child(0).child(2).child(1));
			getPhysique(mainElement.child(0).child(2).child(2));
			getSpecialMoves(mainElement.child(2));

		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			if(driver != null)
				driver.close();
			
		}
	}

	private static void getInfo(Element main) {
		Elements infoElement = main.select("table[class=table table-center] > tbody > tr");
		for (Element item : infoElement) {

			if (item.child(0).text().equals("Dex Number:")) {
				System.out.println("Dex Number:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Type:")) {
				Elements childItem = item.child(1).children();
				for (Element spanItem : childItem) {
					System.out.println("Type:" + spanItem.text());
				}
			}

			if (item.child(0).text().equals("Species:")) {
				System.out.println("Species:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Rarity:")) {
				System.out.println("Rarity:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Evolve Candy:")) {
				System.out.println("Evolve Candy:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Egg:")) {
				System.out.println("Egg:" + item.child(1).text());
			}
		}
	}

	private static void getBaseStat(Element main) {
		Elements baseStatElement = main.select("table[class=table] > tbody > tr");
		for (Element item : baseStatElement) {

			if (item.child(0).text().equals("Base Attack:")) {
				System.out.println("Base Attack:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Base Defense:")) {
				System.out.println("Base Defense:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Base Stamina:")) {
				System.out.println("Base Stamina:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Base Capture Rate:")) {
				System.out.println("Base Capture Rate:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Base Flee Rate:")) {
				System.out.println("Base Flee Rate:" + item.child(1).text());
			}
		}
	}

	private static void getPhysique(Element main) {
		Elements physiqueElement = main.select("table[class=table] > tbody > tr");
		for (Element item : physiqueElement) {

			if (item.child(0).text().equals("Height:")) {
				System.out.println("Height:" + item.child(1).text());
			}

			if (item.child(0).text().equals("XL Height:")) {
				System.out.println("XL Height:" + item.child(1).text());
			}

			if (item.child(0).text().equals("XS Height:")) {
				System.out.println("XS Height:" + item.child(1).text());
			}

			if (item.child(0).text().equals("Weight:")) {
				System.out.println("Weight:" + item.child(1).text());
			}

			if (item.child(0).text().equals("XL Weight:")) {
				System.out.println("XL Weight:" + item.child(1).text());
			}

			if (item.child(0).text().equals("XS Weight:")) {
				System.out.println("XS Weight:" + item.child(1).text());
			}
		}
	}

	private static void getSpecialMoves(Element main) {

		List<String> moves = new ArrayList<String>();
		Elements titleElements = main.select("div > div > a");
		for (Element item : titleElements) {
			moves.add(item.text());
		}

		int index = 0;
		Elements tableElements = main.select("div > table");
		for (Element tableItem : tableElements) {
			Elements detailElements = tableItem.select("tbody > tr");
			System.out.println("Special Move:" + moves.get(index));
			for (Element item : detailElements) {

				if (item.child(0).text().equals("Move Type:")) {
					System.out.println("Move Type:" + item.child(1).text());
				}

				if (item.child(0).text().equals("Type:")) {
					Elements childItem = item.child(1).children();
					for (Element spanItem : childItem) {
						System.out.println("Type:" + spanItem.text());
					}
				}

				if (item.child(0).text().equals("DPS:")) {
					System.out.println("DPS:" + item.child(1).text());
				}

				if (item.child(0).text().equals("Accuracy:")) {
					System.out.println("Accuracy:" + item.child(1).text());
				}

				if (item.child(0).text().equals("Critical Chance:")) {
					System.out.println("Critical Chance:" + item.child(1).text());
				}

				if (item.child(0).text().equals("Energy:")) {
					System.out.println("Energy:" + item.child(1).text());
				}

				if (item.child(0).text().equals("STAB DPS:")) {
					System.out.println("STAB DPS:" + item.child(1).text());
				}

				if (item.child(0).children().size() > 0) {
					if (item.child(0).getElementsByTag("span").text().equals("STAB Bonus")) {
						System.out.println("STAB Bonus" + item.child(1).text());
					}
				}
			}

			index++;
		}
	}
}
