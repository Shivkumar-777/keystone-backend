package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.CustomerRequest;
import com.shivkumar.keystonebackend.dto.CustomerResponse;
import com.shivkumar.keystonebackend.entity.Company;
import com.shivkumar.keystonebackend.entity.Customer;
import com.shivkumar.keystonebackend.repository.CompanyRepository;
import com.shivkumar.keystonebackend.repository.CustomerRepository;
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
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;

    // ==========================
    // CREATE CUSTOMER
    // ==========================

    public CustomerResponse createCustomer(CustomerRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Customer customer = Customer.builder()
                .customerName(request.getCustomerName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .company(company)
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }

    // ==========================
    // GET ALL CUSTOMERS
    // ==========================

    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // SEARCH BY CUSTOMER NAME
    // ==========================

    @Transactional(readOnly = true)
    public Page<CustomerResponse> searchCustomers(
            String customerName,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return customerRepository
                .findByCustomerNameContainingIgnoreCase(customerName, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY EMAIL
    // ==========================

    @Transactional(readOnly = true)
    public Page<CustomerResponse> searchByEmail(
            String email,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return customerRepository
                .findByEmailContainingIgnoreCase(email, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY PHONE
    // ==========================

    @Transactional(readOnly = true)
    public Page<CustomerResponse> searchByPhone(
            String phone,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return customerRepository
                .findByPhoneContaining(phone, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // GET CUSTOMER BY ID
    // ==========================

    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return mapToResponse(customer);
    }

    // ==========================
    // UPDATE CUSTOMER
    // ==========================

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        customer.setCustomerName(request.getCustomerName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setCompany(company);

        Customer updatedCustomer = customerRepository.save(customer);

        return mapToResponse(updatedCustomer);
    }

    // ==========================
    // DELETE CUSTOMER
    // ==========================

    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }

    // ==========================
    // ENTITY → RESPONSE DTO
    // ==========================

    private CustomerResponse mapToResponse(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .companyId(customer.getCompany().getId())
                .companyName(customer.getCompany().getCompanyName())
                .build();
    }
}