package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderStatusCountResponse {

    private WorkOrderStatus status;

    private long count;

}