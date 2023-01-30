package com.niit.Notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageConfiguration {
    private String exchangeName = "food-exchange";
    private String registerQueue = "food-queue";
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue registerQueue(){
        return new Queue(registerQueue);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackSon2MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
//    @Bean
//    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(producerJackSon2MessageConverter());
//        return rabbitTemplate;
//    }
    @Bean
    Binding bindingUser(DirectExchange exchange, Queue registerQueue){
        return BindingBuilder.bind(registerQueue()).to(directExchange()).with("food-routing");
    }
}
