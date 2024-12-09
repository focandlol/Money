package focandlol.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {
    private T data;
    private final String responseCode;
    private final String responseMessage;

    public CustomResponse(T data, ResponseCode responseCode) {
        this.data = data;
        this.responseCode = responseCode.getCode();
        this.responseMessage = responseCode.getMessage();
    }

    public CustomResponse(ResponseCode responseCode) {
        this.responseCode = responseCode.getCode();
        this.responseMessage = responseCode.getMessage();
    }
}
