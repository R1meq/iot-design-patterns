package org.iot.patterns.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.iot.patterns.entity.enums.Type;
import org.iot.patterns.mapper.UserMapper;
import org.iot.patterns.repository.UserRepository;
import org.iot.patterns.service.UserService;
import org.iot.patterns.utils.CsvDataUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void writeToDatabase() {
        if (userRepository.count() != 0) {
            return;
        }
        List<List<String>> data = CsvDataUtils.readRowDataByType(Type.USER);
        userRepository.saveAllAndFlush(userMapper.fromCsvRecords(data));
    }

}
