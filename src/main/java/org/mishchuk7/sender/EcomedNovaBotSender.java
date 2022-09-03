package org.mishchuk7.sender;

import org.mishchuk7.constants.Constants;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class EcomedNovaBotSender extends DefaultAbsSender {
    public EcomedNovaBotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }
}
