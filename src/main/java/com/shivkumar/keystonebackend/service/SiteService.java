package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.SiteRequest;
import com.shivkumar.keystonebackend.dto.SiteResponse;
import com.shivkumar.keystonebackend.entity.Customer;
import com.shivkumar.keystonebackend.entity.Site;
import com.shivkumar.keystonebackend.repository.CustomerRepository;
import com.shivkumar.keystonebackend.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;
    private final CustomerRepository customerRepository;

    // Create Site
    public SiteResponse createSite(SiteRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Site site = Site.builder()
                .siteName(request.getSiteName())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .postalCode(request.getPostalCode())
                .customer(customer)
                .build();

        Site savedSite = siteRepository.save(site);

        return mapToResponse(savedSite);
    }

    // Get All Sites
    public List<SiteResponse> getAllSites() {

        return siteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Site By ID
    public SiteResponse getSiteById(Long id) {

        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));

        return mapToResponse(site);
    }

    // Update Site
    public SiteResponse updateSite(Long id, SiteRequest request) {

        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        site.setSiteName(request.getSiteName());
        site.setAddress(request.getAddress());
        site.setCity(request.getCity());
        site.setState(request.getState());
        site.setPostalCode(request.getPostalCode());
        site.setCustomer(customer);

        Site updatedSite = siteRepository.save(site);

        return mapToResponse(updatedSite);
    }

    // Delete Site
    public void deleteSite(Long id) {

        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));

        siteRepository.delete(site);
    }

    // Entity -> DTO Mapper
    private SiteResponse mapToResponse(Site site) {

        return SiteResponse.builder()
                .id(site.getId())
                .siteName(site.getSiteName())
                .address(site.getAddress())
                .city(site.getCity())
                .state(site.getState())
                .postalCode(site.getPostalCode())
                .customerId(site.getCustomer().getId())
                .customerName(site.getCustomer().getCustomerName())
                .build();
    }
}