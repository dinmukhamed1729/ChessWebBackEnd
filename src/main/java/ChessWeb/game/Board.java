package ChessWeb.game;

import ChessWeb.game.piece_utils.Color;
import ChessWeb.game.piece_utils.Horizontal;
import ChessWeb.game.piece_utils.Location;
import ChessWeb.game.piece_utils.PieceSymbol;
import ChessWeb.game.pieces.*;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Board {
    private Map<Location, Piece> pieces;

    public Board() {
        initDefaultFields();
    }

    private void initDefaultFields() {
        pieces = Stream.of(new Bishop(new Location(Horizontal.c, 1), Color.WHITE, PieceSymbol.WHITE_BISHOP),
                        new Bishop(new Location(Horizontal.f, 1), Color.WHITE, PieceSymbol.WHITE_BISHOP),
                        new Bishop(new Location(Horizontal.c, 8), Color.BLACK, PieceSymbol.BLACK_BISHOP),
                        new Bishop(new Location(Horizontal.f, 8), Color.BLACK, PieceSymbol.BLACK_BISHOP),

                        new King(new Location(Horizontal.e, 1), Color.WHITE, PieceSymbol.WHITE_KING),
                        new King(new Location(Horizontal.e, 8), Color.BLACK, PieceSymbol.BLACK_KING),

                        new Knight(new Location(Horizontal.b, 1), Color.WHITE, PieceSymbol.WHITE_KNIGHT),
                        new Knight(new Location(Horizontal.g, 1), Color.WHITE, PieceSymbol.WHITE_KNIGHT),
                        new Knight(new Location(Horizontal.b, 8), Color.BLACK, PieceSymbol.BLACK_KNIGHT),
                        new Knight(new Location(Horizontal.g, 8), Color.BLACK, PieceSymbol.BLACK_KNIGHT),

                        new Rook(new Location(Horizontal.a, 1), Color.BLACK, PieceSymbol.WHITE_ROOK),
                        new Rook(new Location(Horizontal.h, 1), Color.BLACK, PieceSymbol.WHITE_ROOK),
                        new Rook(new Location(Horizontal.a, 8), Color.BLACK, PieceSymbol.BLACK_ROOK),
                        new Rook(new Location(Horizontal.h, 8), Color.BLACK, PieceSymbol.BLACK_ROOK),

                        new Queen(new Location(Horizontal.d, 8), Color.BLACK, PieceSymbol.BLACK_QUEEN),
                        new Queen(new Location(Horizontal.d, 1), Color.WHITE, PieceSymbol.WHITE_QUEEN),

                        new Pawn(new Location(Horizontal.a, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),
                        new Pawn(new Location(Horizontal.b, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),
                        new Pawn(new Location(Horizontal.c, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),
                        new Pawn(new Location(Horizontal.d, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),
                        new Pawn(new Location(Horizontal.e, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),
                        new Pawn(new Location(Horizontal.f, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),
                        new Pawn(new Location(Horizontal.g, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),
                        new Pawn(new Location(Horizontal.h, 2), Color.WHITE, PieceSymbol.WHITE_PAWN),

                        new Pawn(new Location(Horizontal.a, 7), Color.BLACK, PieceSymbol.BLACK_PAWN),
                        new Pawn(new Location(Horizontal.b, 7), Color.BLACK, PieceSymbol.BLACK_PAWN),
                        new Pawn(new Location(Horizontal.c, 7), Color.BLACK, PieceSymbol.BLACK_PAWN),
                        new Pawn(new Location(Horizontal.d, 7), Color.BLACK, PieceSymbol.BLACK_PAWN),
                        new Pawn(new Location(Horizontal.e, 7), Color.BLACK, PieceSymbol.BLACK_PAWN),
                        new Pawn(new Location(Horizontal.f, 7), Color.BLACK, PieceSymbol.BLACK_PAWN),
                        new Pawn(new Location(Horizontal.g, 7), Color.BLACK, PieceSymbol.BLACK_PAWN),
                        new Pawn(new Location(Horizontal.h, 7), Color.BLACK, PieceSymbol.BLACK_PAWN))
                .collect(Collectors.toMap(Piece::getLocation, x -> x));
    }


    public void movePiece(String pieceOldPosition, String pieceNewPosition) {
        Location newP = new Location(pieceNewPosition);
        Location oldP = new Location(pieceOldPosition);

        pieces.put(newP,pieces.remove(oldP));

        System.out.println(pieces.get(newP));
    }
}
