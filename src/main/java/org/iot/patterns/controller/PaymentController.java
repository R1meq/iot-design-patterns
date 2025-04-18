package org.iot.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.Payment;
import org.iot.patterns.entity.enums.PaymentMethod;
import org.iot.patterns.entity.enums.PaymentStatus;
import org.iot.patterns.service.OrderService;
import org.iot.patterns.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;

    @GetMapping
    public String getAllPayments(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        return "payments/list";
    }

    @GetMapping("/{id}")
    public String getPaymentById(@PathVariable Long id, Model model) {
        model.addAttribute("payment", paymentService.findById(id));
        return "payments/view";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("paymentStatuses", PaymentStatus.values());
        return "payments/add";
    }

    @PostMapping("/add")
    public String createPayment(@ModelAttribute Payment payment) {
        paymentService.save(payment);
        return "redirect:/payments";
    }

    @GetMapping("/{id}/delete")
    public String deletePayment(@PathVariable Long id) {
        paymentService.delete(id);
        return "redirect:/payments";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("payment", paymentService.findById(id));
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("paymentStatuses", PaymentStatus.values());
        return "payments/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePayment(@PathVariable Long id, @ModelAttribute Payment payment) {
        paymentService.update(id, payment);
        return "redirect:/payments";
    }
}
