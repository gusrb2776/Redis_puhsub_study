package study.redis_pubsub_study.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import study.redis_pubsub_study.model.MessageDTO;

// RedisSubService와 동일한 기능.
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    // 이거 List였는데, 여기선 Redis 저장소 사용
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // value를 JSON으로 직렬화해서 pub했으니까, 받아올 땐 역직렬화로 받아옴.
            String publishedMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
            MessageDTO messageDTO = objectMapper.readValue(publishedMessage, MessageDTO.class);

            System.out.println("messageDTO.getRoomId() = " + messageDTO.getRoomId());
            System.out.println("publishedMessage = " + publishedMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
