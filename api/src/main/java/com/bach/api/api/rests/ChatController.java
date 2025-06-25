package com.bach.api.api.rests;


import org.springframework.stereotype.Controller;


@Controller

public class ChatController {
    public String showChatPage() {
        return "chat";
    }
}
