package fileUtility;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadFormPropertes {

	public static String readproperty(String key) throws IOException {
		Properties prop  = new Properties();
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Configure.properties");
		prop.load(fis);
		 String value = prop.getProperty(key);
		    if (value == null) {
		        throw new RuntimeException("Key " + key + "' not found in properties file.");
		    }
		    return value;
	}
}


