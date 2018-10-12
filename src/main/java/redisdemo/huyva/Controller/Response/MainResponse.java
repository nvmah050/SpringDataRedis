package redisdemo.huyva.Controller.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainResponse<T> {
    private String code;
    private String msg;
    private T data;

    public MainResponse(String code, String msg){
        this.code=  code;
        this.msg = msg;
    }

}
