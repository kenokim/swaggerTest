package keno.swaggerTest.Login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Register a new user")
    @ApiResponse(responseCode = "400", description = "User with username already exists")
    @Parameters
    @PostMapping("/user/register")
    public ResponseEntity register(@RequestBody UserFormDto dto) {
        userService.save(dto.getUsername(), dto.getPassword());
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "success", content = {@Content(mediaType = "application/json", schema= @Schema(implementation = UserListDto.class))})    @GetMapping("/user/allUsers")
    public UserListDto getAllUsers(@RequestBody(required = false) UserListQueryDto dto) {
        log.info("somebody's accessed get all users");
        if (dto == null) dto = new UserListQueryDto();
        return new UserListDto(userService.findAllPaged(dto));
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity illegalStateExceptionHandler(IllegalStateException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Data
    static class UserFormDto {
        @NotBlank @Size(min=0, max=20)
        private String username;
        @NotBlank @Size(min=0, max=20)
        private String password;
    }

    @Data
    static class UserListDto {
        private Long numUsers;
        private List<UserDto> users;

        UserListDto(Page<User> users) {
            this.numUsers = users.getTotalElements();
            this.users = users.getContent().stream().map(UserDto::new).toList();
        }
    }

    @Data
    static class UserDto {
        private Long id;
        private String username;

        UserDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
        }
    }
}
