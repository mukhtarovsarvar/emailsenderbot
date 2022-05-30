package com.company.GeneratePromoKod.service;

import com.company.GeneratePromoKod.dto.EmailDto;
import com.company.GeneratePromoKod.entity.EmailEntity;
import com.company.GeneratePromoKod.repository.EmailRepository;
import com.sun.mail.smtp.SMTPAddressFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final EmailRepository repository;

    public void send(String toEmail, String title, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(title);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setText(content, true);
            create(toEmail,title,content);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e.toString());        }
    }

    public void create(String toEmail, String title, String content) {
        EmailEntity entity = new EmailEntity();
        entity.setToEmail(toEmail);
        entity.setContent(content);
        entity.setTitle(title);
        repository.save(entity);
    }

    public List<EmailDto> getList(PageRequest of) {
        List<EmailDto> list = new LinkedList<>();
        repository.findAll(of).forEach(emailEntity -> {
            list.add(toDo(emailEntity));
        });
        return list;
    }

    private EmailDto toDo(EmailEntity emailEntity) {
        EmailDto dto = new EmailDto();
        dto.setToEmail(emailEntity.getToEmail());
        dto.setContent(emailEntity.getContent());
        dto.setTitle(emailEntity.getTitle());
        dto.setId(emailEntity.getId());

        return dto;
    }

    public String delete(Integer id) {
        repository.findById(id).orElseThrow(() -> {
            return null;
        });
        repository.deleteById(id);
        return "Success";
    }
}
