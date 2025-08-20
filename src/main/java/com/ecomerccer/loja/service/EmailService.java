package com.ecomerccer.loja.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviaemail(String para, String assunto, String corpoHtml) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            // true = multipart, "UTF-8" garante acentos
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(para);
            helper.setSubject(assunto);

            // 👇 ESSA LINHA FAZ O CONTEÚDO SER HTML
            helper.setText(corpoHtml, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }
}
