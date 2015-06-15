package io.github.parklize.worker;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import io.github.parklize.measure.DBrec;
import io.github.parklize.utils.DBpediaAPI;

// TODO: Auto-generated Javadoc
/**
 * The Class Worker.
 */
public class Worker {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Logger  logger = Logger.getLogger("com.foo");
		logger.setLevel(Level.INFO);
//		DBpediaAPI.serviceTest();
//		DBpediaAPI.getCiiLinks("<http://dbpedia.org/resource/Cat>", "<http://dbpedia.org/resource/Dog>");
//		DBpediaAPI.getCiiRaN("<http://dbpedia.org/resource/Cat>", "<http://dbpedia.org/property/species>");
		
		DBrec dbrec = new DBrec();
		double sim = dbrec.getSim("<http://dbpedia.org/resource/Cat>", "<http://dbpedia.org/resource/Human>");
		System.out.println(sim);
		
	}

}
