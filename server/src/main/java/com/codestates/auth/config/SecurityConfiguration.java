package com.codestates.auth.config;

import com.codestates.auth.filter.JwtAuthenticationFilter;
import com.codestates.auth.jwt.JwtTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
	@Value("${spring.security.oauth2.client.registration.google.clientId}")
	private String clientId;

	@Value("${spring.security.oauth2.client.registration.google.clientSecret}")
	private String clientSecret;
	private final JwtTokenizer jwtTokenizer;

	public SecurityConfiguration(JwtTokenizer jwtTokenizer) {
		this.jwtTokenizer = jwtTokenizer;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
						.headers().frameOptions().sameOrigin()
						.and()
						.csrf().disable()
						.cors(withDefaults())
						.formLogin().disable()
						.httpBasic().disable()
						.apply(new CustomFilterConfigurer())	// Custom Configurer 를 추가해 커스터마이징된 Configuration 추가
						.and()
						.authorizeHttpRequests(authorize -> authorize
										.anyRequest().permitAll()		// 모든 HTTP request 요청에 대해 접근 허용
						)
//						.authorizeHttpRequests(authorize -> authorize			//OAuth
//										.anyRequest().authenticated()
//						)
						.oauth2Login(withDefaults());	// oauth 인증 활성화
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// JwtAuthenticationFilter 를 등록하는 역할
	public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
		@Override
		public void configure(HttpSecurity builder) throws Exception {
			AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);  // AuthenticationManager 객체를 얻음

			JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
			jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");          // request URL 설정

			builder.addFilter(jwtAuthenticationFilter);  // JwtAuthenticationFilter 를 Spring Security Filter Chain 에 추가
		}
	}

	// CORS 정책을 설정
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));	// 스크립트 기반의 HTTP 통신을 허용
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));	// 파라미터로 지정한 HTTP Method 대한 HTTP 통신을 허용
		configuration.setAllowedHeaders(Arrays.asList("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);	 // CORS 정책을 적용
		return source;
	}

	// ClientRegistration 을 저장하기 위한 Repository
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		var clientRegistration = clientRegistration();

		return new InMemoryClientRegistrationRepository(clientRegistration);   // ClientRegistration을 메모리에 저장
	}

	// ClientRegistration 인스턴스를 생성하는 private 메서드
	private ClientRegistration clientRegistration() {
		// Builder 패턴을 이용해 ClientRegistration 인스턴스를 제공
		return CommonOAuth2Provider
						.GOOGLE
						.getBuilder("google")
						.clientId(clientId)
						.clientSecret(clientSecret)
						.build();
	}
}