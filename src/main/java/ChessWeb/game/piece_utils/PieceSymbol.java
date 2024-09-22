package ChessWeb.game.piece_utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PieceSymbol {
    WHITE_KING('\u2654'),
    WHITE_QUEEN('\u2655'),
    WHITE_ROOK('\u2656'),
    WHITE_BISHOP('\u2657'),
    WHITE_KNIGHT('\u2658'),
    WHITE_PAWN('\u2659'),

    BLACK_KING('\u265A'),
    BLACK_QUEEN('\u265B'),
    BLACK_ROOK('\u265C'),
    BLACK_BISHOP('\u265D'),
    BLACK_KNIGHT('\u265E'),
    BLACK_PAWN('\u265F');

    private final char symbol;

    PieceSymbol(char symbol) {
        this.symbol = symbol;
    }
    @JsonValue
    public char getSymbol() {
        return symbol;
    }

    @JsonCreator
    public static PieceSymbol forSymbol(char symbol) {
        for (PieceSymbol pieceSymbol : values()) {
            if (pieceSymbol.getSymbol() == symbol) {
                return pieceSymbol;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

}
