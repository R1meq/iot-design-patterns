package org.iot.patterns.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.MobilePlan;
import org.iot.patterns.entity.Order;
import org.iot.patterns.entity.User;
import org.iot.patterns.entity.enums.Type;
import org.iot.patterns.mapper.OrderMapper;
import org.iot.patterns.repository.MobilePlanRepository;
import org.iot.patterns.repository.OrderRepository;
import org.iot.patterns.repository.UserRepository;
import org.iot.patterns.service.OrderService;
import org.iot.patterns.utils.CsvDataUtils;
import org.iot.patterns.utils.ServiceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MobilePlanRepository mobilePlanRepository;
    private final ServiceUtils serviceUtils;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public void writeToDatabase() {
        if (orderRepository.count() != 0) {
            return;
        }
        List<List<String>> data = CsvDataUtils.readRowDataByType(Type.ORDER);
        Map<Long, User> userMap = serviceUtils.createEntityMap(userRepository, User::getId);
        Map<Long, MobilePlan> mobilePlanMap = serviceUtils.createEntityMap(mobilePlanRepository, MobilePlan::getId);

        List<Order> orders = data.stream()
                .map(e -> {
                    Order order = orderMapper.fromCsvRecord(e);
                    order.setUser(userMap.get(Long.parseLong(e.get(1))));
                    order.setMobilePlan(mobilePlanMap.get(Long.parseLong(e.get(2))));
                    return order;
                })
                .toList();

        orderRepository.saveAllAndFlush(orders);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Order save(Order entity) {
        userRepository.findById(entity.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        mobilePlanRepository.findById(entity.getMobilePlan().getId()).orElseThrow(EntityNotFoundException::new);
        return orderRepository.save(entity);
    }

    @Override
    public void update(Long id, Order entity) {
        Order existingOrder = findById(id);
        userRepository.findById(entity.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        mobilePlanRepository.findById(entity.getMobilePlan().getId()).orElseThrow(EntityNotFoundException::new);
        existingOrder.setUser(entity.getUser());
        existingOrder.setMobilePlan(entity.getMobilePlan());
        orderRepository.save(existingOrder);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        orderRepository.deleteById(id);
    }
}
