import java.io.File;
import weka.core.Attribute;
import weka.core.DenseInstance;
import java.util.*;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class WekaCreateARFF {
	public static void main(String[] args) throws Exception   {
		ArrayList<Attribute> attributes = new ArrayList<Attribute> ();
		ArrayList<String> data = new ArrayList<String> ();
		Instances dataSet;
		
		attributes.add (new Attribute ("id"));
		attributes.add (new Attribute ("name"));
		attributes.add (new Attribute ("political party"));
		attributes.add (new Attribute ("state"));
		attributes.add (new Attribute ("birth date"));
		
		dataSet = new Instances ("SimpleARFF", attributes, 0);
		
		
	}
}
