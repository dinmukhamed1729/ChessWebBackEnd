package ChessWeb.game.pieces;

import ChessWeb.game.piece_utils.Color;
import ChessWeb.game.piece_utils.Location;
import ChessWeb.game.piece_utils.PieceSymbol;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("Rook")
public class Rook extends Piece {
    public Rook(Location location, Color color, PieceSymbol pieceSymbol) {
        super(location, color, pieceSymbol);
    }
}
