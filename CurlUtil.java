package calling.photo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurlUtil {
	
	public static String executeCurlCommand(String[] command) {

		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		String result = "";
		try {
			p = process.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			result = builder.toString();
			// System.out.print("EXECUTION RESULT : " + result);
			

		} catch (IOException e) {
			// System.out.print("error");
			result = "Error : " + e.getMessage();
			e.printStackTrace();
		}

		return result;
		
	}

}
