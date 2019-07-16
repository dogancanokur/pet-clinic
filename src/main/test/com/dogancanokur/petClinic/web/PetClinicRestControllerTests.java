package com.dogancanokur.petClinic.web;

import com.dogancanokur.petClinic.model.Owner;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetClinicRestControllerTests {
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGetOwnerById() {
        ResponseEntity<Owner> responseEntity =
                restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);

        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), CoreMatchers.equalTo(200));
        MatcherAssert.assertThat(responseEntity.getBody().getFirstName(), CoreMatchers.equalTo("Doğancan"));
    }

    @Test
    public void testGetOwnerByLastName() {
        ResponseEntity<List> responseEntity =
                restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Okur", List.class);

        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), CoreMatchers.equalTo(200));

        List<Map<String, String>> owners = responseEntity.getBody();
        List<String> firstNames = owners.stream().map(e -> e.get("firstName")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Doğancan", "Kübra"));
    }

    @Test
    public void testGetOwners() {

        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);

        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));

        List<Map<String, String>> owners = responseEntity.getBody();
        List<String> firstNames = owners.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Doğancan", "Kübra", "Murat", "Gürcan"));
    }

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Mban");
        owner.setLastName("Dran");
        URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);

        Owner owner2 = restTemplate.getForObject(location, Owner.class);

        MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
        MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));

    }

    @Test
    public void testUpdateOwner() {
        Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);

        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Kübra"));

        owner.setLastName("Aygun");
        restTemplate.put("http://localhost:8080/rest/owner/2", owner);

        owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);

        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Kübra"));
        MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo("Aygun"));
    }
}
