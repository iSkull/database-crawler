package com.pokemon.go.database;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.pokemon.go.database.config.MainConfiguration;
import com.pokemon.go.database.interfaces.IMovesController;
import com.pokemon.go.database.interfaces.IPokemonsController;
import com.pokemon.go.database.interfaces.ISightingsController;
import com.pokemon.go.database.module.MainModule;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Main extends Application<MainConfiguration> {

	private final IPokemonsController pokemonsController;
	private final IMovesController movesController;
	private final ISightingsController sightingsController;

	@Inject
	public Main(IPokemonsController pokemonsController, IMovesController movesController,
			ISightingsController sightingsController) {
		this.pokemonsController = pokemonsController;
		this.movesController = movesController;
		this.sightingsController = sightingsController;
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Injector injector = Guice.createInjector(new MainModule());
		Main main = injector.getInstance(Main.class);
		try {
			String[] arguments = new String[1];
			arguments[0] = "server";
			main.run(arguments);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run(MainConfiguration arg0, Environment arg1) throws Exception {
		// TODO Auto-generated method stub
		arg1.jersey().register(pokemonsController);
		arg1.jersey().register(movesController);
		arg1.jersey().register(sightingsController);
	}
}
