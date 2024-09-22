package ChessWeb.game.piece_utils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Color {
    WHITE,BLACK;
    @JsonCreator
    public static Color fromString(String key) {
        return Color.valueOf(key.toUpperCase());
    }
}
