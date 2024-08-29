package by.kovalyov.moscowproject.service.kafka;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.HumanService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Getter
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private HumanDto payload;

    private CountDownLatch latch = new CountDownLatch(1);

    private final HumanService humanService;

    public void receiveMessage(Message<HumanDto> message) {
        payload = message.getPayload();
        humanService.createHuman(message.getPayload());
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}
