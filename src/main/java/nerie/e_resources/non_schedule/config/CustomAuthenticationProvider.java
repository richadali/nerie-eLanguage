package nerie.e_resources.non_schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nerie.e_resources.non_schedule.services.PasswordService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordService passwordService;

	@Autowired(required = false)
	private HttpServletRequest request;

	@Override
	public Authentication authenticate(@Valid Authentication authentication) {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		String captcha = request.getParameter("captcha");
		String capchaText = (String) request.getSession().getAttribute("captchaText");
		request.removeAttribute("captchaText");

		if (captcha.equals(capchaText)) {

			UserDetails userDetails = passwordService.isValidUser(username, password);
			if (userDetails != null) {
				return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
			} else {
				throw new BadCredentialsException("Incorrect user credentials !!");
			}
		} else {
			throw new BadCredentialsException("Invalid Captcha !!");

		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}