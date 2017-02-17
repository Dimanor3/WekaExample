import java.io.File;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

public class HandlingData {
	public static void main (String[] args) throws Exception {
		// Creates a CSVLoader to load the data from cars-missing.csv.
		CSVLoader csvLoader = new CSVLoader ();
		
		m_ReplaceMissingValues = new ReplaceMissingValues ();
		
		// Reads ARFF file.
		DataSource arffFile;

		// Creates an Instances variable to hold cars-missing's data.
		Instances carsMissing = null;
		
		// Checks to see whether or not the file exists in .CSV format,
		// if it doesn't it then checks for to see if the file exists in
		// .ARFF format, if it doesn't it then lets the user know that the
		// file doesn't exist.
		if (new File ("cars-missing.csv").exists ()) {
			// Sets the data source for the CSVLoader
			csvLoader.setSource (new File ("cars-missing.csv"));

			// Stores the data found from cars-missing.csv in an Instances variable.
			carsMissing = csvLoader.getDataSet ();
		} else if (new File ("cars-missing.arff").exists ()) {
			arffFile = new DataSource ("cars-missing.arff");
			
			carsMissing = arffFile.getDataSet ();
		} else {
			System.out.println ("This file doesn't exist!");
		}
		
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
