package by.kovalyov.moscowproject.service;

import by.kovalyov.moscowproject.dto.HumanDto;

import java.util.List;

public interface HumanService {
    HumanDto getHumanById(Long id);
    List<HumanDto> getAllHumans();
    HumanDto createHuman(HumanDto humanDto);
    HumanDto deleteHuman(Long id);
    HumanDto updateHuman(Long id, HumanDto humanDto);
}
