package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.SiteRequest;
import com.shivkumar.keystonebackend.dto.SiteResponse;
import com.shivkumar.keystonebackend.entity.Customer;
import com.shivkumar.keystonebackend.entity.Site;
import com.shivkumar.keystonebackend.repository.CustomerRepository;
import com.shivkumar.keystonebackend.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteService {

    private final SiteRepository siteRepository;
    private final CustomerRepository customerRepository;

    // ==========================
    // CREATE
    // ==========================

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

        return mapToResponse(siteRepository.save(site));
    }

    // ==========================
    // GET ALL
    // ==========================

    @Transactional(readOnly = true)
    public List<SiteResponse> getAllSites() {

        return siteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // SEARCH BY SITE NAME
    // ==========================

    @Transactional(readOnly = true)
    public Page<SiteResponse> searchSites(
            String siteName,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return siteRepository
                .findBySiteNameContainingIgnoreCase(siteName, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY CITY
    // ==========================

    @Transactional(readOnly = true)
    public Page<SiteResponse> searchByCity(
            String city,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return siteRepository
                .findByCityContainingIgnoreCase(city, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY STATE
    // ==========================

    @Transactional(readOnly = true)
    public Page<SiteResponse> searchByState(
            String state,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return siteRepository
                .findByStateContainingIgnoreCase(state, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY POSTAL CODE
    // ==========================

    @Transactional(readOnly = true)
    public Page<SiteResponse> searchByPostalCode(
            String postalCode,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return siteRepository
                .findByPostalCodeContaining(postalCode, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // FILTER BY CUSTOMER
    // ==========================

    @Transactional(readOnly = true)
    public Page<SiteResponse> getSitesByCustomer(
            Long customerId,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return siteRepository
                .findByCustomerId(customerId, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // GET BY ID
    // ==========================

    @Transactional(readOnly = true)
    public SiteResponse getSiteById(Long id) {

        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));

        return mapToResponse(site);
    }

    // ==========================
    // UPDATE
    // ==========================

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

        return mapToResponse(siteRepository.save(site));
    }

    // ==========================
    // DELETE
    // ==========================

    public void deleteSite(Long id) {

        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));

        siteRepository.delete(site);
    }

    // ==========================
    // ENTITY → RESPONSE DTO
    // ==========================

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