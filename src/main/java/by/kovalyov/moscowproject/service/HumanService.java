package by.kovalyov.moscowproject.service;

import by.kovalyov.moscowproject.entity.Human;

import java.util.List;

public interface HumanService {
    Human getHumanById(Long id);
    List<Human> getAllHumans();
    Human createHuman(Human human);
    Human deleteHuman(Long id);
    Human updateHuman(Long id, Human human);
}
