package dev.agitrubard.couriertracking.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCourierLocationTrackingConfiguration {

    public static final String EXCHANGE_NAME = "courier-location-tracking.exchange";
    public static final String QUEUE_NAME = "courier-location-tracking.queue";
    public static final String ROUTING_KEY = "courier-location-tracking.key";

    @Bean
    TopicExchange courierLocationTrackingExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue courierLocationTrackingQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    Binding binding(Queue courierLocationTrackingQueue, TopicExchange courierLocationTrackingExchange) {
        return BindingBuilder
                .bind(courierLocationTrackingQueue)
                .to(courierLocationTrackingExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                  MessageConverter jsonMessageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                        MessageConverter jsonMessageConverter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter);
        return factory;
    }

}
