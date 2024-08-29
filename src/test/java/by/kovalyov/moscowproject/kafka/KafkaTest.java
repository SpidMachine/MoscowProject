package by.kovalyov.moscowproject.kafka;

import by.kovalyov.moscowproject.entity.Human;
import by.kovalyov.moscowproject.repository.HumanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class KafkaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HumanRepository humanRepository;

    @Test
    public void givenHumanDto_whenSendingEntityWithEndPointWithSimpleProducer_thenMessageEqualsToDataFromDB() throws Exception {
        Human human = new Human(1L, "kafkaProd", 22);

        mockMvc.perform(
                post("/api/messages").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(human)));

        Thread.sleep(3000);

        Assertions.assertThat(humanRepository.findFirstByName("kafkaProd")).isEqualTo(human);
    }
}
