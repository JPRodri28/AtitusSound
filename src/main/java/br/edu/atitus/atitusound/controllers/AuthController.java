package br.edu.atitus.atitusound.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import atitussound.utils.JwtUtils;
import br.edu.atitus.atitusound.dtos.SigninDTO;
import br.edu.atitus.atitusound.dtos.UserDTO;
import br.edu.atitus.atitusound.entities.UserEntity;
import br.edu.atitus.atitusound.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;
	private final AuthenticationConfiguration auth;
	
	public AuthController(UserService userService, AuthenticationConfiguration auth) {
		super();
		this.userService = userService;
		this.auth = auth;
	}

	private UserEntity convertDTO2Entity(UserDTO dto) {
		var user = new UserEntity();
		BeanUtils.copyProperties(dto, user);
		return user;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<String> signin(@RequestBody SigninDTO signin){
		try {
			auth.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(signin.getUsername(), signin.getPassword()));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("error", e.getMessage()).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error", e.getMessage()).build();
		}
		String jwt = JwtUtils.generateTokenFromUsername(signin.getUsername());
		return ResponseEntity.ok(jwt);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserEntity> signup(@RequestBody UserDTO dto){
		var user = convertDTO2Entity(dto);
		try {
			userService.save(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	
}
