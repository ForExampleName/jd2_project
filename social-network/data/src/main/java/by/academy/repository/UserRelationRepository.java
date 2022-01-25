package by.academy.repository;

import by.academy.pojo.relation.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, String> {

    @Query("SELECT R FROM UserRelation R WHERE R.user.id=:userId AND R.follow.id=:followId")
    Optional<UserRelation> findByUserIdAndFollowId(@Param("userId") String userId, @Param("followId") String followId);

    @Query("SELECT R FROM UserRelation R WHERE R.user.id=:userId")
    List<UserRelation> getUserRelationsByUserId(@Param("userId") String userId);
}
