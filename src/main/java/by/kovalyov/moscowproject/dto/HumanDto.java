package by.kovalyov.moscowproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HumanDto {
    private Long id;
    private String name;
    private int age;
}
