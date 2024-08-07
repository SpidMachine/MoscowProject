package by.kovalyov.moscowproject.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class HumanDto {
    private String name;
    private int age;
}
