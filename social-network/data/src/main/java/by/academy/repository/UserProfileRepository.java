package by.academy.repository;

import by.academy.pojo.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    @Query("SELECT U FROM UserProfile U WHERE U.user.id=:userId")
    UserProfile findUserProfileByUserId(@Param("userId") String userId);

    @Query("SELECT P.avatar FROM UserProfile P WHERE P.user.id=:userId " +
            "AND P.creationTime=(SELECT MAX(UP.creationTime) FROM UserProfile UP WHERE UP.user.id=:userId)")
    byte[] findUserAvatarByUserId(@Param("userId") String userId);

    @Query("SELECT P FROM UserProfile P WHERE P.user.id=:userId " +
            "AND P.creationTime=(SELECT MAX(UP.creationTime) FROM UserProfile UP WHERE UP.user.id=:userId)")
    UserProfile findCurrentUserProfileByUserId(@Param("userId") String userId);

    @Query(value =
            "SELECT pr.user_id, pr.first_name, pr.last_name, pr.avatar FROM user_profile pr " +
                    "JOIN (SELECT p.user_id AS id, MAX(p.creation_time) AS time FROM user_profile p GROUP BY p.user_id) sub " +
                    "ON pr.user_id=sub.id AND pr.creation_time=sub.time " +
                    "WHERE pr.user_id IN (:ids) ORDER BY pr.first_name ASC, pr.last_name ASC",
            nativeQuery = true)
    List<Object[]> searchUsersByIds(@Param("ids") List<String> ids);


}
