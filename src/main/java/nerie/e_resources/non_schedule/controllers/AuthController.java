package nerie.e_resources.non_schedule.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import nerie.e_resources.non_schedule.dto.request.RegisterRequest;
import nerie.e_resources.non_schedule.entity.User;
import nerie.e_resources.non_schedule.repository.UserRepository;
import nerie.e_resources.non_schedule.services.AuthenticationService;
import nerie.e_resources.non_schedule.utils.RSAUtil;

@Controller
@RequestMapping("/nerie/e-resources/")

public class AuthController {

	@Autowired
	private AuthenticationService service;

	@Autowired
	private UserRepository userRepository;

	// Registration of new user
	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest request) throws Exception {

		Optional<User> user = userRepository.findByUsername(request.getUsername());

		if (user.isPresent()) {
			throw new Exception("Error! User already exists");

		} else {
			service.register(request);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Registered Succesfully!");
	}

	// Getting Public Key

	@GetMapping(value = "/get-publickey")
	@ResponseBody
	public String getPublickey(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String publicKey = null;
		try {
			publicKey = RSAUtil.generateKey();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "-----BEGIN PUBLIC KEY-----\n" + publicKey + "\n-----END PUBLIC KEY-----";
	}

}