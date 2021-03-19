package compets.engine.process;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import compets.engine.data.animal.Animal;

public class SimulationSave {

	private FileWriter file;

	public SimulationSave(String filePath) {
		try {
			this.file = new FileWriter(filePath);
		} catch (IOException e) {
			System.err.println("Couldn't find file");
			e.printStackTrace();
		}
	}
	
	public void save(Animal animal) throws IOException{
		BufferedWriter writer;
		int actionGaugeVal = animal.getBehavior().getActionGauge().getValue();
		int healthGaugeVal = animal.getBehavior().getHealthGauge().getValue();
		
		writer = new BufferedWriter(file);
		writer.write("actionGauge:"+actionGaugeVal);
		writer.write("healthGauge:"+healthGaugeVal);
		writer.close();
	}
	
	public void load() {
		
	}
	
}
