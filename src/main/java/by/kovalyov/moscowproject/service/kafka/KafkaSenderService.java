package by.kovalyov.moscowproject.service.kafka;

import by.kovalyov.moscowproject.dto.HumanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSenderService {

    private final StreamBridge streamBridge;

    public void sendMessage(HumanDto humanDto) {
        streamBridge.send("sendToKafka-out-0", humanDto);
    }
}
