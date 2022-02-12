package keno.swaggerTest.Login.support;

import keno.swaggerTest.Login.User;
import keno.swaggerTest.Login.UserListQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorySupport {
    Page<User> findAllByQueryDto(UserListQueryDto queryDto);
}
