import java.io.File;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.instance.RemoveWithValues;

public class HandlingData {
	public static void main (String[] args) throws Exception {
		// Sets up the indexs we want to remove items from
		int[] index = new int[6];
		
		index[0] = 1;
		index[1] = 2;
		index[2] = 3;
		index[3] = 4;
		index[4] = 5;
		index[5] = 6;
		
		// Creates a CSVLoader to load the data from cars-missing.csv.
		CSVLoader csvLoader = new CSVLoader ();
		
		// Create ReplaceMissingValues filter
		ReplaceMissingValues rMV = new ReplaceMissingValues ();
		
		// Create Remove filter
		Remove remove = new Remove ();
		
		// Reads ARFF file.
		DataSource arffFile;

		// Creates an Instances variable to hold cars-missing's data.
		Instances carsMissingReplace = null;
		
		// Checks to see whether or not the file exists in .CSV format,
		// if it doesn't it then checks for to see if the file exists in
		// .ARFF format, if it doesn't it then lets the user know that the
		// file doesn't exist.
		if (new File ("cars-missing.csv").exists ()) {
			// Sets the data source for the CSVLoader
			csvLoader.setSource (new File ("cars-missing.csv"));

			// Stores the data found from cars-missing.csv in an Instances variable.
			carsMissingReplace = csvLoader.getDataSet ();
		} else if (new File ("cars-missing.arff").exists ()) {
			arffFile = new DataSource ("cars-missing.arff");
			
			carsMissingReplace = arffFile.getDataSet ();
		} else {
			System.out.println ("This file doesn't exist!");
		}
		
		// Makes the last attribute the class.
		carsMissingReplace.setClassIndex (carsMissingReplace.numAttributes () - 1);

		Instances carsMissingRemove = carsMissingReplace;
		carsMissingRemove.setClassIndex (carsMissingRemove.numAttributes () - 1);
		
		// Set the input format for rMV.
		rMV.setInputFormat (carsMissingReplace);

		// The setup for the remove filter
		remove.setAttributeIndices (""+(carsMissingRemove.classIndex()+1));
		remove.setInvertSelection (false);
		remove.setInputFormat(carsMissingRemove);
		
		carsMissingRemove = Filter.useFilter (carsMissingRemove, remove);
		
		// Apply the filter
		carsMissingReplace = Filter.useFilter (carsMissingReplace, rMV);
		
		// Creates an evaluation of carsMissing.
		Evaluation evalReplace = new Evaluation (carsMissingReplace);
		//Evaluation evalRemove = new Evaluation (carsMissingRemove);

		// Initializes the classiWSfier.
		Classifier clsJ48Replace = new J48 (), clsJ48Remove = new J48 ();
		
		// Builds the classifier with the Instances variable.
		clsJ48Replace.buildClassifier (carsMissingReplace);
		//clsJ48Remove.buildClassifier (carsMissingRemove);
		
		// Completes a cross validation for the classifier.
		evalReplace.crossValidateModel (clsJ48Replace, carsMissingReplace, 10, new Random (1));
		//evalRemove.crossValidateModel(clsJ48Remove, carsMissingRemove, 10, new Random (1));
		
		// Prints out all the required data.
		System.out.println ("Results with Missing Values Replaced");
		System.out.println (evalReplace.toClassDetailsString ());
		System.out.println (evalReplace.toMatrixString ());
		System.out.println ("\nResults with Missing Values Instances Removed");
		//System.out.println (evalRemove.toClassDetailsString ());
		//System.out.println (evalRemove.toMatrixString ());
	}
}
