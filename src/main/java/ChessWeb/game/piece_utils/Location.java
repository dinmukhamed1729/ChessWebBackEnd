package ChessWeb.game.piece_utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Location {
    private Horizontal y;
    private Integer x;

    public  Location(String loc) {
        String[] yx = loc.split(" ");
        this.y = Horizontal.valueOf(yx[0]);
        this.x = Integer.parseInt(yx[1]);
    }

    @JsonValue
    public String getJsonValue(){
        return y.name()+" " +x;
    }
    @JsonCreator
    public static Location fromJsonValue(String jsonValue) {
        String[] yx = jsonValue.split(" ");
        Horizontal y = Horizontal.valueOf(yx[0]);
        Integer x = Integer.parseInt(yx[1]);
        return new Location(y, x);
    }
}
