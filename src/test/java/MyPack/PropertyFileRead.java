package MyPack;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileRead {
	
	
	
	public static Properties initializeProper(String path) throws IOException {
		FileReader f = new FileReader(path);
		Properties p = new Properties();
		p.load(f);
		
		return p;
		
		
		
	}
	
}
