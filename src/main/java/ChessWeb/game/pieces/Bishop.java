package ChessWeb.game.pieces;

import ChessWeb.game.piece_utils.Color;
import ChessWeb.game.piece_utils.Location;
import ChessWeb.game.piece_utils.PieceSymbol;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Generated
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("Bishop")
public class Bishop extends Piece {

    public Bishop(Location location, Color color, PieceSymbol pieceSymbol) {
        super(location, color, pieceSymbol);
    }

}
