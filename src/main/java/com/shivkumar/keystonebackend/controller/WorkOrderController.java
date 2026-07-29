package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.WorkOrderRequest;
import com.shivkumar.keystonebackend.dto.WorkOrderResponse;
import com.shivkumar.keystonebackend.service.WorkOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-orders")
@RequiredArgsConstructor
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    // CREATE
    @PostMapping
    public WorkOrderResponse createWorkOrder(@RequestBody WorkOrderRequest request) {
        return workOrderService.createWorkOrder(request);
    }

    // GET ALL
    @GetMapping
    public List<WorkOrderResponse> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public WorkOrderResponse getWorkOrderById(@PathVariable Long id) {
        return workOrderService.getWorkOrderById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public WorkOrderResponse updateWorkOrder(
            @PathVariable Long id,
            @RequestBody WorkOrderRequest request) {

        return workOrderService.updateWorkOrder(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteWorkOrder(@PathVariable Long id) {

        workOrderService.deleteWorkOrder(id);

        return "Work Order deleted successfully";
    }
}