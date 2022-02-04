package keno.swaggerTest.Login;

import keno.swaggerTest.Login.support.UserRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositorySupport {

    User findByUsername(String username);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    @Override @Deprecated
    Page<User> findAllByQueryDto(UserListQueryDto queryDto);

    //@Query("select u from User u limit 10")
    //List<User> selectTenUsers();

}
