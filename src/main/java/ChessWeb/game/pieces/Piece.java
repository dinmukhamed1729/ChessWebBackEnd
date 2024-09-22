package ChessWeb.game.pieces;

import ChessWeb.game.piece_utils.Color;
import ChessWeb.game.piece_utils.Location;
import ChessWeb.game.piece_utils.PieceSymbol;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Piece {
    protected Location location;
    protected Color color;
    protected PieceSymbol pieceSymbol;
}
