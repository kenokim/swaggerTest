package keno.swaggerTest.Login;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class UserListQueryDto {
    private Integer page = 0;
    private Integer pageSize = 10; // default; get 10 users
    private String sortBy = "new";

    public PageRequest getPageRequest() {
        return PageRequest.of(page, pageSize);
    }

    // Consider refactor to Formatter / Converter
    /*public Enum<UserSortEnum> getSortBy() {
        switch (sortBy) {
            case "username":
                return UserSortEnum.USERNAME;
            default:
                return UserSortEnum.NEW;
        }
    }*/
}
