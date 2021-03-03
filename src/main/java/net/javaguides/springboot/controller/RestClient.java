package net.javaguides.springboot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.entity.User;

public class RestClient {
	
	private static final String GET_ALL_USERS_API = "http://localhost:3333/api/users";
	private static final String GET_USERS_BY_ID_API = "http://localhost:3333/api/users/{id}";
	private static final String CREATE_USER_API = "http://localhost:3333/api/users";
	private static final String UPDATE_USER_API = "http://localhost:3333/api/users/{id}";
	private static final String DELETE_USER_API = "http://localhost:3333/api/users/{id}";
	
	static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		callGetAllUsersAPI();
		callGetUserByIdAPI();
		callCreateUserAPI();
		callUpdateUserAPI();
		callDeleteAPI();
	}
	
	private static void callGetAllUsersAPI() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
		System.out.println(result);
	}
	
	private static void callGetUserByIdAPI() {
		Map<String,Integer> param = new HashMap<>();
		param.put("id", 1);
		
		User user = restTemplate.getForObject(GET_USERS_BY_ID_API, User.class, param);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
	}
	
	private static void callCreateUserAPI() {
		User user = new User("Virat","Kholi","virat@gmail.com");
		ResponseEntity<User> user2 = restTemplate.postForEntity(CREATE_USER_API, user, User.class);
		System.out.println(user2.getBody());
	}
	
	private static void callUpdateUserAPI() {
		Map<String,Integer> param = new HashMap<>();
		param.put("id", 3);
		User updateUser = new User("Jeevan","Kumar","jeevan@gmail.com");
		restTemplate.put(UPDATE_USER_API, updateUser, param);
	}
	
	public static void callDeleteAPI() {
		Map<String,Integer> param = new HashMap<>();
		param.put("id", 2);
		restTemplate.delete(DELETE_USER_API, param);
	}

}
