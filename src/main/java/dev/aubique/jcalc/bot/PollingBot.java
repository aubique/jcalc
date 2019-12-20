package dev.aubique.jcalc.bot;

import dev.aubique.jcalc.core.Parser;
import dev.aubique.jcalc.util.ConfigLoader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PollingBot extends TelegramLongPollingBot {

    private static PollingBot instance = null;
    private Parser model;

    private PollingBot() {
        model = new Parser();
    }

    public static PollingBot getInstance() {
        if (instance == null) {
            instance = new PollingBot();
        }
        return instance;
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = model.computeExpression(update.getMessage().getText());
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    @Override
    public String getBotUsername() {
        return ConfigLoader.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return ConfigLoader.getBotToken();
    }
}
