package by.academy.repository;

import by.academy.pojo.account.AuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, String> {
    @Query("SELECT A.password FROM AuthenticationInfo A WHERE A.user.id=:userId " +
            "AND A.creationTime=(SELECT MAX(A.creationTime) FROM AuthenticationInfo A WHERE A.user.id=:userId)")
    String findCurrentPasswordByUserId(@Param("userId") String userId);

    @Query("SELECT A.password FROM AuthenticationInfo A WHERE A.user.login=:userLogin " +
            "AND A.creationTime=(SELECT MAX(A.creationTime) FROM AuthenticationInfo A WHERE A.user.login=:userLogin)")
    String findCurrentPasswordByUserLogin(@Param("userLogin") String userLogin);
}
