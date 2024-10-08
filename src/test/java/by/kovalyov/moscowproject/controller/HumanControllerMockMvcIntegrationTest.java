package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.entity.Human;
import by.kovalyov.moscowproject.repository.HumanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HumanControllerMockMvcIntegrationTest {

    @Autowired
    private HumanRepository humanRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenHuman_whenAdd_thenStatus201andHumanReturned() throws Exception {
        Human testHuman = createTestHuman(new Human(1L, "test", 10));

        mockMvc.perform(
                        post("/api/human")
                                .content(objectMapper.writeValueAsString(testHuman))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.age").value(10))
                .andDo(print());
        Assertions.assertThat(humanRepository.findById(testHuman.getId())).isNotNull();
    }

    @Test
    void givenId_whenGetExistingHuman_thenStatus200andHumanReturned() throws Exception {
        long humanId = createTestHuman(new Human(1L, "test", 10)).getId();

        mockMvc.perform(
                        get("/api/human/{id}", humanId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.age").value(10))
                .andDo(print());
        Assertions.assertThat(humanRepository.findById(humanId)).isNotNull();
    }

    @Test
    void givenHuman_whenUpdate_thenStatus200andUpdatedHumanReturns() throws Exception {
        Human human = createTestHuman(new Human(1L, "test", 10));

        mockMvc.perform(
                        put("/api/human/{id}", human.getId())
                                .content(objectMapper.writeValueAsString(new Human(1L, "testik", 20)))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("testik"))
                .andExpect(jsonPath("$.age").value(20))
                .andDo(print());
        Assertions.assertThat(humanRepository.findById(human.getId())).isNotNull();
        Assertions.assertThat(humanRepository.findFirstByName(human.getName())).isEqualTo(human);
    }

    @Test
    void givenId_whenDelete_thenStatus204andDeletedHumanReturns() throws Exception {
        Human human = createTestHuman(new Human(1L, "test", 10));

        mockMvc.perform(
                        delete("/api/human/{id}", human.getId()))
                .andExpect(status().is(204))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.age").value(10))
                .andDo(print());
        Assertions.assertThat(humanRepository.findFirstByName("test")).isNull();
    }

    @Test
    void givenId_whenDelete_thenStatus404anExceptionThrow() throws Exception {
        mockMvc.perform(
                        get("/api/human/1"))
                .andExpect(status().isNotFound());
    }


    private Human createTestHuman(Human human) {
        return humanRepository.save(human);
    }
}
