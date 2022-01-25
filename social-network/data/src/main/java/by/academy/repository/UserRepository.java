package by.academy.repository;

import by.academy.pojo.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);

    User findUserByLogin(String login);

    @Query("SELECT U.id FROM User U WHERE U.id<>:userId")
    List<String> findAllUserIdsExceptUserId(@Param("userId") String userId);
}
