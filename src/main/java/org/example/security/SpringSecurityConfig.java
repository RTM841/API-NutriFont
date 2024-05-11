package org.example.security;


import org.example.security.filter.JwtAuthenticationFilter;
import org.example.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        //Permirsos de roles y usuarios.
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/{username}").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("SUPERADMIN")
                        //Permisos de Categoria.
                        .requestMatchers(HttpMethod.GET, "/api/categoria").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categoria").hasAnyRole("ADMINSUPERMERCADO","SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/categoria/{id}").hasAnyRole("ADMINSUPERMERCADO","SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categoria/{id}").hasAnyRole("ADMINSUPERMERCADO","SUPERADMIN")
                        //Permisos Supermercado.
                        .requestMatchers(HttpMethod.GET, "/api/supermercado").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/supermercado/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/supermercado").hasAnyRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/supermercado/{id}").hasAnyRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/supermercado/{id}").hasAnyRole("SUPERADMIN")
                        //Permisos Productos.
                        .requestMatchers(HttpMethod.GET, "/api/producto").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/producto/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/producto").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/producto/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/producto/{id}").permitAll()
                        //Permisos Receta.
                        .requestMatchers(HttpMethod.GET, "/api/receta").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/receta/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/receta").hasAnyRole("USER","ADMINSUPERMERCADO","SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/receta/{id}").hasAnyRole("ADMINSUPERMERCADO","SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/receta/{id}").hasAnyRole("ADMINSUPERMERCADO","SUPERADMIN")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }
}
