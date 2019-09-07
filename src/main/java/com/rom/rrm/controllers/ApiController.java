package com.rom.rrm.controllers;

import com.rom.rrm.annotations.ReCaptchaValidation;
import com.rom.rrm.document.Company;
import com.rom.rrm.document.Manager;
import com.rom.rrm.document.Review;
import com.rom.rrm.dto.NewManager;
import com.rom.rrm.dto.ReviewResult;
import com.rom.rrm.dto.SearchResult;
import com.rom.rrm.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/company")
    public List<Company> companySearch(@RequestParam String city) {
        return apiService.searchCompanyByLocation(city);
    }

    @GetMapping("/company/id")
    public Company companySearchById(@RequestParam String id) {
        return apiService.findCompanyById(id);
    }

    @GetMapping("/company/all")
    public List<Company> fetchAllCompanies() {
        return apiService.fetchAllCompanies();
    }

    @GetMapping("/search")
    public List<SearchResult> managerSearch(@RequestParam(required = false) String companyId, @RequestParam(required = false) String managerName) {
        return apiService.searchManager(companyId, managerName);
    }

    @GetMapping("/search/review")
    public ReviewResult searchReview(@RequestParam(required = false) String managerId) {
        return apiService.searchReview(managerId);
    }

    @PostMapping("/manager")
    @ReCaptchaValidation
    public Manager addManager(@RequestBody NewManager managerMono) {
        return apiService.addManagerOfCompany(managerMono);
    }

    @GetMapping("/manager")
    public List<Manager> fetchManager(@RequestParam String managerName, @RequestParam(required = false) String companyId) {
        return apiService.searchManagerByName(managerName, companyId);
    }

    @GetMapping("/review")
    public List<Review> reviewSearch(@RequestParam String managerId) {
        return apiService.searchReviewForManager(managerId);
    }

    @PostMapping("/review")
    @ReCaptchaValidation
    public Review addReview(@RequestBody @Valid Review review) {
        return apiService.addReviewForManager(review);
    }

}
