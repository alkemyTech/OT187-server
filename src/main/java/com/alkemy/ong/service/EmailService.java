
package com.alkemy.ong.service;

import java.io.IOException;

public interface EmailService {
    void send(String sendTo, String subject_email, String template) throws IOException;

    void sendWelcomeEmailTo(String from ,String to) throws IOException;
}
