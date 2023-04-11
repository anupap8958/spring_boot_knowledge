package com.training.platform.service;

import com.training.platform.models.UserRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class RestTemplateService {
    private RestTemplate restTemplate = new RestTemplate();

    private final String url = "https://64078a1e8ee73db92e2c044a.mockapi.io/api/v1/users";

    public List<UserRestTemplate> getAllUsers() {
        List<UserRestTemplate> response = new ArrayList<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            response = restTemplate.exchange(new URI(url), HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<UserRestTemplate>>() {
            }).getBody();

            if (response.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return response;
    }

    public UserRestTemplate getUserById(String id) {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("id", id);

            UserRestTemplate response = restTemplate.getForObject(url + "/" + id, UserRestTemplate.class, param);

            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<String> createUser(UserRestTemplate user) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<UserRestTemplate> entity = new HttpEntity<>(user, headers);

            return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public String updateUser(String id, UserRestTemplate user) {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("id", id);

            UserRestTemplate userOld = restTemplate.getForObject(url + "/" + id, UserRestTemplate.class, param);

            userOld.setName(user.getName() == null || user.getName().isEmpty() ? userOld.getName() : user.getName());
            userOld.setEmail(user.getEmail() == null || user.getEmail().isEmpty() ? userOld.getEmail() : user.getEmail());
            userOld.setAddress(user.getAddress() == null || user.getAddress().isEmpty() ? userOld.getAddress() : user.getAddress());
            userOld.setSexType(user.getSexType() == null || user.getSexType().isEmpty() ? userOld.getSexType() : user.getSexType());

            restTemplate.put(url + "/" + id, user, param);
            return "Updated Successfully";
        } catch (Exception e) {
            throw e;
        }

    }

    public String deleteUser(String id) {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("id", id);

            restTemplate.delete(url + "/" + id, param);

            return "Deleted " + id + " Successfully";
        } catch (Exception e) {
            throw e;
        }
    }
}
