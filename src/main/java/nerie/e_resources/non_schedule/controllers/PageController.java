package nerie.e_resources.non_schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import jakarta.servlet.http.HttpServletRequest;

@Controller

public class PageController {

	private final DefaultKaptcha defaultKaptcha;

	@Autowired
	public PageController(DefaultKaptcha defaultKaptcha) {
		this.defaultKaptcha = defaultKaptcha;
	}

	// Login
	@GetMapping(value = "/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			System.out.println("Auth data" + auth);
			return "redirect:/dashboard";
		}
		return "login";

	}

	// Contact Us
	@GetMapping(value = "/contact-us")
	public String contact_us() {
		return "contact-us";
	}

	@GetMapping(value = "/english-word")
	public String eng_word_sentences() {
		return "create-word";
	}


	//	Index
	@GetMapping("/")
	public String home() {
		return "nerie-eresources-home";
	}

	// Dashboard
	@GetMapping(value = "/dashboard")
	public String dashboard(HttpServletRequest req) {
		System.out.println(req.toString());
		return "dashboard";
	}

	@GetMapping(value = "/nerie-eresources-home")
	public String e_resources(HttpServletRequest req) {
		return "nerie-eresources-home";
	}

	@GetMapping(value = "/about-us")
	public String about_us(HttpServletRequest req) {
		return "about-us";
	}

	@GetMapping(value = "/language&social-science")
	public String language_social_science(HttpServletRequest req) {
		return "lang-socialscien";
	}

}
