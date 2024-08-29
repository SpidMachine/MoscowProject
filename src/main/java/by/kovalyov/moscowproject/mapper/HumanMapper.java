package by.kovalyov.moscowproject.mapper;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.entity.Human;

public class HumanMapper {

    public static HumanDto mapToHumanDto(Human human) {
        if (human == null) {
            return null;
        }

        return new HumanDto(
                human.getName(),
                human.getAge()
        );
    }

    public static Human mapToHuman(HumanDto humanDto) {
        return new Human(
                1L,
                humanDto.getName(),
                humanDto.getAge()
        );
    }
}
