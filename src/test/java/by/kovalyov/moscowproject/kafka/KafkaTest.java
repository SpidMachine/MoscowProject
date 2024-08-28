package by.kovalyov.moscowproject.kafka;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.HumanService;
import by.kovalyov.moscowproject.service.kafka.KafkaConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class KafkaTest {

    @Autowired
    private KafkaConsumerService consumer;

    @Autowired
    private HumanService humanService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        consumer.resetLatch();
    }

    @Test
    public void givenHumanDto_whenSendingEntityWithEndPointWithSimpleProducer_thenMessageEqualsToDataFromDB() throws Exception {
        HumanDto humanDto = new HumanDto(1L, "kafkaProd", 22);

        mockMvc.perform(
                post("/api/producers/send-message").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(humanDto)));
        boolean messageConsumed = consumer.getLatch()
                .await(5, TimeUnit.SECONDS);
        humanService.createHuman(consumer.getPayload());

        assertThat(messageConsumed).isTrue();
        assertThat(humanService.getHumanById(humanDto.getId())).isEqualTo(consumer.getPayload());
    }
}
