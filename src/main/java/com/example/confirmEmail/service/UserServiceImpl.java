package com.example.confirmEmail.service;

import com.example.confirmEmail.model.ConfirmToken;
import com.example.confirmEmail.model.User;
import com.example.confirmEmail.repository.ConfirmRepository;
import com.example.confirmEmail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmRepository confirmRepository;
    private final MailSender mailSender;
    private final String confirmUrl;

    public UserServiceImpl(UserRepository userRepository,
                           ConfirmRepository confirmRepository,
                           MailSender mailSender,
                           @Value("${confirm.url}") String confirmUrl) {
        this.userRepository = userRepository;
        this.confirmRepository = confirmRepository;
        this.mailSender = mailSender;
        this.confirmUrl = confirmUrl;
    }

    @Override
    public ResponseEntity<?> saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use.");
        }
        User userWithId = userRepository.save(user);
        ConfirmToken confirmToken = new ConfirmToken(userWithId);
        confirmRepository.save(confirmToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Continue Registration");
        mailMessage.setText("To confirm your account, please click here:"
                + confirmUrl + confirmToken.getToken());
        System.out.println("Начинаю отправку письма");
        mailSender.send(mailMessage);
        System.out.println("закончил отправку");

        System.out.println("Confirm Token:" + confirmToken.getToken());
        return ResponseEntity.ok("Verify link sent on your email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String token) {
        ConfirmToken confirmToken = confirmRepository.findByToken(token);

        if (confirmToken != null) {
            User user = userRepository.findByEmailIgnoreCase(confirmToken.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: couldn't verify email");
    }
}
