package focandlol.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("00","SUCCESS");

    private final String code;
    private final String message;
}
