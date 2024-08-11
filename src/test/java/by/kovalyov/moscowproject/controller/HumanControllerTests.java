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

    @Test
    void shouldReturnAllBooks() throws Exception {
        HumanDto human = HumanDto.builder()
                .name("test")
                .age(10)
                .build();

        HumanDto human1 = HumanDto.builder()
                .name("test1")
                .age(20)
                .build();

        List<HumanDto> list = List.of(human, human1);

        Mockito.when(humanService.getAllHumans()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/human/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @Test
    void shouldReturnHumanById() throws Exception {
        HumanDto human = HumanDto.builder()
                .id(1L)
                .name("test")
                .age(10)
                .build();

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
        HumanDto human = HumanDto.builder()
                .name("test")
                .age(10)
                .build();

        mockMvc.perform(post("/api/human").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(human)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldUpdateHuman() throws Exception {
        long id = 1;

        HumanDto human = HumanDto.builder()
                .id(id)
                .name("test")
                .age(10)
                .build();

        HumanDto updatedHuman = HumanDto.builder()
                .id(id)
                .name("testik")
                .age(20)
                .build();

        Mockito.when(humanService.updateHuman(human.getId(), updatedHuman)).thenReturn(updatedHuman);

        mockMvc.perform(put("/api/human/{id}", human.getId()).contentType(MediaType.APPLICATION_JSON)
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

}
