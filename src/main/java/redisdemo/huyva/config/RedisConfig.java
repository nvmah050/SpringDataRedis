package redisdemo.huyva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redisdemo.huyva.queue.MessagePublisher;
import redisdemo.huyva.queue.MessagePublisherImpl;
import redisdemo.huyva.queue.RedisMessageSubscriberImpl;

@Configuration
@ComponentScan("redisdemo.huyva")
@EnableRedisRepositories(basePackages = "redisdemo.huyva.repository")
@PropertySource("classpath:application.properties")
public class RedisConfig  {

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
     RedisTemplate<Long, Object> redisTemplate(){
        final RedisTemplate<Long, Object> template = new RedisTemplate<Long, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("huyva");
    }
    @Bean
    MessageListenerAdapter messageListenerAdapter(){
        return new MessageListenerAdapter(new RedisMessageSubscriberImpl());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(), topic());
    }

}
