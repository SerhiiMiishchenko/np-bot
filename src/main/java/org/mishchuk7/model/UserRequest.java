package org.mishchuk7.model;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;

@Builder
@Data
public class UserRequest {
    private Update update;
    private Long chatId;
}
