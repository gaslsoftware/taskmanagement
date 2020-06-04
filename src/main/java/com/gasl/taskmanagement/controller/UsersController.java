package com.gasl.taskmanagement.controller;

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

@RestController
public class UsersController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtutil;
	
	@Autowired
	UsersDetailsService userDetailsService;
	

	@PostMapping("/authenticate")
	public ResponseEntity<?> getAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

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
		userDetailsService.createNewUser(userInformation);
		return ResponseEntity.ok("User successfully created");
	}

}
