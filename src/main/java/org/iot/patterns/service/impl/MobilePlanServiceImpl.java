package org.iot.patterns.service.impl;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.enums.Type;
import org.iot.patterns.mapper.MobilePlanMapper;
import org.iot.patterns.repository.MobilePlanRepository;
import org.iot.patterns.service.MobilePlanService;
import org.iot.patterns.utils.CsvDataUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MobilePlanServiceImpl implements MobilePlanService {
    private final MobilePlanRepository mobilePlanRepository;
    private final MobilePlanMapper mobilePlanMapper;

    @Override
    @Transactional
    public void writeToDatabase() {
        if (mobilePlanRepository.count() != 0) {
            return;
        }
        List<List<String>> data = CsvDataUtils.readRowDataByType(Type.MOBILE_PLAN);
        mobilePlanRepository.saveAllAndFlush(mobilePlanMapper.fromCsvRecords(data));
    }
}
