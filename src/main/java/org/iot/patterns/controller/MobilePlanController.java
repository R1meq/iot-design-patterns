package org.iot.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.MobilePlan;
import org.iot.patterns.service.MobilePlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mobile-plans")
@RequiredArgsConstructor
public class MobilePlanController {
    private final MobilePlanService mobilePlanService;

    @GetMapping
    public String getAllMobilePlans(Model model) {
        model.addAttribute("mobilePlans", mobilePlanService.findAll());
        return "mobilePlans/list";
    }

    @GetMapping("/{id}")
    public String getMobilePlanById(@PathVariable Long id, Model model) {
        model.addAttribute("mobilePlan", mobilePlanService.findById(id));
        return "mobilePlans/view";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("mobilePlan", new MobilePlan());
        return "mobilePlans/add";
    }

    @PostMapping("/add")
    public String createMobilePlan(@ModelAttribute MobilePlan mobilePlan) {
        mobilePlanService.save(mobilePlan);
        return "redirect:/mobile-plans";
    }

    @GetMapping("/{id}/delete")
    public String deleteMobilePlan(@PathVariable Long id) {
        mobilePlanService.delete(id);
        return "redirect:/mobile-plans";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("mobilePlan", mobilePlanService.findById(id));
        return "mobilePlans/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateMobilePlan(@PathVariable Long id, @ModelAttribute MobilePlan mobilePlan) {
        mobilePlanService.update(id, mobilePlan);
        return "redirect:/mobile-plans";
    }
}
