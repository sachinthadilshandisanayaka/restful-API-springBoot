package com.example.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.User;

public class RestClient {
	
	private static final String GET_ALL_USERS_API = "http://localhost:8080/api/users";
	private static final String GET_USER_BY_ID_API = "http://localhost:8080/api/users/{id}";
	private static final String CREATE_USER_API = "http://localhost:8080/api/users";
	private static final String UPDATE_USER_API = "http://localhost:8080/api/users/{id}";
	private static final String DELETE_USER_API = "http://localhost:8080/api/users/{id}";
	
	static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		callGetAllUserAPI();
		callGetUserById();
		callCreateUserAPI();
		callUpdateUserAPI();
		callDeleteUser();
		
	}
	// json type out put
	private static void callGetAllUserAPI() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
		// String.class means that json type(out put)
		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
		System.out.println(result);
		
	}

	private static void callGetUserById() {
		
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 1);	
		User user = restTemplate.getForObject(GET_USER_BY_ID_API, User.class, param);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
	}
	
	private static void callCreateUserAPI() {
		User user = new User("nimal", "marly", "nimal@gmail.com");
		ResponseEntity<User> user2 = restTemplate.postForEntity(CREATE_USER_API, user, User.class);
		System.out.println(user2.getBody());	
	}
	
	private static void callUpdateUserAPI() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 2);
		User user = new User("sanju", "dilshan", "sanju@gmail.com");
		restTemplate.put(UPDATE_USER_API, user, param);
	}
	
	private static void callDeleteUser() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 3);
		restTemplate.delete(DELETE_USER_API, param);
	}
	
	
}





























