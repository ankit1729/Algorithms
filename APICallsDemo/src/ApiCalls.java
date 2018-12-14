import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

// Main Url 
// http://indl136174.idc.oracle.com:7011/hcmRestApi/resources/11.13.18.05/checkInDocuments?expand=questionnaireResponses,questionnaireResponses.questionResponses,questionnaireResponses.questionResponses.AnswerCLOB,questionnaireResponses.questionResponses.responseAttachments&q=CheckInDocumentId=300100111091276
public class ApiCalls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//URL url = new URL("http://indl136174.idc.oracle.com:7011/hcmRestApi/resources/11.13.18.05/checkInDocuments?expand=questionnaireResponses&q=CheckInDocumentId=300100111091276");
			URL url = new URL("https://fuscdrmsmc80-fa-ext.us.oracle.com:443/hcmRestApi/resources/11.13.18.05/checkInDocuments/00100000000EACED00057708000110F0842DF9DF0000000EACED0005770800000000000000CA0000000EACED00057708000110F07E10AD1A0000000EACED00057708000110F08424BFC20000000EACED0005770800000000000000CA0000000EACED00057708000110F0841311620000004AACED00057372000D6A6176612E73716C2E4461746514FA46683F3566970200007872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000160AF049000780000004AACED00057372000D6A6176612E73716C2E4461746514FA46683F3566970200007872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800004EB9F6DD1400780000000EACED00057708000110F084132E1F0000004AACED00057372000D6A6176612E73716C2E4461746514FA46683F3566970200007872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000160AF049000780000004AACED00057372000D6A6176612E73716C2E4461746514FA46683F3566970200007872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800004EB9F6DD1400780000000AACED000577040000000100000001590000000EACED0005770800005AF310F6AA9C0000004AACED00057372000D6A6176612E73716C2E4461746514FA46683F3566970200007872000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000000E7F1AB9800780000004AACED00057372000D6A6176612E73716C2E4461746514FA46683F3566970200007872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800004EB9F6DD140078?fields=CheckInTemplateId&onlyData=true");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			//conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Basic dG0tbWZpdHppbW1vbnM6V2VsY29tZTE=");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("REST-Framework-Version", "4");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			ObjectMapper mapper = new ObjectMapper();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			StringBuffer jsonBuffer = new StringBuffer();
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				jsonBuffer.append(output);
			}
			
			mapper.readValue(jsonBuffer.toString(), CheckInDocumentsItems.class);
			
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
