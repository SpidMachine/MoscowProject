package by.kovalyov.moscowproject.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HumanDto {
    private String name;
    private int age;
}
