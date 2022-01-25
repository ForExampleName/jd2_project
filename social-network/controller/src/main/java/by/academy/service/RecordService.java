package by.academy.service;

import by.academy.dto.RecordCommand;
import by.academy.dto.UserPageRecordDto;

import java.io.IOException;

public interface RecordService {
    void saveNewRecord(RecordCommand recordCommand) throws IOException;

    void deleteRecord(String recordId);

    UserPageRecordDto getUserPageRecordDto(String userId, Integer page);
}
