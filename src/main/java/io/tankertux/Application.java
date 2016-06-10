package io.tankertux;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRabbit
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(messageConverter());
        template.setExchange(exchange().getName());
        return template;
    }

    @Bean
    public MessageConverter messageConverter(){
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper());
        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory("grantserver", 5672);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(){
        return new RabbitAdmin(connectionFactory());
    }

    @Bean(name = "event.queue")
    public Queue queue(){
        return new Queue("event.queue");
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange("event.topic.exchange");
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with("*.tankertux").noargs();
    }

    @Bean
    public ClassMapper classMapper(){
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setDefaultType(TankertuxEvent.class);
        return classMapper;
    }
}
