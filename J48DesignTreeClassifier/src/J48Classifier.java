import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class J48Classifier {
	public static void main (String[] args) throws Exception {
		Random rand = new Random (1);

		// The number of folds for the cross-evaluation.
		int folds = 10;

		// Reads ARFF file.
		DataSource arffFile = new DataSource ("weather.nominal.arff");

		// Temporarily holds each read line from the selected file.
		String temp = null;

		ArrayList<String> testData = new ArrayList<String> ();
		
		Instances testResult;

		// Tries to read the file and if it can't it lets the user know.
		try {
			// Reads text from the selected file.
			FileReader fileReader = new FileReader ("testData.txt");

			// Opens the selected file to read it.
			BufferedReader bufferedReader = new BufferedReader (fileReader);

			// Reads the selected file.
			while ( (temp = bufferedReader.readLine ()) != null) {
				testData.add (temp);
			}

			// Closes the selected file after it's done reading.
			bufferedReader.close ();
		} catch (FileNotFoundException ex) {
			System.out.println ("Unable to open the file!");
		} catch (IOException ex) {
			System.out.println ("Error reading file!");
		}

		// Converts and stores ARFF file.
		Instances arffData = arffFile.getDataSet ();

		// Makes the last attribute the class.
		arffData.setClassIndex (arffData.numAttributes () - 1);

		// Creates an evaluation of arffData.
		Evaluation eval = new Evaluation (arffData);

		// Initializes the classifier.
		Classifier clsJ48 = new J48 ();

		// Builds the classifier with the Instances in the ARFF file.
		clsJ48.buildClassifier (arffData);

		// Completes a cross validation for the classifier.
		eval.crossValidateModel (clsJ48, arffData, folds, rand);

		// Prints out all the required data.
		System.out.println (clsJ48);
		System.out.println (eval.toSummaryString ());
		System.out.println (eval.toClassDetailsString ());
		System.out.println (eval.toMatrixString ());
	}
}
