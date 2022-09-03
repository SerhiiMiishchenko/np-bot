package org.mishchuk7.hanlders;

import org.mishchuk7.constants.Constants;
import org.mishchuk7.enums.KeyboardButtons;
import org.mishchuk7.model.InternetDocument;
import org.mishchuk7.model.InternetDocumentRequest;
import org.mishchuk7.model.UserRequest;
import org.mishchuk7.service.TelegramService;

import java.io.IOException;
import java.util.List;

public class CallbackQueryHandler extends UserRequestHandler {
    private final TelegramService telegramService;

    public CallbackQueryHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCallbackQuery(userRequest.getUpdate());
    }

    @Override
    public void handle(UserRequest userRequest) {
        String notFound = "Відправлень не знайдено.";
        String[] callbackData = userRequest.getUpdate().getCallbackQuery().getData().split(":");
        List<InternetDocument> internetDocuments;
        String result = "";
        try {
            String buttonText = callbackData[1];
            String data = InternetDocumentRequest.getUserInput(callbackData[0]);
            internetDocuments = new InternetDocumentRequest().findDocumentByData(data);
            if (buttonText.equalsIgnoreCase(KeyboardButtons.ONE_WAYBILL.getButtonText())) {
                result = internetDocuments.isEmpty() ? notFound : internetDocuments.get(0).toString();
            } else if (buttonText.equalsIgnoreCase(KeyboardButtons.ALL_WAYBILLS.getButtonText())) {
                result = internetDocuments.stream()
                        .map(InternetDocument::toString)
                        .reduce((doc, doc1) -> doc + "\n" + doc1)
                        .orElse(notFound);
            }
            telegramService.sendMessage(userRequest.getChatId(), cutTooLongResult(result));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

    private String cutTooLongResult(String result) {
        int length = Constants.MESSAGE_MAX_LENGTH;
        char[] resultChars = result.toCharArray();
        StringBuilder message = new StringBuilder();
        if (result.length() > length) {
            for (int i = 0; i < length; i++) {
                message.append(resultChars[i]);
            }
            return message.toString();
        }
        return result;
    }

}
