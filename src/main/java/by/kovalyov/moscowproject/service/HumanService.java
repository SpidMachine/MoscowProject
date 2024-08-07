package by.kovalyov.moscowproject.service;

import by.kovalyov.moscowproject.Dto.HumanDto;
import by.kovalyov.moscowproject.entity.Human;

import java.util.List;

public interface HumanService {
    Human getHumanById(Long id);
    List<Human> getAllHumans();
    Human createHuman(HumanDto humanDto);
    Human deleteHuman(Long id);
    Human updateHuman(Long id, HumanDto humanDto);
}
