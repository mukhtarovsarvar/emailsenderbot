package com.company.GeneratePromoKod;

import com.company.GeneratePromoKod.controller.MainController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    @Autowired
    @Lazy
    private  MainController mainController;
    @Override
    public String getBotUsername() {
        return "@email_sender_facebot";
    }

    @Override
    public String getBotToken() {
        return "5315210355:AAFPCdAEdfjPsMs3nillZv-cQYIAwYDo1lg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            User user = message.getFrom();
            if (message.hasText()) {
                log(user, message.getText());
                mainController.handleText(user, message);
            }
        }


    }

    public void send(Object obj) {
        try {
            if (obj instanceof SendMessage) {
                execute((SendMessage) obj);
            } else if (obj instanceof SendPhoto) {
                execute((SendPhoto) obj);
            } else if (obj instanceof SendVideo) {
                execute((SendVideo) obj);
            } else if (obj instanceof SendLocation) {
                execute((SendLocation) obj);
            } else if (obj instanceof SendContact) {
                execute((SendContact) obj);
            } else if (obj instanceof EditMessageText) {
                execute((EditMessageText) obj);
            } else if (obj instanceof SendDocument) {
                execute((SendDocument) obj);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void log(User user, String text) {
        String str = String.format(LocalDateTime.now() + ",  userId: %d, firstName: %s, lastName: %s, text: %s",
                user.getId(), user.getFirstName(), user.getLastName(), text);
        System.out.println(str);
    }
}
