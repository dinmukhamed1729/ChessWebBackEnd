package ChessWeb.controller;

import ChessWeb.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.security.Principal;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final GameService gameService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Map<String, String> processMessageFromClient(String message, Principal principal) {
        return Map.of("message", message, "principal", principal.getName());
    }

    @MessageMapping("/sendToUserWithUserName")
    public void sendMessageWithUserName(@Payload Map<String, String> payload) {
        var message = Map.of(
                "message", payload.get("message"),
                "from", payload.get("from"),
                "sendTo", payload.get("sendTo"),
                "COMMAND", payload.get("COMMAND"),
                "gameId", payload.get("gameId"));
        messagingTemplate.convertAndSendToUser(payload.get("sendTo"), "/queue/private", message);
    }

    @MessageMapping("/GameSocket")
    public void gameSocket(@Payload Map<String, String> map) {
        System.out.println("GameId ======" + map.get("gameId"));
        String pieceNewPosition = map.get("pieceNewPosition");
        String pieceOldPosition = map.get("pieceOldPosition");
        Long gameId = Long.parseLong(map.get("gameId"));
        gameService.movePiece(pieceOldPosition, pieceNewPosition, gameId);
        messagingTemplate.convertAndSendToUser(map.get("opponent"), "/queue/private", map);
    }

    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            String destination = headerAccessor.getDestination();
            String sessionId = headerAccessor.getSessionId();
            String userName = headerAccessor.getUser() != null ? headerAccessor.getUser().getName() : "Unknown";

            // Логика при подписке на определённый destination
            System.out.println("User " + userName + " subscribed to " + destination);
        }
    }
}
