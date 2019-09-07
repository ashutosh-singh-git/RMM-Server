package com.rom.rrm.service;

import com.rom.rrm.dto.ReCaptchaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestConnection {

    private final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";
    private final String RECAPTCHA_KEY = "6LdLpq8UAAAAAMEawahfhfqy6l5KU1Evv2Ee2BZa";

    public boolean verifyGoogleReCaptcha(String token){
        String url = RECAPTCHA_URL + "?secret=" + RECAPTCHA_KEY + "&response=" + token;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReCaptchaResponse> response = restTemplate.getForEntity(url, ReCaptchaResponse.class);

        if(response != null && response.getStatusCode() == HttpStatus.OK){
            return response.getBody() != null && response.getBody().isSuccess();
        }
        return true;
    }
}
