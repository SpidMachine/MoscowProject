package by.kovalyov.moscowproject.service;

import by.kovalyov.moscowproject.entity.Human;
import by.kovalyov.moscowproject.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HumanServiceImpl implements HumanService {

    private final HumanRepository humanRepository;

    @Override
    public Human getHumanById(Long id) {
        return humanRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Human> getAllHumans() {
        return humanRepository.findAll();
    }

    @Override
    public Human createHuman(Human human) {
        return humanRepository.save(human);
    }

    @Override
    public Human deleteHuman(Long id) {
        Human human = humanRepository.findById(id).orElseThrow();
        humanRepository.deleteById(id);
        return human;
    }

    @Override
    public Human updateHuman(Long id, Human human) {
        Human updatedHuman = humanRepository.findById(id).orElseThrow();

        updatedHuman.setName(human.getName());
        updatedHuman.setAge(human.getAge());
        humanRepository.save(updatedHuman);

        return updatedHuman;
    }
}
