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

	public SimulationSave() {
		this.file = new File(FILEPATH);
	}
	
	/**
	 * Sauvegarde l'état de la partie dans un fichier
	 * 
	 * @param animal l'animal qui doir etre sauvegarder
	 * @throws IOException si il a été impossible de sauvegarder
	 */
	public void save(Animal animal) throws IOException{
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		int actionGaugeVal = animal.getBehavior().getActionGauge().getValue();
		int healthGaugeVal = animal.getBehavior().getHealthGauge().getValue();
		
		writer.write("actionGauge:"+actionGaugeVal);
		writer.write("healthGauge:"+healthGaugeVal);
		writer.close();
	}
	
	/**
	 * Récupère les données sauvegarder et recreait la simulation précédente
	 * 
	 * @return l'animal avec les parametre sauvegarder
	 * @throws FileNotFoundException si aucun fichier de sauvegarde existe
	 */
	public Animal load() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		int actionGaugeVal = 0, healthGaugeVal = 0, x = 0, y = 0;
		
		try {
			x = Integer.parseInt(reader.readLine());
			y = Integer.parseInt(reader.readLine());
			actionGaugeVal = Integer.parseInt(reader.readLine());
			healthGaugeVal  =Integer.parseInt(reader.readLine());
			reader.close();
		} catch (NumberFormatException e) {
			// format fichier incorrecte
			e.printStackTrace();
		} catch (IOException e) {
			// impossible de lire le fichier
			e.printStackTrace();
		} 
		
		Position pos = new Position(x,y);
		Animal animal = new Dog(pos);
		animal.setBehavior(actionGaugeVal, healthGaugeVal);	
		
		return animal;
	}
	
}
