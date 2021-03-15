
package com.ikubinfo.internship.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private TokenProvider tokenProvider;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private JwtAuthenticationEntryPoint authenticationErrorHandler;

    @Autowired
    public WebSecurityConfig(TokenProvider tokenProvider, JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint authenticationErrorHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.authenticationErrorHandler = authenticationErrorHandler;
    }
    // Configure BCrypt password encoder

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

   /* @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
*/
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        List<String> allowOrigins = Arrays.asList("*");
        configuration.setAllowedOriginPatterns(allowOrigins);
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Collections.singletonList(HttpHeaders.AUTHORIZATION));
        // in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(

                        "/api/login",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/h2-console",
                        "/h2-console/**",
                        "/css/**",
                        "/js/**",
                        "/images/**","/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**");
    }

    // Configure security settings
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ;
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf()
                .disable()
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/route/list")
                .hasAnyRole("ADMIN")
                .antMatchers("/api/create-user")
                .hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .apply(securityConfigurerAdapter());
    }


    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(this.tokenProvider);
    }
}