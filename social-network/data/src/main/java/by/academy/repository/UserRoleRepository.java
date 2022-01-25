package by.academy.repository;

import by.academy.pojo.account.UserRole;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    @Query("SELECT R From UserRole R WHERE R.user.id=:userId ORDER BY R.creationTime DESC")
    List<UserRole> findUserRolesByUserId(@Param("userId") String userId, Pageable pageable);

    default UserRole findCurrentUserRoleByUserId(String userId) {
        return findUserRolesByUserId(userId, PageRequest.of(0, 1)).get(0);
    }
}
