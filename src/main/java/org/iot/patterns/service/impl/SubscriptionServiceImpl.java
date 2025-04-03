package org.iot.patterns.service.impl;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.MobilePlan;
import org.iot.patterns.entity.Subscription;
import org.iot.patterns.entity.User;
import org.iot.patterns.entity.enums.Type;
import org.iot.patterns.mapper.SubscriptionMapper;
import org.iot.patterns.repository.MobilePlanRepository;
import org.iot.patterns.repository.SubscriptionRepository;
import org.iot.patterns.repository.UserRepository;
import org.iot.patterns.service.SubscriptionService;
import org.iot.patterns.utils.CsvDataUtils;
import org.iot.patterns.utils.ServiceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final MobilePlanRepository mobilePlanRepository;
    private final ServiceUtils serviceUtils;

    @Override
    @Transactional
    public void writeToDatabase() {
        if (subscriptionRepository.count() != 0) {
            return;
        }
        List<List<String>> data = CsvDataUtils.readRowDataByType(Type.SUBSCRIPTION);
        Map<Long, MobilePlan> mobilePlanMap = serviceUtils.createEntityMap(mobilePlanRepository, MobilePlan::getId);
        Map<Long, User> userMap = serviceUtils.createEntityMap(userRepository, User::getId);

        List<Subscription> subscriptions = data.stream()
                .map(e -> {
                    Subscription subscription = subscriptionMapper.fromCsvRecord(e);
                    subscription.setUser(userMap.get(Long.parseLong(e.get(1))));
                    subscription.setMobilePlan(mobilePlanMap.get(Long.parseLong(e.get(2))));
                    return subscription;
                })
                .toList();

        subscriptionRepository.saveAllAndFlush(subscriptions);
    }
}
