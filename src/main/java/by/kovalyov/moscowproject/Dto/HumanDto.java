package by.kovalyov.moscowproject.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HumanDto {
    private Long id;
    private String name;
    private int age;
}
