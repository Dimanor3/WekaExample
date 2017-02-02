import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.core.Attribute;
import weka.core.DenseInstance;
import java.util.*;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class WekaCreateARFF {
	public static void main(String[] args) throws Exception {
		// Holds an ArrayList of attributes.
		ArrayList<Attribute> attributes = new ArrayList<Attribute> ();
		
		// Holds data from file.
		ArrayList<String> data = new ArrayList<String> ();
		
		// Temporarily holds each read line from the selected file.
		String temp = null;
		
		// The DataSet.
		Instances dataSet;
		
		// Tries to read the file and if it can't it lets the user know.
		try {
			// Reads text from the selected file.
			FileReader fileReader = new FileReader ("data.txt");
			
			// Opens the selected file to read it.
			BufferedReader bufferedReader = new BufferedReader (fileReader);
			
			// Reads the selected file.
			while ((temp = bufferedReader.readLine()) != null) {
				System.out.println (temp);
			}
			
			// Closes the selected file after it's done reading.
			bufferedReader.close ();
		} catch (FileNotFoundException ex) {
			System.out.println ("Unable to open the file!");
		} catch (IOException ex) {
			System.out.println ("Error reading file!");
		}
		
		// Adds all the required attributes.
		attributes.add (new Attribute ("id"));
		attributes.add (new Attribute ("name"));
		attributes.add (new Attribute ("political party"));
		attributes.add (new Attribute ("state"));
		attributes.add (new Attribute ("birth date"));
		
		// Instantiates dataSet
		dataSet = new Instances ("SimpleARFF", attributes, 0);
		
		
	}
}
