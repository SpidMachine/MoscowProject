package by.kovalyov.moscowproject.service;

import by.kovalyov.moscowproject.Dto.HumanDto;
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
    public Human createHuman(HumanDto humanDto) {
        Human human = new Human();
        human.setName(humanDto.getName());
        human.setAge(human.getAge());
        return humanRepository.save(human);
    }

    @Override
    public Human deleteHuman(Long id) {
        Human human = humanRepository.findById(id).orElseThrow();
        humanRepository.deleteById(id);
        return human;
    }

    @Override
    public Human updateHuman(Long id, HumanDto humanDto) {
        Human updatedHuman = humanRepository.findById(id).orElseThrow();

        updatedHuman.setName(humanDto.getName());
        updatedHuman.setAge(humanDto.getAge());
        humanRepository.save(updatedHuman);

        return updatedHuman;
    }
}
