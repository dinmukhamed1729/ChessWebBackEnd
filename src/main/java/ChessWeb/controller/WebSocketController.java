package ChessWeb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;
@RequiredArgsConstructor
@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Map<String, String> processMessageFromClient(String message , Principal principal) {
        return  Map.of("message",message,"principal",principal.getName());
    }

    @MessageMapping("/sendToUserWithUserName")
    public void sendMessageWithUserName(@Payload Map<String, String> payload) {
        String message = payload.get("message");
        String sendTo = payload.get("sendTo");
        messagingTemplate.convertAndSendToUser(sendTo, "/queue/private", message);
    }
}
