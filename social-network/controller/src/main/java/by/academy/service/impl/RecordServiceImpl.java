package by.academy.service.impl;

import by.academy.dto.RecordCommand;
import by.academy.dto.RecordDto;
import by.academy.dto.UserPageRecordDto;
import by.academy.pojo.account.User;
import by.academy.pojo.record.Record;
import by.academy.repository.RecordRepository;
import by.academy.repository.UserRepository;
import by.academy.service.RecordService;
import by.academy.session.UserDetails;
import by.academy.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:pagination.properties")
public class RecordServiceImpl implements RecordService {

    @Value("${records.per.page}")
    private Integer recordsPerPage;

    @Autowired
    private UserDetails userDetails;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = IOException.class)
    public void saveNewRecord(RecordCommand recordCommand) throws IOException {
        LocalDateTime creationTime = LocalDateTime.now();
        User user = userRepository.findUserById(userDetails.getUserId());

        Record record = new Record();
        record.setCreationTime(creationTime);
        record.setUser(user);
        record.setHeader(recordCommand.getHeader());
        record.setDescription(recordCommand.getDescription());

        byte[] picture = recordCommand.getPicture().getBytes();
        record.setPicture(picture.length == 0 ? null : picture);

        recordRepository.save(record);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteRecord(String recordId) {
        Record record = recordRepository.findById(recordId).get();
        recordRepository.delete(record);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public UserPageRecordDto getUserPageRecordDto(String userId, Integer page) {
        Page<Record> records = recordRepository.findAllRecordsByUserId(userId, PageRequest.of(page, recordsPerPage));
        UserPageRecordDto dto = new UserPageRecordDto();
        dto.setShowControls(userId.equals(userDetails.getUserId()));
        dto.setRecords(records.stream().map(RecordDto::new).collect(Collectors.toList()));
        dto.setPagination(PaginationUtil.getRecordPaginationDto(page, records.getTotalPages()));
        return dto;
    }
}
