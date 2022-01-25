package by.academy.repository;

import by.academy.enums.AccountStatus;
import by.academy.pojo.account.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, String> {
    @Query("SELECT U.status FROM UserStatus U WHERE U.user.id=:userId " +
            "AND U.creationTime=(SELECT MAX(U.creationTime) FROM UserStatus U WHERE U.user.id=:userId)")
    AccountStatus findCurrentAccountStatusByUserId(@Param("userId") String userId);

    @Query("SELECT U.status FROM UserStatus U WHERE U.user.login=:userLogin " +
            "AND U.creationTime=(SELECT MAX(U.creationTime) FROM UserStatus U WHERE U.user.login=:userLogin)")
    AccountStatus findCurrentAccountStatusByUserLogin(@Param("userLogin") String userLogin);

    @Query(value =
            "SELECT s.user_id FROM user_status s " +
                    "JOIN (SELECT user_id AS id, MAX(creation_time) AS time FROM user_status GROUP BY user_id ) sub " +
                    "ON s.user_id=sub.id AND s.creation_time=sub.time WHERE s.status!='BLOCKED'",
            nativeQuery = true)
    List<String> findAllActiveUserAccountIds();

    @Query(value =
            "SELECT s.user_id FROM user_status s " +
                    "JOIN (SELECT user_id AS id, MAX(creation_time) AS time FROM user_status GROUP BY user_id) sub " +
                    "ON s.user_id=sub.id AND s.creation_time=sub.time WHERE s.status!='BLOCKED' AND s.user_id!=:userId",
            nativeQuery = true)
    List<String> findAllActiveUserAccountIdsExceptUserId(@Param("userId") String userId);
}
