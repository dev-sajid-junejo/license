package com.example.demo.service;

import com.example.demo.model.License;
import com.example.demo.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }
    public License getLicenseById(Long id) {
        Optional<License> optionalLicense = licenseRepository.findById(id);
        return optionalLicense.orElse(null);
    }
    public License saveLicense(License license) {
        return licenseRepository.save(license);
    }
    public void deleteLicense(Long id) {
        licenseRepository.deleteById(id);
    }
    public void updateLicense(License editedLicense) {
        License existingLicense = licenseRepository.findById(editedLicense.getId()).orElse(null);

        if (existingLicense != null) {
            existingLicense.setSoftwareName(editedLicense.getSoftwareName());
            existingLicense.setExpirationDate(editedLicense.getExpirationDate());

            licenseRepository.save(existingLicense);
        }
    }

}
