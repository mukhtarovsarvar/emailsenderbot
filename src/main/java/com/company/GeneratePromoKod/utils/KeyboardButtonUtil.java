package com.company.GeneratePromoKod.utils;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;

public class KeyboardButtonUtil {
    public static KeyboardButton buttonEmoji(String text, String emoji) {
        String emojiText = EmojiParser.parseToUnicode(emoji + " " + text);
        return new KeyboardButton(emojiText);
    }

    public static KeyboardButton button(String text) {
        return new KeyboardButton(text);
    }

    public static ReplyKeyboard userMenu() {
        KeyboardButton sovolla = KeyboardButtonUtil.buttonEmoji("Emailga habar yuborish", "💨💌");


        KeyboardRow row = new KeyboardRow();
        row.add(sovolla);


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(row));
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
