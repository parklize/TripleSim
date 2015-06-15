package io.github.parklize.measure;

import java.util.List;

import io.github.parklize.utils.DBpediaAPI;

public class DBrec implements SimMeasure {

	@Override
	public double getSim(String r_a, String r_b) {
		
		// Component 1
		
		// Component 2
		
		// Component 3 - Incoming node
		List<String> link_list_Cii = DBpediaAPI.getCiiLinks(r_a, r_b);
		double sum_Cii = 0.0;
		for (String link : link_list_Cii) {
			String property = "<" + link + ">";
			double count_1 = DBpediaAPI.getCiiRaN(r_a, property, r_b);
			double count_2 = DBpediaAPI.getCiiRaN(r_a, property);
			sum_Cii = sum_Cii + getNormalizedValue(count_1, count_2);
		}
		
		// Component 4 - Outgoing node
		List<String> link_list_Cio = DBpediaAPI.getCioLinks(r_a, r_b);
		double sum_Cio = 0.0;
		for (String link : link_list_Cio) {
			String property = "<" + link + ">";
			double count_1 = DBpediaAPI.getCioRaN(r_a, property, r_b);
			double count_2 = DBpediaAPI.getCioRaN(r_a, property);
			sum_Cio = sum_Cio + getNormalizedValue(count_1, count_2);
		}
		
		return 1/(1+sum_Cii+sum_Cio);
	}

	private double getNormalizedValue(double count_1, double count_2) {
		return count_1/(1+Math.log10(count_2));
	}

}
