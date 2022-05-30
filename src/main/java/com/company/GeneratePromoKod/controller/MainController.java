package com.company.GeneratePromoKod.controller;

import com.company.GeneratePromoKod.MyTelegramBot;
import com.company.GeneratePromoKod.dto.EmailSendDTO;
import com.company.GeneratePromoKod.dto.ProfileDTO;
import com.company.GeneratePromoKod.entity.CurrentUser;
import com.company.GeneratePromoKod.enums.UserStatus;
import com.company.GeneratePromoKod.service.CurrentUserService;
import com.company.GeneratePromoKod.service.EmailService;
import com.company.GeneratePromoKod.service.ProfileService;
import com.company.GeneratePromoKod.utils.KeyboardButtonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Lazy
    private final MyTelegramBot myTelegramBot;
    private final ProfileService profileService;
    private final EmailService emailService;

    Map<String, EmailSendDTO> currentUserMap = new HashMap<>();

    public void handleText(User user, Message message) {
        String text = message.getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.getId()));
        EmailSendDTO currentStatus = currentUserMap.get(String.valueOf(user.getId()));

        if (text.equals("/start")) {
            profileService.saveCheck(new ProfileDTO(String.valueOf(user.getId()), user.getFirstName()));
            sendMessage.setText("click button");
            ReplyKeyboard replyKeyboard = KeyboardButtonUtil.userMenu();
            sendMessage.setReplyMarkup(replyKeyboard);
            myTelegramBot.send(sendMessage);
        } else if (text.equals("\uD83D\uDCA8\uD83D\uDC8C Emailga habar yuborish")) {
            ReplyKeyboard replyKeyboard = KeyboardButtonUtil.userMenu();
            sendMessage.setReplyMarkup(replyKeyboard);
            EmailSendDTO dto = new EmailSendDTO();
            dto.setStatus(UserStatus.EMAILSENDING);
            currentUserMap.put(String.valueOf(user.getId()),dto);
            sendMessage.setText("emailni kiriting >>");
            myTelegramBot.send(sendMessage);
        } else if(currentStatus != null) {
            if (currentStatus.getStatus().equals(UserStatus.EMAILSENDING)) {
                sendMessage.setText("textni kiriting >>");
                myTelegramBot.send(sendMessage);
                currentStatus.setEmail(text);
                currentStatus.setStatus(UserStatus.TEXTWRITING);
                currentUserMap.put(String.valueOf(user.getId()),currentStatus);
            } else if (currentStatus.getStatus().equals(UserStatus.TEXTWRITING)) {
                currentUserMap.remove(String.valueOf(user.getId()));
                emailService.send(currentStatus.getEmail(), "Hello", text);
                sendMessage.setText("Yuborildi!");
                myTelegramBot.send(sendMessage);
            }
        }

    }
}
