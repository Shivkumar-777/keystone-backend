package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.CustomerRequest;
import com.shivkumar.keystonebackend.dto.CustomerResponse;
import com.shivkumar.keystonebackend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // ==========================
    // CREATE CUSTOMER
    // ==========================

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request) {
        return customerService.createCustomer(request);
    }

    // ==========================
    // GET ALL CUSTOMERS
    // ==========================

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // ==========================
    // SEARCH BY CUSTOMER NAME
    // Example:
    // /api/customers/search?customerName=john&page=0&size=10
    // ==========================

    @GetMapping("/search")
    public Page<CustomerResponse> searchCustomers(
            @RequestParam String customerName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return customerService.searchCustomers(customerName, page, size);
    }

    // ==========================
    // SEARCH BY EMAIL
    // Example:
    // /api/customers/search/email?email=test@gmail.com&page=0&size=10
    // ==========================

    @GetMapping("/search/email")
    public Page<CustomerResponse> searchByEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return customerService.searchByEmail(email, page, size);
    }

    // ==========================
    // SEARCH BY PHONE
    // Example:
    // /api/customers/search/phone?phone=9876543210&page=0&size=10
    // ==========================

    @GetMapping("/search/phone")
    public Page<CustomerResponse> searchByPhone(
            @RequestParam String phone,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return customerService.searchByPhone(phone, page, size);
    }

    // ==========================
    // GET CUSTOMER BY ID
    // ==========================

    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // ==========================
    // UPDATE CUSTOMER
    // ==========================

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request
    ) {

        return customerService.updateCustomer(id, request);
    }

    // ==========================
    // DELETE CUSTOMER
    // ==========================

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}