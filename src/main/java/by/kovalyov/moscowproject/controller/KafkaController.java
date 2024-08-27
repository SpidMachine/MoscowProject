package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producers")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody HumanDto humanDto) {
        kafkaProducerService.sendMessage(humanDto);
    }
}
