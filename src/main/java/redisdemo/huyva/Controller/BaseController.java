package redisdemo.huyva.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import redisdemo.huyva.Controller.Response.MainResponse;

public class BaseController {
    public ResponseEntity<MainResponse> returnSuccess(MainResponse response){
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
