package org.mishchuk7;

import lombok.extern.slf4j.Slf4j;
import org.mishchuk7.constants.Constants;
import org.mishchuk7.enums.KeyboardButtons;
import org.mishchuk7.keyboards.InlineKeyboardsMaker;
import org.mishchuk7.model.InternetDocument;
import org.mishchuk7.model.InternetDocumentRequest;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.mishchuk7.constants.Constants.BOT_NAME;
import static org.mishchuk7.constants.Constants.BOT_TOKEN;

@Slf4j
public class EcomedNovaBot extends TelegramLongPollingBot {

    private final InternetDocumentRequest request;

    public EcomedNovaBot() {
        this.request = new InternetDocumentRequest();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String result;
        SendMessage message;

        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();
            Long userId = update.getMessage().getChatId();
            Long chatId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();
            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);
            try {
                wrightUserInputToFile(textFromUser);
                message = SendMessage.builder()
                        .chatId(chatId)
                        .text("Оберіть варіант звіту: ")
                        .replyMarkup(InlineKeyboardsMaker.getInlineKeyboardMarkup(textFromUser))
                        .build();
                message.getEntities();
                this.sendApiMethod(message);
            } catch (IOException e) {
                log.error("Exception during the write file");
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasCallbackQuery()) {
            String callback = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getFrom().getId();
            try {
                result = buttonListener(callback);
                message = SendMessage.builder()
                        .chatId(chatId)
                        .text(result)
                        .build();
                message.getEntities();
                this.sendApiMethod(message);
            } catch (IOException | InterruptedException | TelegramApiException e) {
                log.error("Exception when sending message: ", e);
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }

    private String buttonListener(String callBackData) throws IOException, InterruptedException {
        String findByString = readUserInputFile();
        List<InternetDocument> internetDocuments = request.findDocumentByData(findByString);
        String notFound = "Відправлень не знайдено.";
        if (callBackData.equalsIgnoreCase(KeyboardButtons.ONE_WAYBILL.getButtonText())) {
            return internetDocuments.isEmpty() ? notFound : internetDocuments.get(0).toString();
        } else if (callBackData.equalsIgnoreCase(KeyboardButtons.ALL_WAYBILLS.getButtonText())) {
            return internetDocuments.stream()
                    .map(InternetDocument::toString)
                    .reduce((doc, doc1) -> doc + "\n" + doc1)
                    .orElse(notFound);
        }
        return notFound;
    }

    private String readUserInputFile() throws IOException {
        return Files.readAllLines(Path.of(Constants.PATH_TO_USER_INPUT)).get(0);
    }

    private void wrightUserInputToFile(String textFromUser) throws IOException {
        Path path = Paths.get(Constants.PATH_TO_USER_INPUT);
        Files.write(path, textFromUser.getBytes(StandardCharsets.UTF_8));
    }
}
