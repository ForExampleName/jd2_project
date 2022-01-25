package by.academy.service.impl;

import by.academy.pojo.account.SecretQuestion;
import by.academy.repository.SecretQuestionRepository;
import by.academy.service.SecretQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SecretQuestionServiceImpl implements SecretQuestionService {
    @Autowired
    private SecretQuestionRepository secretQuestionDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<SecretQuestion> findAll() {
        return secretQuestionDao.findAll();
    }
}
