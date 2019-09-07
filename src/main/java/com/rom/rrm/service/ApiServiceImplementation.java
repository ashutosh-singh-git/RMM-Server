package com.rom.rrm.service;

import com.rom.rrm.document.Company;
import com.rom.rrm.document.Manager;
import com.rom.rrm.document.Review;
import com.rom.rrm.dto.NewManager;
import com.rom.rrm.dto.PromotedReview;
import com.rom.rrm.dto.ReviewResult;
import com.rom.rrm.dto.SearchResult;
import com.rom.rrm.repository.CompanyRepository;
import com.rom.rrm.repository.ManagerRepository;
import com.rom.rrm.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class ApiServiceImplementation implements ApiService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Company> searchCompanyByLocation(String city) {
        return companyRepository.findByCity(city);
    }

    @Override
    public Company findCompanyById(String id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public List<Company> fetchAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.insert(company);
    }

    @Override
    public List<Manager> searchManagerByName(String managerName, String companyId) {
        return managerRepository.findByNameRegexAndCompanyId(".*" + managerName + ".*", companyId);
    }

    @Override
    public List<Manager> searchManagerByCompany(String companyId) {
        return managerRepository.findByCompanyId(companyId);
    }

    @Override
    public List<SearchResult> searchManager(String companyId, String managerName) {
        if (companyId != null && !companyId.isBlank()) {
            Optional<Company> company = companyRepository.findById(companyId);
            if (company.isPresent()) {
                List<Manager> managerList = managerRepository.findByNameRegexAndCompanyId(".*" + managerName + ".*", companyId);

                if (managerList.size() > 0) {
                    return managerList.stream()
                            .map(v -> createResult(v, company.get()))
                            .collect(Collectors.toList());
                }

                return managerRepository.findByCompanyId(companyId).stream()
                        .map(v -> createResult(v, company.get()))
                        .collect(Collectors.toList());
            }
        } else {
            List<Manager> managerList = managerRepository.findByNameRegex(".*" + managerName + ".*");
            if (managerList.size() > 0) {
                return managerList.stream()
                        .map(v -> {
                            Optional<Company> company = companyRepository.findById(v.getCompanyId());
                            return company.map(company1 -> createResult(v, company1)).orElse(null);
                        })
                        .collect(Collectors.toList());
            }
        }
        return null;
    }

    @Override
    public ReviewResult searchReview(String managerId) {
        if (managerId != null && !managerId.isBlank()) {
            List<Review> reviewList = reviewRepository.findByManagerId(managerId);
            Optional<Manager> manager = managerRepository.findById(managerId);
            if (manager.isPresent()) {
                return createReviewResult(manager.get(), reviewList);
            }
        }
        System.out.println("Manager Id is null");
        return null;
    }

    private ReviewResult createReviewResult(Manager mg, List<Review> reviewList) {

        Optional<Company> companyOptional = companyRepository.findById(mg.getCompanyId());
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            int averageRating = mg.getTotalReviews() > 0 ? Math.floorDiv(mg.getTotalRatings(), mg.getTotalReviews()) : mg.getTotalRatings();
            return ReviewResult
                    .builder()
                    .city(company.getCity())
                    .managerName(mg.getName())
                    .id(mg.getId())
                    .averageRating(averageRating)
                    .companyId(company.getId())
                    .companyName(company.getName())
                    .gender(mg.getGender())
                    .designation(mg.getDesignation())
                    .totalReviews(mg.getTotalReviews())
                    .reviews(reviewList)
                    .build();
        } else {
            System.out.println("Company not found!");
            return null;
        }
    }

    private SearchResult createResult(Manager manager, Company company) {

        int averageRating = manager.getTotalReviews() > 0 ? Math.floorDiv(manager.getTotalRatings(), manager.getTotalReviews()) : manager.getTotalRatings();
        SearchResult searchResult = SearchResult.builder()
                .companyId(company.getId())
                .city(company.getCity())
                .companyName(company.getName())
                .id(manager.getId())
                .designation(manager.getDesignation())
                .gender(manager.getGender())
                .managerName(manager.getName())
                .totalReviews(manager.getTotalReviews())
                .averageRating(averageRating)
                .promotedReview(null)
                .build();

        Review review = reviewRepository.findFirstByManagerId(searchResult.getId());
        if (review != null) {
            PromotedReview promotedReview = PromotedReview.builder()
                    .rating(review.getOverallRating())
                    .reviewer(review.getName())
                    .comments(review.getComments())
                    .build();
            searchResult.setPromotedReview(promotedReview);
        }

        return searchResult;
    }

    @Override
    public Manager addManagerOfCompany(NewManager newManager) {
        String companyId = newManager.getCompanyId();
        Manager manager = new Manager();
        manager.setGender(newManager.getGender());
        manager.setDesignation(newManager.getDesignation());
        manager.setName(newManager.getManagerName());
        if (companyId != null && !companyId.isBlank()) {
            manager.setCompanyId(companyId);
            return managerRepository.save(manager);
        } else {
            Company company = new Company();
            company.setCity(newManager.getCity());
            company.setName(newManager.getCompanyName());
            Company newCompany = companyRepository.save(company);
            manager.setCompanyId(newCompany.getId());
            return managerRepository.save(manager);
        }
    }

    @Override
    public List<Review> searchReviewForManager(String managerId) {
        return reviewRepository.findByManagerId(managerId);
    }

    @Override
    public Review addReviewForManager(Review review) {
        Review reviewMono = reviewRepository.save(review);
        Optional<Manager> optionalManager = managerRepository.findById(review.getManagerId());
        if (optionalManager.isPresent()) {
            Manager manager = optionalManager.get();
            manager.setTotalReviews(manager.getTotalReviews() + 1);
            manager.setTotalRatings(manager.getTotalRatings() + review.getOverallRating());
            managerRepository.save(manager);
        }
        return reviewMono;
    }
}
