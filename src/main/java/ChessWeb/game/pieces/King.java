package ChessWeb.game.pieces;

import ChessWeb.game.piece_utils.Color;
import ChessWeb.game.piece_utils.Location;
import ChessWeb.game.piece_utils.PieceSymbol;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("King")
public class King extends Piece {
    public King(Location location, Color color, PieceSymbol pieceSymbol) {
        super(location, color, pieceSymbol);
    }

}
