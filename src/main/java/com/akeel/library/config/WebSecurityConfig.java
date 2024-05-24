package com.akeel.library.config;
////
////import security.com.akeel.library.JwtAuthenticationEntryPoint;
////import security.com.akeel.library.JwtRequestFilter;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////
////    @Autowired
////    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
////
////    @Autowired
////    private UserDetailsService jwtUserDetailsService;
////
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        // configure AuthenticationManager to use the custom UserDetailsService
////        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
////
////    @Override
////    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        // We don't need CSRF for this example
////        httpSecurity.csrf().disable()
////                // dont authenticate this particular request
////                .authorizeRequests().antMatchers("/authenticate", "/register").permitAll()
////                // all other requests need to be authenticated
////                .anyRequest().authenticated().and()
////                // make sure we use stateless session; session won't be used to store user's state.
////                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////
////        // Add a filter to validate the tokens with every request
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////    }
////}
//
//
////
////import security.com.akeel.library.JwtRequestFilter;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
////
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
////        return authConfig.getAuthenticationManager();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.csrf(csrf -> csrf.disable())
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .authorizeHttpRequests(
////                        auth -> auth.requestMatchers("/authenticate").permitAll().anyRequest().authenticated());
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////}
////
////import security.com.akeel.library.JwtAuthenticationEntryPoint;
////import security.com.akeel.library.JwtRequestFilter;
////import service.com.akeel.library.JwtUserDetailsService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////public class WebSecurityConfig  {
////
////    @Autowired
////    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
////
////    @Autowired
////    private JwtUserDetailsService jwtUserDetailsService;
////
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
////
////    @Override
////    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.csrf().disable()
////                .authorizeRequests()
////                .antMatchers(
////                        "/authenticate",
////                        "/register",
////                        "/v2/api-docs",
////                        "/configuration/ui",
////                        "/swagger-resources/**",
////                        "/configuration/security",
////                        "/swagger-ui.html",
////                        "/webjars/**",
////                        "/swagger-ui/**"
////                ).permitAll()
////                .anyRequest().authenticated().and()
////                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////    }
////}
//
//
////import security.com.akeel.library.JwtAuthenticationEntryPoint;
////import security.com.akeel.library.JwtRequestFilter;
////import service.com.akeel.library.JwtUserDetailsService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////public class WebSecurityConfig {
////
////    @Autowired
////    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
////
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
////
////    @Autowired
////    private JwtUserDetailsService jwtUserDetailsService;
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
////        return authConfig.getAuthenticationManager();
////    }
////
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setUserDetailsService(jwtUserDetailsService);
////        authProvider.setPasswordEncoder(passwordEncoder());
////        return authProvider;
////    }
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
////                .authorizeRequests()
////                .requestMatchers(
//////                .antMatchers(
////                        "/authenticate",
////                        "/register",
////                        "/v2/api-docs",
////                        "/configuration/ui",
////                        "/swagger-resources/**",
////                        "/configuration/security",
////                        "/swagger-ui.html",
////                        "/webjars/**",
////                        "/swagger-ui/**"
////                ).permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .exceptionHandling((exception) -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedPage("/error/accedd-denied"))
////                .sessionManagement(session -> session
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////}
//
//import security.com.akeel.library.JwtAuthenticationEntryPoint;
//import security.com.akeel.library.JwtRequestFilter;
//import service.com.akeel.library.JwtUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    private JwtUserDetailsService jwtUserDetailsService;
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
////        return authConfig.getAuthenticationManager();
////    }
////
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(jwtUserDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(
//                        auth -> auth.requestMatchers(
//                                "/swagger-ui/**",
//                                "/swagger-api-docs/**",
//                                "/api/register",
//                                "/authenticate",
//                                "/api/authenticate"
//                        ).permitAll())
//                        .exceptionHandling(e-> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))//.authenticationEntryPoint(jwtAuthenticationEntryPoint)
//        ;
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
////        http.csrf(csrf -> csrf.disable())
////                .authorizeRequests()
////                .requestMatchers(
////                        "/swagger-ui/index.html",
////                        "/authenticate",
////                        "/register",
////                        "/v2/api-docs",
////                        "/configuration/ui",
////                        "/swagger-resources/**",
////                        "/configuration/security",
////                        "/swagger-ui.html",
////                        "/webjars/**",
////                        "/swagger-ui/**"
////                ).permitAll()
////                .anyRequest().authenticated()
//////                .anyRequest().authenticated()
////                .and()
////                .exceptionHandling(e-> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))//.authenticationEntryPoint(jwtAuthenticationEntryPoint)
////
////                .sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
//    }
//}


import com.akeel.library.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                "/swagger-ui/index.html",
                                "/authenticate",
                                "/register",
                                "/v2/api-docs",
                                "/configuration/ui",
                                "/swagger-resources/**",
                                "/configuration/security",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/swagger-ui/**",
                                "/swagger-api-docs/**",
                                "/api/user/register",
                                "/api/user/authenticate"
                               ).permitAll().anyRequest().authenticated());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
