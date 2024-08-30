package com.vian.leave.service.infrastructure.rabbitmq.direct;

import com.vian.leave.service.infrastructure.props.RabbitMQProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 路由模式
 * @ClassName: DirectConfig
 * @Author: lin long fa
 * @Date: 2024-08-16 18:13:14
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Configuration
@Slf4j
public class DirectConfig {

    @Resource
    private RabbitMQProperties rabbitMQProperties;

    // 死信交换机
    public static final String DEAD_LETTER_EXCHANGE = "deadLetterExchange";

    // 死信队列
    public static final String DEAD_LETTER_QUEUE = "deadLetterQueue";

    // 死信队列
    public static final String DEAD_LETTER_ROUTING = "deadLetterRouting";

    @Bean
    public DirectExchange routingExchange() {
        return new DirectExchange("routingExchange", true, false);
    }

    @Bean
    public CustomExchange routingDelayedExchange() {
        HashMap<String, Object> args = new HashMap<>();
        //自定义交换机的类型
        args.put("x-delayed-type", "direct");
        return new CustomExchange("routingDelayedExchange", "x-delayed-message", true, false, args);
//        return new DirectExchange("routingExchange", true, false);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    @Bean
    public Queue routingFirstQueue() {
        return new Queue("routingFirstQueue", true);
    }

    @Bean
    public Queue routingSecondQueue() {
        return new Queue("routingSecondQueue", true);
    }

    @Bean
    public Queue routingThirdQueue() {
        Map<String, Object> args = new HashMap<>(2);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING);
        //声明队列的 TTL
//        args.put("x-message-ttl", 10000);
        //队列绑定交换机
        return QueueBuilder.durable("routingThirdQueue").withArguments(args).build();
//        return new Queue("routingThirdQueue", true);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE, true);
    }

    @Bean
    public Binding routingFirstBind() {
        return BindingBuilder.bind(routingFirstQueue()).to(routingExchange()).with("firstRouting");
    }

    @Bean
    public Binding routingSecondBind() {
        return BindingBuilder.bind(routingSecondQueue()).to(routingExchange()).with("secondRouting");
    }

    @Bean
    public Binding routingThirdBind() {
        return BindingBuilder.bind(routingThirdQueue()).to(routingDelayedExchange()).with("thirdRouting").noargs();
    }

    @Bean
    public Binding deadLetterBind() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING);
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMQProperties.getHost());
        connectionFactory.setUsername(rabbitMQProperties.getUsername());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);  // 启用生产者确认
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("Message successfully delivered to the exchange");
            } else {
                System.err.println("Failed to deliver message to the exchange: " + cause);
            }
        });
        /**
         * true：交换机无法将消息进行路由时，会将该消息返回给生产者
         * false：如果发现消息无法进行路由，则直接丢弃
         */
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returned -> {
            System.err.println("Message returned: " + new String(returned.getMessage().getBody()));
            System.err.println("Returned reply code: " + returned.getReplyCode());
            System.err.println("Returned reply text: " + returned.getReplyText());
            System.err.println("Exchange: " + returned.getExchange());
            System.err.println("Routing key: " + returned.getRoutingKey());
        });
        return rabbitTemplate;
    }
}
