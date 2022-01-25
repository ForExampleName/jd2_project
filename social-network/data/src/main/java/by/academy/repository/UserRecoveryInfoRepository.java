package by.academy.repository;

import by.academy.pojo.account.UserRecoveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecoveryInfoRepository extends JpaRepository<UserRecoveryInfo, String> {
    @Query("SELECT U.question.question FROM UserRecoveryInfo U WHERE U.user.id=:userId")
    String findSecretQuestionByUserId(@Param("userId") String userId);

    @Query("SELECT U.answer FROM UserRecoveryInfo U WHERE U.user.id=:userId")
    String findAnswerByUserId(@Param("userId") String userId);
}
