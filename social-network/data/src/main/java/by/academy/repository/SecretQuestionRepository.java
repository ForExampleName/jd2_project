package by.academy.repository;

import by.academy.pojo.account.SecretQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretQuestionRepository extends JpaRepository<SecretQuestion, Long> {
}
