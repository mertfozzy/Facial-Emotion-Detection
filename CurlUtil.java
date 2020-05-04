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
			
			int begIndex = result.indexOf("classes"); //curl sonucunun kısaltılması
			int endIndex = result.indexOf("}", begIndex + 8);
			result = result.substring(begIndex + 12, endIndex + 3);
			// curl -u "apikey:BmpffSkPjESnRKDYCoHVKaPQSSHCxrVQU8YS3-WOildA" -F "classifier_ids=DefaultCustomModel_2030462599" "https://api.us-south.visual-recognition.watson.cloud.ibm.com/instances/acfa1ca5-43c1-4b38-9a3d-9eff8477b8f8/v3/classify?url=https://upload.wikimedia.org/wikipedia/commons/8/80/Charlie_Hunnam_by_Gage_Skidmore_3.jpg&version=2018-03-19"
		} catch (IOException e) {
			// System.out.print("error");
			result = "Error : " + e.getMessage();
			e.printStackTrace();
		}

		return result;
		
	}

}
