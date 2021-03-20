package compets.engine.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalType;
import compets.engine.data.animal.Dog;
import compets.engine.data.map.Position;
import compets.engine.process.factory.AnimalFactory;

public class SimulationSave {

	private File file;
	private static final String FILEPATH = "save/save.txt";
	private static final String POSITION_STRING = "position:";
	private static final String POSITION_SEPARATOR = ",";
	private static final String ACTION_GAUGE_STRING = "actionGauge:";
	private static final String HEALTH_GAUGE_STRING = "healthGauge:";
	private static final String ANIMAL_TYPE_STRING = "animalType:";

	private static final String SEPARATOR_KEY_VALUE = ":";
	private static final String KEY_POSITION = "position";
	private static final String KEY_ACTION_GAUGE = "actionGauge";
	private static final String KEY_HEALTH_GAUGE = "healthGauge";
	private static final String KEY_ANIMAL_TYPE = "animalType";
	
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

		writer.write(ANIMAL_TYPE_STRING + retreiveAnimalType(animal));
		writer.newLine();
		writer.write(POSITION_STRING + position.getX() + POSITION_SEPARATOR + position.getY());
		writer.newLine();
		writer.write(ACTION_GAUGE_STRING + actionGaugeVal);
		writer.newLine();
		writer.write(HEALTH_GAUGE_STRING + healthGaugeVal);
		writer.close();
	}

	private String retreiveAnimalType(Animal animal) {
		try {
			return AnimalType.valueOf(animal.getClass().getSimpleName().toUpperCase()).name();
		}catch (IllegalArgumentException e) {
			System.err.println("Cannot found animal type in enum");
			return AnimalType.DOG.name();
		}
	}

	/**
	 * @deprecated use loadAnimalData instead
	 * Récupère les données sauvegarder et recreait la simulation précédente
	 * 
	 * @return l'animal avec les parametre sauvegarder
	 * @throws FileNotFoundException si aucun fichier de sauvegarde existe
	 */
	public Animal load() throws IOException {
		if(!file.exists()) {
			throw new IOException("No savegame found");
		}
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int actionGaugeVal = 0, healthGaugeVal = 0, x = 0, y = 0;
		try {
//			line = reader.readLine();
//			if(!line.startsWith(ANIMAL_TYPE_STRING)) {
//				reader.close();
//				throw new IOException("Cannot read save file : animal type not found.");
//			}
//			String animalType = reader.readLine().substring(ANIMAL_TYPE_STRING.length());
			
			String line = reader.readLine();
			if(!line.startsWith(POSITION_STRING)) {
				reader.close();
				throw new IOException("Cannot read save file : position property not found.");
			}
			String[] positionString = line.substring(POSITION_STRING.length()).split(POSITION_SEPARATOR);
			x = Integer.parseInt(positionString[0]);
			y = Integer.parseInt(positionString[1]);
			
			line = reader.readLine();
			if(!line.startsWith(ACTION_GAUGE_STRING)) {
				reader.close();
				throw new IOException("Cannot read save file : action gauge value not found.");
			}
			actionGaugeVal = Integer.parseInt(line.substring(ACTION_GAUGE_STRING.length()));
			
			line = reader.readLine();
			if(!line.startsWith(HEALTH_GAUGE_STRING)) {
				reader.close();
				throw new IOException("Cannot read save file : health gauge value  not found.");
			}
			healthGaugeVal = Integer.parseInt(line.substring(HEALTH_GAUGE_STRING.length()));
			reader.close();
		} catch (NumberFormatException e) {
			// format fichier incorrecte
			throw new IOException("Cannot read save file : a number cannot be readed properly");
		}

//		Animal animal;
//		
//		if(animalType.equals("chat")){
//			animal = new Cat(pos);
//		}
//		else {
//			animal = new Dog(pos);
//		}

		Position pos = new Position(x, y);
		Animal animal = new Dog(pos);
		animal.setBehavior(actionGaugeVal, healthGaugeVal);

		return animal;
	}
	
	/**
	 * Load animal data from the save file. It take account of the animal type too (cat, dog, ...)
	 * @return the animal described in the save file
	 * @throws IOException if anything in the file is not writed properly
	 */
	public Animal loadAnimalData() throws IOException{
		if(!file.exists()) {
			throw new IOException("No savegame found");
		}
		// Add all lines in a hashmap in order to retrieve it after 
		HashMap<String, String> fileContent = parseFileContent(file);
		
		// if any value is null, then we didn't found it in the file
		String actionGaugeString = fileContent.get(KEY_ACTION_GAUGE);
		checkNonNull(actionGaugeString, "actionGauge");
		String healthGaugeString = fileContent.get(KEY_HEALTH_GAUGE);
		checkNonNull(healthGaugeString, "healthGauge");
		String positionString = fileContent.get(KEY_POSITION);
		checkNonNull(positionString, "positionGauge");
		//animal is optionnal
		String animalTypeString = fileContent.get(KEY_ANIMAL_TYPE);
		
		int actionGaugeValue = readNumber(actionGaugeString);
		int healthGaugeValue = readNumber(healthGaugeString);
		
		String positionsSplit[] = positionString.split(POSITION_SEPARATOR);
		if(positionsSplit.length != 2) {
			throw new IOException("Cannot read properly position property : the number of values provided is not correct");
		}
		Position position = new Position(readNumber(positionsSplit[0]), readNumber(positionsSplit[1]));
		
		// If animal type is not found, get the default one (DOG)
		AnimalType animalType = readAnimalType(animalTypeString);
		Animal animal = AnimalFactory.createAnimalWithType(animalType, position);
		animal.setBehavior(actionGaugeValue, healthGaugeValue);
		
		return animal;
	}

	/**
	 * @param animalTypeString
	 * @return
	 */
	private AnimalType readAnimalType(String animalTypeString) {
		AnimalType animalType;
		if(animalTypeString == null) {
			animalType = AnimalType.DOG;
		}else {
			try {
				animalType = AnimalType.valueOf(animalTypeString);
			}catch (IllegalArgumentException e) {
				animalType = AnimalType.DOG;
			}
		}
		return animalType;
	}
	
	private HashMap<String, String> parseFileContent(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		HashMap<String, String> fileContent = new HashMap<String, String>();
		String line;
		while((line = reader.readLine()) != null) {
			String split[] = line.split(SEPARATOR_KEY_VALUE);
			// if we don't have exactly 2 fields here, line is unreadable
			// also, don't read properties that were already loaded
			if(split.length != 2 || fileContent.containsKey(split[0])) {
				continue;
			}
			fileContent.put(split[0], split[1]);
		}
		reader.close();
		return fileContent;
	}

	private void checkNonNull(Object obj, String propertyName) throws IOException{
		if(obj == null) {
			throw new IOException(String.format("Cannot find %s properety in file", propertyName));
		}
	}
	
	private int readNumber(String numberString) throws IOException{
		try {
			return Integer.parseInt(numberString);
		} catch (NumberFormatException e) {
			throw new IOException("Cannot read number " + numberString);
		}
	}
}
