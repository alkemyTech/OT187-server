package com.alkemy.ong.security;

import com.alkemy.ong.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
import static com.alkemy.ong.utility.Constantes.TESTIMONIAL_URL;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
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
        httpSecurity.cors().and().csrf().disable()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

                //Organization
                .antMatchers(HttpMethod.POST, ORGANIZATION_MAP_REQUEST + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, ORGANIZATION_MAP_REQUEST + "/*").permitAll()
                .antMatchers(HttpMethod.PUT, ORGANIZATION_MAP_REQUEST + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, ORGANIZATION_MAP_REQUEST + REQUEST_ID).hasAnyAuthority("ADMIN")

                //News
                .antMatchers(HttpMethod.GET, NEWS_URL + "/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, NEWS_URL).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, NEWS_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, NEWS_URL + REQUEST_ID).hasAnyAuthority("ADMIN")

                //Activity
                .antMatchers(HttpMethod.GET, ACTIVITY_URL + "/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, ACTIVITY_URL).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, ACTIVITY_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, ACTIVITY_URL + REQUEST_ID).hasAnyAuthority("ADMIN")

                //AmazonS3
                .antMatchers(HttpMethod.POST,AWS_STORAGE_REQUEST + AWS_UPLOAD_FILE).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,AWS_STORAGE_REQUEST + AWS_DELETE_FILE).hasAnyAuthority("ADMIN")
                
                //Contacts
                .antMatchers(HttpMethod.POST, CONTACT_URL).permitAll()
                .antMatchers(HttpMethod.GET, CONTACT_URL).hasAnyAuthority("ADMIN")
                
                //Category
                .antMatchers(HttpMethod.GET, CATEGORY_URL + "/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, CATEGORY_URL).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, CATEGORY_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, CATEGORY_URL + REQUEST_ID).hasAnyAuthority("ADMIN")

                //Users
                .antMatchers(HttpMethod.GET, USER_GET).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, USER_PATCH).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, USER_AUTH_ME).hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, USER_REGISTER).permitAll()
                .antMatchers(HttpMethod.POST, USER_LOGIN).permitAll()
                
                //Members
                .antMatchers(HttpMethod.GET, MEMBER_URL + "/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, MEMBER_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, MEMBER_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, MEMBER_URL).hasAnyAuthority("ADMIN")

                //Testimonials
                .antMatchers(HttpMethod.GET, TESTIMONIAL_URL).hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, TESTIMONIAL_URL).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, TESTIMONIAL_URL + REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, TESTIMONIAL_URL + REQUEST_ID).hasAnyAuthority("ADMIN")

                //Comments
                .antMatchers(HttpMethod.GET, COMMENT_URL + "/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, COMMENT_URL +  REQUEST_ID).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, COMMENT_URL + REQUEST_ID).hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, COMMENT_URL).hasAnyAuthority("ADMIN", "USER")

                //OpenApi
                .antMatchers(SWAGGER_URL).permitAll()
                
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}