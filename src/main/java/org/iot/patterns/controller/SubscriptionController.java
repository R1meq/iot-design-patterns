package org.iot.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.MobilePlan;
import org.iot.patterns.entity.Subscription;
import org.iot.patterns.entity.enums.SubscriptionStatus;
import org.iot.patterns.service.MobilePlanService;
import org.iot.patterns.service.SubscriptionService;
import org.iot.patterns.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final MobilePlanService mobilePlanService;

    @GetMapping
    public String getAllSubscriptions(Model model) {
        model.addAttribute("subscriptions", subscriptionService.findAll());
        return "subscriptions/list";
    }

    @GetMapping("/{id}")
    public String getSubscriptionById(@PathVariable Long id, Model model) {
        model.addAttribute("subscription", subscriptionService.findById(id));
        return "subscriptions/view";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("mobilePlans", mobilePlanService.findAll());
        model.addAttribute("statuses", SubscriptionStatus.values());
        return "subscriptions/add";
    }

    @PostMapping("/add")
    public String createSubscription(@ModelAttribute Subscription subscription) {
        subscriptionService.save(subscription);
        return "redirect:/subscriptions";
    }

    @GetMapping("/{id}/delete")
    public String deleteSubscription(@PathVariable Long id) {
        subscriptionService.delete(id);
        return "redirect:/subscriptions";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("subscription", subscriptionService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("mobilePlans", mobilePlanService.findAll());
        model.addAttribute("statuses", SubscriptionStatus.values());
        return "subscriptions/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateSubscription(@PathVariable Long id, @ModelAttribute Subscription subscription) {
        subscriptionService.update(id, subscription);
        return "redirect:/subscriptions";
    }
}