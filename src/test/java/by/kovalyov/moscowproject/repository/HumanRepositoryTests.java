package by.kovalyov.moscowproject.repository;

import by.kovalyov.moscowproject.entity.Human;
import by.kovalyov.moscowproject.service.HumanService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HumanRepositoryTests {

    @Autowired
    private HumanRepository humanRepository;

    @Test
    public void humanRepository_save_returnSavedHuman() {
        Human human = Human.builder()
                .name("test")
                .age(10)
                .build();

        Human savedHuman = humanRepository.save(human);

        Assertions.assertThat(savedHuman).isNotNull();
        Assertions.assertThat(savedHuman.getId()).isGreaterThan(0);
    }

    @Test
    public void humanRepository_getAll_returnListHumans() {
        Human human = Human.builder()
                .name("test")
                .age(10)
                .build();
        Human human1 = Human.builder()
                .name("test1")
                .age(20)
                .build();

        humanRepository.save(human);
        humanRepository.save(human1);
        List<Human> listHumans = humanRepository.findAll();

        Assertions.assertThat(listHumans).isNotNull();
        Assertions.assertThat(listHumans.size()).isEqualTo(2);
    }

    @Test
    public void humanRepository_getById_returnHuman() throws Exception {
        Human human = Human.builder()
                .id(1L)
                .name("test")
                .age(10)
                .build();

        Human savedHuman = humanRepository.save(human);
        humanRepository.findById(human.getId());

        Assertions.assertThat(savedHuman).isNotNull();
        Assertions.assertThat(savedHuman.getId()).isNotNull();
    }

    @Test
    public void humanRepository_updateHuman_returnHumanIsNotNull() {
        Human human = Human.builder()
                .name("test")
                .age(10)
                .build();

        humanRepository.save(human);
        Human savedHuman = humanRepository.findById(human.getId()).get();
        savedHuman.setName("updateTest");
        savedHuman.setAge(20);
        humanRepository.save(savedHuman);

        Assertions.assertThat(savedHuman).isNotNull();
        Assertions.assertThat(savedHuman.getName()).isEqualTo("updateTest");
        Assertions.assertThat(savedHuman.getAge()).isEqualTo(20);
    }

    @Test
    public void humanRepository_deleteHuman_returnHumanIsEmpty() {
        Human human = Human.builder()
                .name("test")
                .age(10)
                .build();

        humanRepository.save(human);
        humanRepository.deleteById(human.getId());
        Human deletedHuman = humanRepository.findById(human.getId()).orElse(null);

        Assertions.assertThat(deletedHuman).isEqualTo(null);
    }
}
