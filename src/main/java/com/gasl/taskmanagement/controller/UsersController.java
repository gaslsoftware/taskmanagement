package com.gasl.taskmanagement.controller;

import java.util.ArrayList;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gasl.taskmanagement.bo.UsersDetailsService;
import com.gasl.taskmanagement.dto.Users;
import com.gasl.taskmanagement.utils.JwtUtil;
import com.gasl.taskmanagement.vo.AuthenticationRequest;
import com.gasl.taskmanagement.vo.AuthenticationResponse;
import com.gasl.taskmanagement.vo.RequestResponse;

@RestController
public class UsersController {
	
	Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtutil;
	
	@Autowired
	UsersDetailsService userDetailsService;
	
	

	@PostMapping("/authenticate")
	public ResponseEntity<?> getAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		
		logger.info( "EXECFLOW -> UsersController -> getAuthenticationToken");

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			String jwtToken = jwtutil.generateToken(userDetails);
			return ResponseEntity.ok(new AuthenticationResponse(jwtToken));

		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new BadCredentialsException(e.getMessage());
		}

	}
	@PostMapping("/createnewuser")
	public ResponseEntity<?> createNewUser(@RequestBody Users userInformation) {
		RequestResponse requestResponse = new RequestResponse();
		try {

			logger.info("EXECFLOW -> UsersController -> createNewUser");

			userDetailsService.createNewUser(userInformation);
			requestResponse.setModel(new ArrayList<>());
			requestResponse.setHasError(false);
			requestResponse.setMessage("User successfully created");
			return ResponseEntity.ok(requestResponse);

		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			requestResponse.setModel(new ArrayList<>());
			requestResponse.setHasError(true);
			requestResponse.setMessage("Duplicate username");
			return ResponseEntity.ok(requestResponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			requestResponse.setModel(new ArrayList<>());
			requestResponse.setHasError(true);
			requestResponse.setMessage("Something went wrong");
			return ResponseEntity.ok(requestResponse);
		}
	}

}
