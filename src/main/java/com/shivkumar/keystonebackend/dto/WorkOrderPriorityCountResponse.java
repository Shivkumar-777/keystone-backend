package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.WorkOrderPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderPriorityCountResponse {

    private WorkOrderPriority priority;

    private long count;

}