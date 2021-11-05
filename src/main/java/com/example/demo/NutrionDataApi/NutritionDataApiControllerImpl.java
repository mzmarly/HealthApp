package com.example.demo.NutrionDataApi;

//import org.apache.http.HttpEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

@Service
@Slf4j
public class NutritionDataApiControllerImpl implements NutritionDataApiController {

   // @Value("${cmc.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
//    @Override
//    public String makeAPICallForNutritionData(String food) throws URISyntaxException, IOException {
//
//        String response_content = "";
//
//        URIBuilder query = new URIBuilder("https://api.api-ninjas.com/v1/nutrition?query={food}");
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpGet request = new HttpGet(query.build());
//
//        request.setHeader(HttpHeaders.ACCEPT, "application/json");
//        request.addHeader("X-Api-Key", getApiKey());
//
//        CloseableHttpResponse response = client.execute(request);
//
//        try {
//            System.out.println(response.getStatusLine());
//            HttpEntity entity = response.getEntity();
//            response_content = EntityUtils.toString(entity);
//            System.out.println(entity.getContentType());
//            EntityUtils.consume(entity);
//        } finally {
//            response.close();
//        }
//        return response_content;
//    }

    @Override
    public String testByMichal(String food) {
        String url = "https://api.api-ninjas.com/v1/nutrition?query={food}";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Api-Key", "lVUYcIaQGBHY4gz40swH3g==9lQqFAvISh7QbLpL");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                food
        );
        log.info(response.getBody());
        System.out.println("sasasasasasa");
        return response.getBody();
    }
}
