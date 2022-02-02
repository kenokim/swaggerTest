package keno.swaggerTest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Simple Controller")
public class SimpleController {

    @Operation(description = "Simple GET api endpoint")
    @GetMapping("/getSimple")
    public ResponseEntity getSimple() {
        System.out.println("hello");
        return new ResponseEntity("ok", HttpStatus.OK);
    }
}
