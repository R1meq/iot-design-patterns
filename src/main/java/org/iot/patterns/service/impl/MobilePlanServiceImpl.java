package org.iot.patterns.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.MobilePlan;
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

    @Override
    public List<MobilePlan> findAll() {
        return mobilePlanRepository.findAll();
    }

    @Override
    public MobilePlan findById(Long id) {
        return mobilePlanRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public MobilePlan save(MobilePlan entity) {
        return mobilePlanRepository.save(entity);
    }

    @Override
    public void update(Long id, MobilePlan entity) {
        MobilePlan existingPlan = findById(id);
        existingPlan.setName(entity.getName());
        existingPlan.setDescription(entity.getDescription());
        existingPlan.setPrice(entity.getPrice());
        existingPlan.setDataLimit(entity.getDataLimit());
        existingPlan.setCallMinutes(entity.getCallMinutes());
        existingPlan.setSmsCount(entity.getSmsCount());
        mobilePlanRepository.save(existingPlan);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        mobilePlanRepository.deleteById(id);
    }
}
