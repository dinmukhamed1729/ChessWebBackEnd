package ChessWeb.service;

import ChessWeb.game.Board;

import java.util.List;

public interface GameService {
    Long addNewGame(Board board);
    Board getGame(Long id);

    Long newGame(String from, String sendTo);

    Board getGameByUserName(String username);

    void movePiece(String pieceOldPosition, String pieceNewPosition, Long gameId);

    List<String> getPlayers(Long gameId);
}
