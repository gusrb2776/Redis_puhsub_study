package study.redis_pubsub_study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import study.redis_pubsub_study.adapter.RedisPublisher;
import study.redis_pubsub_study.adapter.RedisSubscriber;
import study.redis_pubsub_study.model.MessageDTO;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisPublisher redisPublisher;

    // 이거 이제 각 channel 별 Listener가 필요함.
    private final RedisSubscriber redisSubscriber;

    /**
     채널 구독 및
     메시지 전송
     */
    public void pubMsgChannel(String channel, MessageDTO messageDTO) {
        // 해당 채널 구독
        redisMessageListenerContainer.addMessageListener(redisSubscriber, new ChannelTopic(channel));

        // 해당 채널에 메시지 전송
        redisPublisher.publish(new ChannelTopic(channel), messageDTO);
    }

    /**
     * 구독 취소
     */
    public void cancelChannel(String channel) {
       redisMessageListenerContainer.removeMessageListener(redisSubscriber, new ChannelTopic(channel));
    }
}
