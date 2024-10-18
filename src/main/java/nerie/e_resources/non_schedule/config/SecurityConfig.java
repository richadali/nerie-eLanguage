package nerie.e_resources.non_schedule.config;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity

public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> {

			CorsConfigurationSource cs = resources -> {

				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "OPTIONS"));
				corsConfiguration.setAllowedHeaders(
						List.of("Authorization", "Content-Type", "X-Requested-With", "Accept", "X-XSRF-TOKEN"));

				corsConfiguration.setAllowCredentials(true);

				return corsConfiguration;

			};

			cors.configurationSource(cs);
		})

				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/nerie/e-resources/english-words/**","/nerie/e-resources/states/**","/nerie/e-resources/languages/**").permitAll()
						.requestMatchers("/contact-us", "/nerie/e-resources/get-captcha",
								"/nerie/e-resources/get-publickey", "/images/**", "/css/**", "/js/**", "/static/**",
								"/resources/**", "/.*.png", "/.*.svg", "/.*.css", "/.*.js", "/.*.min.js", "/.*.ico",
								"/webfonts/**", "/.*.woff2", "/403", "/404", "/500", "/error")
						.permitAll().requestMatchers(HttpMethod.POST, "/login-process").permitAll()
						.requestMatchers("/nerie/e-resources/register", "/nerie/e-resources/create-user",
								"/nerie/e-resources/get-list-users", "/nerie/e-resources/get-list-users-res",
								"/nerie/e-resources/get-user-by-id", "/ajax/get-list-users.js", "/ajax/update-user.js",
								"/nerie/e-resources/update-user", "/nerie/e-resources/enable-user",
								"/nerie/e-resources/lock-unlock-user", "/nerie/e-resources/search-user","/nerie/e-resources/english-words/create")
						.hasAnyRole("ADMIN", "SUPERADMIN")

						.requestMatchers("/ajax/get-list-users-activities.js", "/get-list-login-activities",
								"/get-list-login-activities-res")
						.hasRole("SUPERADMIN").requestMatchers(HttpMethod.POST, "/nerie/e-resources/task-allotment")
						.hasRole("ADMIN")

						.requestMatchers("/nerie/e-resources/get-categories", "/nerie/e-resources/add-category",
								"/nerie/e-resources/edit-category", "/ajax/add-category.js",
								"/ajax/get-list-categories.js")
						.hasRole("ADMIN")
//						.requestMatchers("/dashboard", "/nerie/e-resources/user-profile",
//								"/nerie/e-resources/change-password", "/ajax/get-user-by-username.js",
//								"/nerie/e-resources/get-user-by-username")
						.anyRequest().authenticated()


				).formLogin(formLogin -> formLogin.loginPage("/login").permitAll().loginProcessingUrl("/login/process")
						.successHandler(new AuthenticationSuccessHandler() {

							@Override
							public void onAuthenticationSuccess(HttpServletRequest request,
									HttpServletResponse response, Authentication authentication)
									throws IOException, ServletException {
								response.sendRedirect("/dashboard");

							}

						}))

				.logout((logout) -> logout.logoutUrl("/logout").invalidateHttpSession(true).clearAuthentication(true)
						.logoutSuccessUrl("/login?logout").invalidateHttpSession(true).deleteCookies("JSESSIONID"))

				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
						.invalidSessionUrl("/login").sessionFixation().migrateSession().maximumSessions(1)
						// .maxSessionsPreventsLogin(true)
						.expiredUrl("/login?expired"))

				.headers(headers -> headers
						.xssProtection(
								xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
						.httpStrictTransportSecurity(
								hsts -> hsts.includeSubDomains(true).preload(true).maxAgeInSeconds(31536000))
						.frameOptions(frameOptions -> frameOptions.sameOrigin()).cacheControl(Customizer.withDefaults())
						.contentTypeOptions(Customizer.withDefaults())
						.contentSecurityPolicy(csp -> csp.policyDirectives(
								"script-src 'self'; form-action 'self'; style-src 'self';object-src 'self'"))
						.referrerPolicy(referrer -> referrer.policy(ReferrerPolicy.SAME_ORIGIN)));

		;

		return http.build();
	}

}