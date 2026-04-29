package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
	
	static Properties prop = new Properties();
	
	public static Properties getProperties() throws IOException {
		
		 File file = new File("src/test/resources/config.properties");
		
		 FileInputStream f = new FileInputStream(file);
	      
		 prop.load(f);
		 
		 return prop;
		
	}
	
	public static String getProperty(String key) throws IOException {
		
		getProperties();
		
        if (prop == null) {
            throw new RuntimeException("Properties file not initialized!");
        }
        return prop.getProperty(key);
    }

}
