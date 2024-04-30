package com.netprodex.config;

import com.netprodex.config.filter.JwtTokenValidator;
import com.netprodex.service.UserDetailServiceImpl;
import com.netprodex.util.JwtUtils;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // para configurar por anotaciones
//@SecurityScheme(name="netprodex", scheme = "BasicAuth", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    @Autowired
    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /** SECURITY FILTER CHAIN
     * Aqui se colocan las condiciones de seguridad
     * csrf -> Cross-site request forgery (La falsificación de petición en sitios cruzados) - vulnerabilidad Web
     * httpBasic -> usado solo para user y password
     * STATELESS -> no guardamos session en memoria
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //disable csrf
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sets -> sets.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                        http.requestMatchers(publicEndpoints()).permitAll();
                        // Configure endpoint public
                        http.requestMatchers(HttpMethod.GET, "/api/v1/customer/**").hasAnyRole("ADMIN","USER","INVITED","DEVELOPER");
                        http.requestMatchers(HttpMethod.POST, "/api/v1/customer/*").hasAnyRole("ADMIN","DEVELOPER");
                        http.requestMatchers(HttpMethod.PUT, "/api/v1/customer/*").hasAnyRole("ADMIN","DEVELOPER");
                        http.requestMatchers(HttpMethod.DELETE, "/api/v1/customer/*").hasAnyRole("ADMIN","DEVELOPER");
                        // Configure endpoint private
                        // http.requestMatchers(HttpMethod.POST, "/api/v1/customer/*").hasAuthority("CREATE");
                        // Configure all endpoint not specific
                        // http.anyRequest().authenticated(); -> no recomendado, trabaja con autenticacion
                        http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    private RequestMatcher publicEndpoints() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/v3/api-docs/**"),
                new AntPathRequestMatcher("/swagger-ui/**")
        );
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sets -> sets.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }*/

    /** AUTHENTICATION MANAGER
     *
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /** AUTHENTICATION PROVIDER
     * Necesario: PasswordEncoder y UserDetailsService
     */
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    /** UserDetailsService
     * Simulando que traendo 2 usuarios de la base de datos
     */
    /*@Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("santiago")
                .password("1234")
                .roles("ADMIN")
                .authorities("READ","CREATE")
                .build());

        userDetailsList.add(User.withUsername("daniel")
                .password("1234")
                .roles("USER")
                .authorities("READ")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }*/

    /** PasswordEncoder
     * NoOpPasswordEncoder -> solo para test o pruebas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
