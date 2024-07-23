package study.redis_pubsub_study.controller;

import io.lettuce.core.RedisClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.redis_pubsub_study.model.MessageDTO;
import study.redis_pubsub_study.service.RedisService;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/send")
    public void sendMessage(@RequestParam String channel, @RequestBody MessageDTO messageDTO) {
        System.out.println("channel = " + channel);
        redisService.pubMsgChannel(channel, messageDTO);
    }

    @PostMapping("/cancel")
    public void cancelChannel(@RequestParam String channel) {
        redisService.cancelChannel(channel);
    }
}
