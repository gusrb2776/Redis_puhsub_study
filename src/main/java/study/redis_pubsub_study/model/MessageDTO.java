package study.redis_pubsub_study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    // 메시지 내용
    private String message;
    // 메시지 발신자
    private String sender;
    // 타겟 채널
    private String roomId;
}
