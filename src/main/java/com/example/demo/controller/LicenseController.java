package com.example.demo.controller;

import com.example.demo.model.License;
import com.example.demo.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LicenseController {

    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/api/licenses")
    @ResponseBody
    public List<License> getAllLicenses() {
        return licenseService.getAllLicenses();
    }

    @GetMapping("/viewLicenses")
    public String viewLicenses(Model model) {
        List<License> licenses = licenseService.getAllLicenses();
        model.addAttribute("licenses", licenses);
        return "viewLicenses";
    }
    @GetMapping("/editLicense/{id}")
    public String showEditLicenseForm(@PathVariable Long id, Model model) {
        License license = licenseService.getLicenseById(id);
        if (license == null) {
            return "redirect:/viewLicenses";
        }
        model.addAttribute("editedLicense", license);
        return "editLicense";
    }
    @GetMapping("/deleteLicense/{id}")
    public String deleteLicense(@PathVariable Long id) {
        licenseService.deleteLicense(id);

        // Redirect to the view licenses page
        return "redirect:/viewLicenses";
    }

    @PostMapping("/deleteLicense")
    public String deleteLicense(@ModelAttribute("licenseToDelete") License licenseToDelete) {
        // Perform the delete logic in your service
        licenseService.deleteLicense(licenseToDelete.getId());

        // Redirect to the view licenses page
        return "redirect:/viewLicenses";
    }
    @PostMapping("/saveEditedLicense")
    public String saveEditedLicense(@ModelAttribute("editedLicense") License editedLicense) {
        licenseService.updateLicense(editedLicense);

        return "redirect:/viewLicenses";
    }
        @GetMapping("/addLicense")
    public String showAddLicenseForm(Model model) {
        model.addAttribute("license", new License());
        return "addLicenseForm";
    }

    @PostMapping("/saveLicense")
    public String saveLicense(License license) {
        licenseService.saveLicense(license);
        return "redirect:/viewLicenses";
    }
}
