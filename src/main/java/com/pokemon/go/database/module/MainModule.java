package com.pokemon.go.database.module;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.pokemon.go.database.config.MongoConfiguration;
import com.pokemon.go.database.controller.MovesController;
import com.pokemon.go.database.controller.PokemonsController;
import com.pokemon.go.database.controller.SightingsController;
import com.pokemon.go.database.impl.MongoSightingImpl;
import com.pokemon.go.database.impl.MongoTableNonQueryImpl;
import com.pokemon.go.database.interfaces.IMongoConfiguration;
import com.pokemon.go.database.interfaces.IMovesController;
import com.pokemon.go.database.interfaces.IPokemonsController;
import com.pokemon.go.database.interfaces.ISightingsController;
import com.pokemon.go.database.interfaces.ITableNonQueryStatement;
import com.pokemon.go.database.model.Move;
import com.pokemon.go.database.model.Pokemon;
import com.pokemon.go.database.model.Sighting;

public class MainModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		Names.bindProperties(binder(), loadProperties("mongoConfig.properties"));

		bind(new TypeLiteral<ITableNonQueryStatement<Pokemon>>() {
		}).annotatedWith(Names.named("mongoPokemonImpl")).to(new TypeLiteral<MongoTableNonQueryImpl<Pokemon>>() {
		});

		bind(new TypeLiteral<ITableNonQueryStatement<Move>>() {
		}).annotatedWith(Names.named("mongoMoveImpl")).to(new TypeLiteral<MongoTableNonQueryImpl<Move>>() {
		});

		bind(new TypeLiteral<ITableNonQueryStatement<Sighting>>() {
		}).annotatedWith(Names.named("mongoSightingImpl")).to(new TypeLiteral<MongoSightingImpl<Sighting>>() {
		});

		bind(IPokemonsController.class).to(PokemonsController.class);
		bind(IMovesController.class).to(MovesController.class);
		bind(ISightingsController.class).to(SightingsController.class);
	}

	@Provides
	@Named("mongoTablePokemons")
	public IMongoConfiguration getMongoConfigurationForPokemon(@Named("mongoUrl") String url,
			@Named("mongoDatabase") String database) {
		MongoConfiguration conf = new MongoConfiguration(url, database, "pokemons");
		return conf;
	}

	@Provides
	@Named("mongoTableMoves")
	public IMongoConfiguration getMongoConfigurationForMove(@Named("mongoUrl") String url,
			@Named("mongoDatabase") String database) {
		MongoConfiguration conf = new MongoConfiguration(url, database, "moves");
		return conf;
	}

	@Provides
	@Named("mongoTableSightings")
	public IMongoConfiguration getMongoConfigurationForSighting(@Named("mongoUrl") String url,
			@Named("mongoDatabase") String database) {
		MongoConfiguration conf = new MongoConfiguration(url, database, "sightings-2");
		return conf;
	}

	@Provides
	public WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	private Properties loadProperties(String propFile) {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(propFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
