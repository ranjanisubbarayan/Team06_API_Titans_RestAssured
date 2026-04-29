package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojoclass.TestcaseWrapper;

public class JsonDataLoader {
	
	private static TestcaseWrapper testData;
	
	public static TestcaseWrapper  loadTestData() {
		if(testData == null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				testData = mapper.readValue(JsonDataLoader.class.getClassLoader().getResourceAsStream("TestData.json"),TestcaseWrapper.class);
			}catch (Exception e) {
				throw new RuntimeException("Failed to load Test data",e);
			}
		}
		return testData;
	}
	
}
