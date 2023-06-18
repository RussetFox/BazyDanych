package com.example.backend.Config;

import com.example.backend.Entity.Role;
import com.example.backend.Entity.StockCategory;
import com.example.backend.Entity.User;
import com.example.backend.Repository.StockCategoryRepository;
import com.example.backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter JwtAuthFilter;
    private final UserRepository userRepository;
    private final StockCategoryRepository stockCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/")); // Or specific origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    void createUsersAtStart(){
        User adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .build();
        userRepository.save(adminUser);
        User employeeUser = User.builder()
                .username("employee")
                .password(passwordEncoder.encode("employee123"))
                .role(Role.EMPLOYEE)
                .build();
        userRepository.save(employeeUser);

        User managerUser = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("manager123"))
                .role(Role.MANAGER)
                .build();
        userRepository.save(managerUser);
    }
    @Bean
    void createStockCateogiresAtStart(){
        StockCategory whiskeyCategory = StockCategory.builder()
                .categoryname("Whiskey")
                .build();
        stockCategoryRepository.save(whiskeyCategory);

        StockCategory cognacCategory = StockCategory.builder()
                .categoryname("Cognac")
                .build();
        stockCategoryRepository.save(cognacCategory);
        StockCategory ginCategory = StockCategory.builder()
                .categoryname("Gin")
                .build();
        stockCategoryRepository.save(ginCategory);
        StockCategory vodkaCategory = StockCategory.builder()
                .categoryname("Vodka")
                .build();
        stockCategoryRepository.save(vodkaCategory);
        StockCategory rumCategory = StockCategory.builder()
                .categoryname("Rum")
                .build();
        stockCategoryRepository.save(rumCategory);
        StockCategory liqueurCategory = StockCategory.builder()
                .categoryname("Liqueur")
                .build();
        stockCategoryRepository.save(liqueurCategory);
        StockCategory wineCategory = StockCategory.builder()
                .categoryname("Wine")
                .build();
        stockCategoryRepository.save(wineCategory);

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/baza/user/auth/**", "/**").permitAll()
                .requestMatchers("/content/getall", "/content/getall/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
                .anyRequest().hasAnyRole("ADMIN", "MANAGER")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}