package by.kovalyov.moscowproject.service.kafka;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final HumanService humanService;

    public void receiveMessage(Message<HumanDto> message) {
        System.out.println("Received message: " + message.getPayload());
        humanService.createHuman(message.getPayload());
    }
}
