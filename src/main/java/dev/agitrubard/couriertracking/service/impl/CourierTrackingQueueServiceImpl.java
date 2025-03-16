package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.configuration.RabbitMQCourierLocationTrackingConfiguration;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.service.CourierTrackingQueueService;
import dev.agitrubard.couriertracking.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class CourierTrackingQueueServiceImpl implements CourierTrackingQueueService {

    private final CourierTrackingService courierTrackingService;
    private final AmqpTemplate amqpTemplate;

    @Override
    public void saveLocation(final CourierLocationSaveRequest saveRequest) {

        log.debug("Courier location save request received with courierId:{} and time:{}",
                saveRequest.getCourierId(), saveRequest.getTime()
        );

        try {

            amqpTemplate.convertAndSend(
                    RabbitMQCourierLocationTrackingConfiguration.EXCHANGE_NAME,
                    RabbitMQCourierLocationTrackingConfiguration.ROUTING_KEY,
                    saveRequest
            );

            log.debug("Courier location save request added to queue with courierId:{} and time:{}",
                    saveRequest.getCourierId(), saveRequest.getTime()
            );

        } catch (Exception exception) {
            log.warn("Error occurred while adding courier location save request to queue with courierId:{} and time:{}",
                    saveRequest.getCourierId(), saveRequest.getTime()
            );
            this.save(saveRequest);
            log.warn("Courier location save request forwarded to service with courierId:{} and time:{}",
                    saveRequest.getCourierId(), saveRequest.getTime()
            );
        }

    }

    @RabbitListener(queues = RabbitMQCourierLocationTrackingConfiguration.QUEUE_NAME)
    private void save(final CourierLocationSaveRequest saveRequest) {
        courierTrackingService.saveLocation(saveRequest);
    }

}
