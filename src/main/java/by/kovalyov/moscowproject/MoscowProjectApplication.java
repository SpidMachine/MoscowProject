package by.kovalyov.moscowproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class MoscowProjectApplication {

    private StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(MoscowProjectApplication.class, args);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody String payload) {
        streamBridge.send("output-topic", payload);
    }

    @Bean
    public Supplier<String> sendToKafka() {
        return () -> "hello";
    }
}
