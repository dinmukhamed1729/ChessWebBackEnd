package ChessWeb.game.pieces;

import ChessWeb.game.piece_utils.Color;
import ChessWeb.game.piece_utils.Location;
import ChessWeb.game.piece_utils.PieceSymbol;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("Queen")
public class Queen extends Piece{
    public Queen(Location location, Color color, PieceSymbol pieceSymbol) {
        super(location, color, pieceSymbol);
    }

}
