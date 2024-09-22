package ChessWeb.game.pieces;

import ChessWeb.game.piece_utils.Color;
import ChessWeb.game.piece_utils.Location;
import ChessWeb.game.piece_utils.PieceSymbol;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("Pawn")
public class Pawn extends Piece {

    public Pawn(Location location, Color color, PieceSymbol pieceSymbol) {
        super(location, color, pieceSymbol);
    }

}
