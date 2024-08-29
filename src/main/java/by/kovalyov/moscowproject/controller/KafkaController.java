package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.kafka.KafkaConsumerService;
import by.kovalyov.moscowproject.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;

    @PostMapping
    public void sendMessage(@RequestBody HumanDto humanDto) {
        kafkaProducerService.sendMessage(humanDto);
    }

    @GetMapping
    public ResponseEntity<HumanDto> getMessage() {
        try {
            return new ResponseEntity<>(kafkaConsumerService.getPayload(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
