package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojoclass.JsonTestData;
import pojoclass.TestcaseWrapper;

public class JsonReader {
	
	public static TestcaseWrapper readAllModules(String filePath) {
		
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), TestcaseWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JSON test data");
        }
    }
	
	public static JsonTestData getTestDataByScenarioName(String scenarioName, List<JsonTestData> testCases) {
	    for (JsonTestData testCase : testCases) {
	        if (testCase.getTestcaseName().equalsIgnoreCase(scenarioName)) {
	            return testCase; 
	           
	        }
	    }
	    throw new RuntimeException("Scenario not found: " + scenarioName);
	}

}
