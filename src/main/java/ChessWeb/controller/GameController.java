package ChessWeb.controller;

import ChessWeb.game.Board;
import ChessWeb.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    @GetMapping("/newBoard")
    public Map<String, Object> newBoard() {
        var x = new Board();
        var map = new HashMap<String, Object>();
        map.put("board",x);
        map.put("gameId",gameService.addNewGame(x));
        return map;
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        System.out.println(id);
        return ResponseEntity.ok(gameService.getGame(id));
    }
    @PostMapping("/registerGame")
    public ResponseEntity<Map<String, Long>> registerGame(@RequestBody Map<String, String> names) {
        log.info("registerGame {} {}", names.get("from"), names.get("sendTo"));
        return ResponseEntity.ok(Map.of(  "gameId",  gameService.newGame(names.get("from"), names.get("sendTo"))));
    }
}
