package ChessWeb.service.impl;

import ChessWeb.entity.User;
import ChessWeb.game.Board;
import ChessWeb.service.GameService;
import ChessWeb.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {
    private Long countOfGames = 0L;
    private Map<Long, Board> games = new HashMap<Long, Board>();
    private final UserService userService;

    public Long addNewGame(Board board) {
        countOfGames++;
        games.put(countOfGames, board);
        return countOfGames;
    }

    public Board getGame(Long id) {
        return games.get(id);
    }

    @Override
    public Long newGame(String from, String sendTo) {
        var board = new Board();

        var gameId = addNewGame(board);
        userService.newGame(from, sendTo, gameId);
        return gameId;
    }

    @Override
    public Board getGameByUserName(String username) {
        return this.getGame(userService.getGameIdByUserName(username));
    }

    @Override
    public void movePiece(String pieceOldPosition, String pieceNewPosition, Long gameId) {
        getGame(gameId).movePiece( pieceOldPosition,  pieceNewPosition);
    }

    @Override
    public List<String> getPlayers(Long gameId) {
        return userService.findByGameId(gameId).stream().map(User::name).toList();
    }
}
