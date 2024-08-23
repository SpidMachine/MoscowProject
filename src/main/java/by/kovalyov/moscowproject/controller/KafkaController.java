package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.kafka.KafkaSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaSenderService kafkaSenderService;

    @GetMapping("/send")
    public void sendMessage() {
        kafkaSenderService.sendMessage("my-topic", "hello kafka");
    }
}
