package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.Dto.HumanDto;
import by.kovalyov.moscowproject.service.HumanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class HumanControllerTests {

    @MockBean
    private HumanService humanService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<HumanDto> getAllHuman() {
        HumanDto human = HumanDto.builder()
                .name("test")
                .age(10)
                .build();

        HumanDto human1 = HumanDto.builder()
                .name("test1")
                .age(20)
                .build();

        return List.of(human, human1);
    }

    @Test
    void shouldReturnHumanById() throws Exception {
        HumanDto human = new HumanDto(1L, "test", 10);

        Mockito.when(humanService.getHumanById(human.getId())).thenReturn(human);

        mockMvc.perform(get("/api/human/{id}", human.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(human.getId()))
                .andExpect(jsonPath("$.name").value(human.getName()))
                .andExpect(jsonPath("$.age").value(human.getAge()))
                .andDo(print());
    }

    @Test
    void shouldCreateHuman() throws Exception {
        HumanDto humanDto = new HumanDto(1L, "test", 10);

        mockMvc.perform(post("/api/human").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(humanDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldUpdateHuman() throws Exception {
        long id = 1;

        HumanDto humanDto = new HumanDto(id, "test", 10);
        HumanDto updatedHuman = new HumanDto(id, "testik", 20);

        Mockito.when(humanService.updateHuman(humanDto.getId(), updatedHuman)).thenReturn(updatedHuman);

        mockMvc.perform(put("/api/human/{id}", humanDto.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedHuman)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedHuman.getId()))
                .andExpect(jsonPath("$.name").value(updatedHuman.getName()))
                .andExpect(jsonPath("$.age").value(updatedHuman.getAge()))
                .andDo(print());
    }

    @Test
    void shouldDeleteHuman() throws Exception {
        HumanDto humanDto = new HumanDto(1L, "test", 10);

        Mockito.when(humanService.deleteHuman(humanDto.getId())).thenReturn(humanDto);
        mockMvc.perform(delete("/api/human/{id}", humanDto.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        Mockito.when(humanService.getAllHumans()).thenReturn(getAllHuman());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/human/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andDo(print());
    }

}
