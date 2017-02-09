import java.io.BufferedReader;
import java.io.FileReader;

import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class J48Classifier {
	public static void main (String[] args) throws Exception {
		/*// Reads the desired file.
		BufferedReader reader = new BufferedReader (new FileReader ("weather.nominal.arff"));

		// Reads data from the arff file.
		ArffReader arff = new ArffReader (reader);

		// Stores each data instance of the arff file.
		Instances arffData = arff.getData ();

		// Makes the last attribute the class
		arffData.setClassIndex (arffData.numAttributes () - 1);

		System.out.println (arffData);*/
		System.out.println ("TEST");
	}
}