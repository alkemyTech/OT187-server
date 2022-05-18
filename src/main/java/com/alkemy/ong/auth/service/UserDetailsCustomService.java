package com.alkemy.ong.auth.service;

import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Transactional
@Service
public class UserDetailsCustomService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Value("${app.sendgrid.from}")
    private String MAIL_ONG;

    /*
        Method to register a user with password encryption. Return a UserEntity. And send mail confirm.
     */

    public UserEntity save(UserEntity user) throws IOException {
        UserEntity userE = new UserEntity();
        userE.setFirstName(user.getFirstName());
        userE.setLastName(user.getLastName());
        userE.setEmail(user.getEmail());
        userE.setPassword(passwordEncoder.encode(user.getPassword()));
        userE.setRoleId(user.getRoleId());
        emailService.sendWelcomeEmailTo(MAIL_ONG, user.getEmail());
        userE = this.userRepository.save(userE);

        return userE;

    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("UserEntity not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRoleId().getName()));

        return new org.springframework.security.core.userdetails
                .User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

}
