package dev.agitrubard.couriertracking.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

@UtilityClass
public class CustomMockMvcRequestBuilders {

    public MockHttpServletRequestBuilder get(String endpoint, Map<String, String> parameters) {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(endpoint);

        parameters.forEach(mockHttpServletRequestBuilder::param);

        return mockHttpServletRequestBuilder;
    }

    public MockHttpServletRequestBuilder get(String endpoint) {
        return MockMvcRequestBuilders.get(endpoint);
    }

    public MockHttpServletRequestBuilder post(String endpoint, Object requestBody) {
        return MockMvcRequestBuilders.post(endpoint)
                .content(CustomJsonUtil.toJson(requestBody))
                .contentType(MediaType.APPLICATION_JSON);
    }

}
