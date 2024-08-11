package by.kovalyov.moscowproject.service;

import by.kovalyov.moscowproject.Dto.HumanDto;
import by.kovalyov.moscowproject.entity.Human;
import by.kovalyov.moscowproject.mapper.HumanMapper;
import by.kovalyov.moscowproject.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HumanServiceImpl implements HumanService {

    private final HumanRepository humanRepository;

    @Override
    public HumanDto getHumanById(Long id) {
        Human human = humanRepository.findById(id).orElseThrow();
        return HumanMapper.mapToHumanDto(human);
    }

    @Override
    public List<HumanDto> getAllHumans() {
        List<Human> humanList = humanRepository.findAll();
        return humanList.stream().map(HumanMapper::mapToHumanDto).collect(Collectors.toList());
    }

    @Override
    public HumanDto createHuman(HumanDto humanDto) {
        Human human = Human.builder()
                .name(humanDto.getName())
                .age(humanDto.getAge())
                .build();

        Human createdHuman = humanRepository.save(human);

        return HumanMapper.mapToHumanDto(createdHuman);
    }

    @Override
    public HumanDto deleteHuman(Long id) {
        Human human = humanRepository.findById(id).orElseThrow();
        humanRepository.deleteById(id);
        return HumanMapper.mapToHumanDto(human);
    }

    @Override
    public HumanDto updateHuman(Long id, HumanDto humanDto) {
        Human existingHuman = humanRepository.findById(id).orElseThrow();

        existingHuman.setName(humanDto.getName());
        existingHuman.setAge(humanDto.getAge());
        Human updatedHuman = humanRepository.save(existingHuman);

        return HumanMapper.mapToHumanDto(updatedHuman);
    }
}
