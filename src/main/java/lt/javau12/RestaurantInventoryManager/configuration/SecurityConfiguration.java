package lt.javau12.RestaurantInventoryManager.configuration;

import lt.javau12.RestaurantInventoryManager.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;


@Configuration
public class SecurityConfiguration {

    public static final String API_PREFIX = "/api/inventory";

    JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000")); // Allow React frontend
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // Needed if you're using Authorization headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all routes

        return new CorsFilter(source);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()//Public End point for logging in
                        .requestMatchers("/auth/create/**").hasRole("ADMIN")//End points for creating users,managers and admins
                        .requestMatchers("/auth/all").hasRole("ADMIN")
                        //For Product endpoints
                        .requestMatchers(HttpMethod.GET, API_PREFIX + "/products/**").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers(HttpMethod.POST, API_PREFIX + "/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_PREFIX + "/products/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, API_PREFIX + "/products/**").hasRole("ADMIN")
                        //For Dish Endpoints
                        .requestMatchers(HttpMethod.GET, API_PREFIX + "/dishes/**").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers(HttpMethod.POST, API_PREFIX + "/dishes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_PREFIX + "/dishes/**").hasAnyRole("ADMIN")//Only admins can edit dishes since editing dishes can remove products and loose the link between them
                        .requestMatchers(HttpMethod.DELETE, API_PREFIX + "/dishes/**").hasRole("ADMIN")
                        //For Category Endpoints
                        .requestMatchers(HttpMethod.GET, API_PREFIX + "/category/**").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers(HttpMethod.POST, API_PREFIX + "/category").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_PREFIX + "/category/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, API_PREFIX + "/category/**").hasRole("ADMIN")
                        //For Unit Endpoints
                        .requestMatchers(HttpMethod.GET, API_PREFIX + "/units/**").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers(HttpMethod.POST, API_PREFIX + "/units").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_PREFIX + "/units/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, API_PREFIX + "/units/**").hasRole("ADMIN")
                        //For Vendor Endpoints
                        .requestMatchers(HttpMethod.GET, API_PREFIX + "/vendors/**").hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers(HttpMethod.POST, API_PREFIX + "/vendors").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_PREFIX + "/vendors/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, API_PREFIX + "/vendors/**").hasRole("ADMIN")

                        .anyRequest().permitAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
