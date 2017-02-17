import java.io.File;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class HandlingData {
	public static void main (String[] args) throws Exception {
		// Creates a CSVLoader to load the data from cars-missing.csv
		CSVLoader csvLoader = new CSVLoader ();
		
		// Sets the data source for the CSVLoader
		csvLoader.setSource (new File ("cars-missing.csv"));
		
		// Stores the data found from cars-missing.csv in an Instances variable
		Instances carsMissing = csvLoader.getDataSet ();
		
		// Makes the last attribute the class.
		carsMissing.setClassIndex (carsMissing.numAttributes () - 1);
		
		// Creates an evaluation of carsMissing.
		Evaluation eval = new Evaluation (carsMissing);

		// Initializes the classifier.
		Classifier clsJ48 = new J48 ();
		
		// Builds the classifier with the Instances variable.
		clsJ48.buildClassifier (carsMissing);
		
		// Completes a cross validation for the classifier.
		eval.crossValidateModel (clsJ48, carsMissing, 10, new Random (1));

		// Prints out all the required data.
		System.out.println (eval.toClassDetailsString ());
		System.out.println (eval.toMatrixString ());
	}
}
