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
		
		// Holds all of the nominal values for nominal attributes
		ArrayList<String> politicalPartyVals = new ArrayList<String> ();
		ArrayList<String> stateVals = new ArrayList<String> ();
		
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
				data.add (temp);
			}
			
			// Closes the selected file after it's done reading.
			bufferedReader.close ();
		} catch (FileNotFoundException ex) {
			System.out.println ("Unable to open the file!");
		} catch (IOException ex) {
			System.out.println ("Error reading file!");
		}
		
		// Adds all the values for each nominal type attribute
		politicalPartyVals.add ("democrat");
		politicalPartyVals.add ("republican");
		stateVals.add ("CA");
		stateVals.add ("TX");
		stateVals.add ("NY");
		stateVals.add ("NC");
		stateVals.add ("SC");
		
		// Adds all the required attributes.
		attributes.add (new Attribute ("id"));
		attributes.add (new Attribute ("name", (ArrayList<String>) null));
		attributes.add (new Attribute ("political party", politicalPartyVals));
		attributes.add (new Attribute ("state", stateVals));
		attributes.add (new Attribute ("birth date", "yyyy-MM-dd"));
		
		// Create Instances object
		dataSet = new Instances ("SimpleARFF", attributes, 0);
		
		// Add the data
		for (String line : data) {
			
		}
	}
}
