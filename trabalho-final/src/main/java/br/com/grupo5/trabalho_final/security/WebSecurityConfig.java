package br.com.grupo5.trabalho_final.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.grupo5.trabalho_final.security.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
				.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/**", "/roles/**", "/test/**",
								"/swagger-ui/**", "/v3/api-docs/**", "/actuator/**",
								"/cliente/cadastro/**", "/loja/cadastro/**", "/loja/all-lojas**", "/produto/all/**", "/categoria/**")
						.permitAll()
						.requestMatchers("/loja/{id}/**", "/cliente/{id}/**", "/pedido/**", "/pedido/novo-pedido/**", "/pedido/update-pedido/{id}/**",
								"/pedido/delete-pedido/{id}/**", "pedido/lista-produtos/{idCliente}/**", "/pedido/lista-pedidos/{idCliente}/**")
						.hasAnyRole("USER", "MODERATOR", "ADMIN")
						.requestMatchers("/loja/delete-id/{id}/**", "/cupom/**").hasAnyRole("MODERATOR", "ADMIN")
						.requestMatchers("/cliente/delete-id/{id}", "/alterar-cliente/{cpf}").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/produto/{id}/**", "/user/**", "/cupom/adicionando-cupom/**", "/listando-cupom/**", "/deletando-cupom/{id}/**", "/atualizando-cupom/{id}/**").hasAnyRole("USER", "MODERATOR")
						.requestMatchers("/test/mod/**", "/loja/alterar-loja/{cnpj}", "/product/create/**", "/delete-id/{id}/**")
						.hasRole("MODERATOR")
						.requestMatchers("/user/**").hasRole("USER")
						.requestMatchers("/test/admin/**", "/cliente/all-clientes/**", "/categoria/create/**", "/categoria/{id}/**").hasRole("ADMIN")
						.anyRequest().authenticated());
		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
