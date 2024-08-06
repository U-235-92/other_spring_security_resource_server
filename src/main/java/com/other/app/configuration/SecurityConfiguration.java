package com.other.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Value("${app.security.resource.server.jwk_set_uri}")
	private String jwkSetUri;
	@Value("${app.security.resource.server.issuer_uri}")
	private String issuerUri;
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(csrfCustomizer -> csrfCustomizer
				.disable())
			.authorizeHttpRequests(requestCustomizer -> requestCustomizer
				.requestMatchers("app/read_messages").hasAuthority("SCOPE_read_message")
				.requestMatchers("app/write_message").hasAuthority("SCOPE_write_message")
				.requestMatchers("app/delete_message/**").hasAuthority("SCOPE_delete_message")
				.anyRequest().permitAll())
			.oauth2ResourceServer(resourceServerCustomizer -> resourceServerCustomizer
				.jwt(Customizer.withDefaults()));
		return httpSecurity.build();
	}
	
	@Bean
	protected OAuth2ResourceServerProperties oAuth2ResourceServerProperties() {
		OAuth2ResourceServerProperties oAuth2ResourceServerProperties = new OAuth2ResourceServerProperties();
		Jwt jwt = oAuth2ResourceServerProperties.getJwt();
		jwt.setJwkSetUri(jwkSetUri);
		return oAuth2ResourceServerProperties;
	}
	
	@Bean
	protected JwtDecoder jwtDecoder() {
	    return JwtDecoders.fromIssuerLocation(issuerUri);
	}
}
