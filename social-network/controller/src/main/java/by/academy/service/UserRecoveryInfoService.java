package by.academy.service;

public interface UserRecoveryInfoService {
    String findSecretQuestionByUserId(String userId);

    String findAnswerByUserId(String userId);
}
