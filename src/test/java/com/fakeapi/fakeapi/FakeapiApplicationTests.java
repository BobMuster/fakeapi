package com.fakeapi.fakeapi;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootTest
class FakeapiApplicationTests {

	private final String jsonData = "{  \n" +
			"    \"employee\": {  \n" +
			"        \"1\":       \"sonoo\",   \n" +
			"        \"salary\":      56000,   \n" +
			"        \"married\":    true  \n" +
			"    }  \n" +
			"}  ";

	@Test
	void contextLoads() {
		String path = "$.employee";
		Object result = JsonPath.read(jsonData, path);
		System.out.println(result);
	}

}
