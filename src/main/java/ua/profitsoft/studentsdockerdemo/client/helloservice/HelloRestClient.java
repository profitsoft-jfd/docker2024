package ua.profitsoft.studentsdockerdemo.client.helloservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HelloRestClient {


    private final RestTemplate restTemplate;

    @Value("${hello-service.url}")
    private String helloServiceUrl;

    public String sayHello() {
        ResponseEntity<String> response = restTemplate.exchange(
                helloServiceUrl,
                HttpMethod.GET,
                null,
                ParameterizedTypeReference.forType(String.class)
        );
        return response.getBody();
    }
}
