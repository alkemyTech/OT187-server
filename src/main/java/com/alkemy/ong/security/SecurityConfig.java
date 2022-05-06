package com.alkemy.ong.security;

import com.alkemy.ong.security.filter.JwtRequestFilter;
import com.alkemy.ong.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.alkemy.ong.utility.Constantes.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()

                //Organization
                .antMatchers(HttpMethod.POST, ORGANIZATION_MAP_REQUEST + REQUEST_ID).hasAnyAuthority("ADMIN")
        
                //News
                .antMatchers(HttpMethod.GET, NEWS_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, NEWS_URL).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, NEWS_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, NEWS_URL + REQUEST_ID).hasAnyAuthority("ADMIN")

                //Activities
                .antMatchers(HttpMethod.POST, ACTIVITY_URL).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, ACTIVITY_URL + REQUEST_ID).hasAnyAuthority("ADMIN")

                //AmazonS3
                .antMatchers(AWS_STORAGE_REQUEST + "*").hasAnyAuthority("ADMIN")

                //Users
                .antMatchers(HttpMethod.GET, USER_GET).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, USER_PATCH).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, USER_AUTH_ME).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST ,USER_REGISTER).permitAll()


                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


}