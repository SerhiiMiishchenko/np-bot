package org.mishchuk7;

import org.mishchuk7.hanlders.UserRequestHandler;
import org.mishchuk7.model.UserRequest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Dispatcher {
    private final List<UserRequestHandler> handlers;

    public Dispatcher(List<UserRequestHandler> handlers) {
        this.handlers = handlers.stream()
                .sorted(Comparator.comparing(UserRequestHandler::isGlobal)
                        .reversed())
                .collect(Collectors.toList());
    }

    public boolean dispatch(UserRequest userRequest) {
        for (UserRequestHandler userRequestHandler : handlers) {
            if (userRequestHandler.isApplicable(userRequest)) {
                userRequestHandler.handle(userRequest);
                return true;
            }
        }
        return false;
    }
}
