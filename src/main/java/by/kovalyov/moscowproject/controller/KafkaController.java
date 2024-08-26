package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.kafka.KafkaSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaSenderService kafkaSenderService;

    @GetMapping("/send")
    public void sendMessage() {
        kafkaSenderService.sendMessage(new HumanDto(1L, "kafka", 10));
    }
}
