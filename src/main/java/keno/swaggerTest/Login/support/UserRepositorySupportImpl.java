package keno.swaggerTest.Login.support;

import keno.swaggerTest.Login.User;
import keno.swaggerTest.Login.UserListQueryDto;
import keno.swaggerTest.Login.UserSortEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositorySupportImpl implements UserRepositorySupport {

    @PersistenceContext
    EntityManager em;

    @Override
    public Page<User> findAllByQueryDto(UserListQueryDto queryDto) {
        switch (queryDto.getSortBy()) {
            case "username":

                break;
            default: // "new"

                break;
        }

        return null;
    }



    /*@Override
    public Page<User> findAllByQueryDto(UserListQueryDto queryDto) {
        Pageable pageable = queryDto.getPageRequest();
        QUser user = QUser.user;

    }*/
}
