package com.techelevator.services;

import com.techelevator.model.Comic;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class RestMarvelService {

    private final String API_BASE_URL = "http://gateway.marvel.com/v1/public/";
    private final RestTemplate restTemplate = new RestTemplate();

    public RestMarvelService() {}

    public Comic getComicById(int id) {
        Comic result = null;
        try {
            String getURL = API_BASE_URL + "comics/" + id + getAuthenticationParams();
            result = restTemplate.getForObject(getURL, Comic.class);
        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Comic searchComicsByTitle(String title) {
        Comic result = null;
        try {
            String searchURL = API_BASE_URL + "comics" + getAuthenticationParams() + "&title=" + title;
            result = restTemplate.getForObject(searchURL, Comic.class);
        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Method for returning the MD5 hash for an input string
     * Sources: https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html,
     * https://www.geeksforgeeks.org/md5-hash-in-java/
     * @param input String input
     * @return MD5 hash of input string
     */
    public String getMd5Hash(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(input.getBytes());
            BigInteger bigInteger = new BigInteger(1, byteArray);
            StringBuilder hashText = new StringBuilder(bigInteger.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public String getAuthenticationParams() {
        String publicKey = "0c99b82c89fe7a6e0f5e0838d9c14025";
        String privateKey = "a25dfe7f4efda4d8f4b27b9a4684ed6eed036e0a";
        String ts = Instant.now().toString();

        String message = ts + privateKey + publicKey;
        String hash = getMd5Hash(message);
        return "?ts=" + ts + "&apikey=" + publicKey + "&hash=" + hash;
    }
}
