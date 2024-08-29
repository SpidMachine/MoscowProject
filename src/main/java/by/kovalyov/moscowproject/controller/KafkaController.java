package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.kovalyov.moscowproject.controller.KafkaController.PATH;

@RestController
@RequestMapping(PATH)
@RequiredArgsConstructor
public class KafkaController {

    public static final String PATH = "/api/messages";

    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public void sendMessage(@RequestBody HumanDto humanDto) {
        kafkaProducerService.sendMessage(humanDto);
    }
}
