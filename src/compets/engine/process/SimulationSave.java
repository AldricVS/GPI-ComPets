package compets.engine.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Dog;
import compets.engine.data.map.Position;

public class SimulationSave {

	private File file;
	private static final String FILEPATH = "save/save.txt";
	private static final String POSITION_STRING = "position:";
	private static final String POSITION_SEPARATOR = ",";
	private static final String ACTION_GAUGE_STRING = "actionGauge:";
	private static final String HEALTH_GAUGE_STRING = "healthGauge:";

	public SimulationSave() {
		this(FILEPATH);
	}

	public SimulationSave(String filePath) {
		this.file = new File(filePath);
	}

	/**
	 * Sauvegarde l'état de la partie dans un fichier
	 * 
	 * @param animal l'animal qui doir etre sauvegarder
	 * @throws IOException si il a été impossible de sauvegarder
	 */
	public void save(Animal animal) throws IOException {
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		Position position = animal.getPosition();
		int actionGaugeVal = animal.getBehavior().getActionGauge().getValue();
		int healthGaugeVal = animal.getBehavior().getHealthGauge().getValue();

		writer.write(POSITION_STRING + position.getX() + POSITION_SEPARATOR + position.getY());
		writer.newLine();
		writer.write(ACTION_GAUGE_STRING + actionGaugeVal);
		writer.newLine();
		writer.write(HEALTH_GAUGE_STRING + healthGaugeVal);
		writer.close();
	}

	/**
	 * Récupère les données sauvegarder et recreait la simulation précédente
	 * 
	 * @return l'animal avec les parametre sauvegarder
	 * @throws FileNotFoundException si aucun fichier de sauvegarde existe
	 */
	public Animal load() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));

		int actionGaugeVal = 0, healthGaugeVal = 0, x = 0, y = 0;
		try {
			String[] positionString = reader.readLine().substring(POSITION_STRING.length()).split(POSITION_SEPARATOR);
			x = Integer.parseInt(positionString[0]);
			y = Integer.parseInt(positionString[1]);
			actionGaugeVal = Integer.parseInt(reader.readLine().substring(ACTION_GAUGE_STRING.length()));
			healthGaugeVal = Integer.parseInt(reader.readLine().substring(HEALTH_GAUGE_STRING.length()));
			reader.close();
		} catch (NumberFormatException e) {
			// format fichier incorrecte
			throw new IOException("Cannot read save file : a number cannot be readed properly");
		} catch (IOException e) {
			// impossible de lire le fichier
			e.printStackTrace();
			throw new IOException("Cannot read save file : IOException occured");
		} 

		Position pos = new Position(x, y);
		Animal animal = new Dog(pos);
		animal.setBehavior(actionGaugeVal, healthGaugeVal);

		return animal;
	}

}
