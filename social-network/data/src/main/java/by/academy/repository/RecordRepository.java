package by.academy.repository;

import by.academy.pojo.record.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {
    @Query("SELECT R FROM Record R WHERE R.user.id=:userId ORDER BY R.creationTime")
    List<Record> findAllRecordsByUserId(@Param("userId") String userId);

    @Query("SELECT R FROM Record R WHERE R.user.id=:userId ORDER BY R.creationTime DESC")
    Page<Record> findAllRecordsByUserId(@Param("userId") String userId, Pageable pageable);
}
