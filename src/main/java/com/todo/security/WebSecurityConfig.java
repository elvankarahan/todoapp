package com.todo.security;

import com.todo.security.jwt.AuthEntryPointJwt;
import com.todo.security.jwt.AuthTokenFilter;
import com.todo.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable()
//      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//      .antMatchers("/api/test/**").permitAll().antMatchers("/v3/api-docs",
//            "/swagger-ui.html",
//            "/swagger-ui/**").permitAll()
//      .anyRequest().authenticated();
        //***///
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/test/**").permitAll()
//                .antMatchers("/v3/api-docs","/swagger-ui.html","/swagger-ui/**").permitAll()
//                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/api/test/**").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//                .anyRequest().authenticated();
//        http.headers().frameOptions().disable();
//
//        //http.antMatcher("/**").authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
//        http.cors().and().csrf().disable();
//
//        //.antMatchers("/h2-console/**").permitAll()
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.cors()
                .and().authorizeRequests()
                .anyRequest().permitAll()
                .and().csrf().disable();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        http.cors();
//        //web.ignoring().antMatchers("/h2-console*/**");
////        web.ignoring().antMatchers("/v3/api-docs",
////                "/swagger-ui.html",
////                "/swagger-ui/**");
//
////        http.antMatcher("/actuator/**").and()
////                .authorizeRequests().anyRequest().permitAll();
//    }




    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

