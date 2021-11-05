package com.example.demo.Security;

import org.springframework.context.annotation.Profile;

public class Google extends ApiBinding {

    private static final String GRAPH_API_BASE_URL =
            "https://graph.facebook.com/v2.12";

    public Google(String accessToken) {
        super(accessToken);
    }

    public Profile getProfile() {
        return restTemplate.getForObject(
                GRAPH_API_BASE_URL + "/me", Profile.class);
    }

}