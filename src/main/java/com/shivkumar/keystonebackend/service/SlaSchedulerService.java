package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.entity.WorkOrder;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import com.shivkumar.keystonebackend.enums.NotificationType;
import com.shivkumar.keystonebackend.enums.SLAStatus;
import com.shivkumar.keystonebackend.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlaSchedulerService {

    private final WorkOrderRepository workOrderRepository;
    private final NotificationService notificationService;

    /**
     * Runs every hour.
     */
    @Scheduled(cron = "0 0 * * * *")
    public void checkSlaBreaches() {

        log.info("Checking SLA status...");

        List<WorkOrder> workOrders =
                workOrderRepository.findByStatusNot(WorkOrderStatus.COMPLETED);

        LocalDateTime now = LocalDateTime.now();

        for (WorkOrder workOrder : workOrders) {

            if (workOrder.getSlaDueDate() == null) {
                continue;
            }

            // -------------------------
            // SLA BREACHED
            // -------------------------
            if (now.isAfter(workOrder.getSlaDueDate())) {

                if (workOrder.getSlaStatus() != SLAStatus.BREACHED) {

                    workOrder.setSlaStatus(SLAStatus.BREACHED);

                    notificationService.createNotification(
                            workOrder.getTechnician(),
                            NotificationType.SLA_BREACHED,
                            "SLA Breached",
                            "Work Order #" + workOrder.getId()
                                    + " has exceeded its SLA."
                    );

                    workOrderRepository.save(workOrder);

                    log.info("SLA breached for WorkOrder {}", workOrder.getId());
                }

                continue;
            }

            // -------------------------
            // SLA WARNING (2 hours left)
            // -------------------------
            if (workOrder.getSlaDueDate().minusHours(2).isBefore(now)
                    && workOrder.getSlaStatus() == SLAStatus.ON_TIME) {

                workOrder.setSlaStatus(SLAStatus.AT_RISK);

                notificationService.createNotification(
                        workOrder.getTechnician(),
                        NotificationType.SLA_WARNING,
                        "SLA Warning",
                        "Work Order #" + workOrder.getId()
                                + " will breach its SLA in less than 2 hours."
                );

                workOrderRepository.save(workOrder);

                log.info("SLA warning for WorkOrder {}", workOrder.getId());
            }
        }
    }
}