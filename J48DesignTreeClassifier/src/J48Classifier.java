import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ConverterUtils.DataSource;

public class J48Classifier {
	public static void main (String[] args) throws Exception {
		Random rand = new Random (1);

		// The number of folds for the cross-evaluation.
		int folds = 10;

		// Reads ARFF file.
		DataSource arffFile = new DataSource ("weather.nominal.arff");
		
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

