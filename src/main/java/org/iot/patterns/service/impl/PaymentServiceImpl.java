package org.iot.patterns.service.impl;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.Order;
import org.iot.patterns.entity.Payment;
import org.iot.patterns.entity.enums.Type;
import org.iot.patterns.mapper.PaymentMapper;
import org.iot.patterns.repository.OrderRepository;
import org.iot.patterns.repository.PaymentRepository;
import org.iot.patterns.service.PaymentService;
import org.iot.patterns.utils.CsvDataUtils;
import org.iot.patterns.utils.ServiceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;
    private final ServiceUtils serviceUtils;

    @Override
    @Transactional
    public void writeToDatabase() {
        if (paymentRepository.count() != 0) {
            return;
        }
        List<List<String>> data = CsvDataUtils.readRowDataByType(Type.PAYMENT);
        Map<Long, Order> orderMap = serviceUtils.createEntityMap(orderRepository, Order::getId);

        List<Payment> payments = data.stream()
                .map(e -> {
                    Payment payment = paymentMapper.fromCsvRecord(e);
                    payment.setOrder(orderMap.get(Long.parseLong(e.get(1))));
                    return payment;
                })
                .toList();

        paymentRepository.saveAllAndFlush(payments);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
