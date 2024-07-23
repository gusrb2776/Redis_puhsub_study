package study.redis_pubsub_study.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import study.redis_pubsub_study.model.MessageDTO;


// RedisPubService와 동일한 기능을 함. -> 컨트롤러에서 호출해서 메시지 발행하는거
@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object > redisTemplate;


    /**
     * Object publish 정확히는 MessageDTO전달시 사용
     */
    public void publish(ChannelTopic topic, MessageDTO message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

    /**
     * String Publish
     */
    public void publish(ChannelTopic topic, String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
