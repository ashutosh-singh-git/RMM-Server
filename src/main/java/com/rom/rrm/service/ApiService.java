package com.rom.rrm.service;


import com.rom.rrm.document.Company;
import com.rom.rrm.document.Manager;
import com.rom.rrm.document.Review;
import com.rom.rrm.dto.NewManager;
import com.rom.rrm.dto.ReviewResult;
import com.rom.rrm.dto.SearchResult;

import java.util.List;

public interface ApiService {

    List<Company> searchCompanyByLocation(String locationId);
    Company findCompanyById(String id);
    List<Company> fetchAllCompanies();
    List<SearchResult> searchManager(String companyId, String managerName);
    ReviewResult searchReview(String managerId);
    Manager addManagerOfCompany(NewManager manager);
    Review addReviewForManager(Review review);
}
