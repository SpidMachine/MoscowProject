package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.Dto.HumanDto;
import by.kovalyov.moscowproject.entity.Human;
import by.kovalyov.moscowproject.service.HumanService;
import jdk.javadoc.doclet.Reporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HumanController {

    private final HumanService humanService;

    @GetMapping("/human/all")
    public ResponseEntity<List<HumanDto>> getAllHuman() {
        List<HumanDto> listHumans = humanService.getAllHumans();

        if (listHumans.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(listHumans, HttpStatus.OK);
    }

    @GetMapping("/human/{id}")
    public ResponseEntity<HumanDto> getAllHuman(@PathVariable Long id) {
        try {
            HumanDto human = humanService.getHumanById(id);
            return new ResponseEntity<>(human, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/human")
    public ResponseEntity<HumanDto> createNewHuman(@RequestBody HumanDto humanDto) {
        try {
            HumanDto newHuman = humanService.createHuman(humanDto);
            return new ResponseEntity<>(newHuman, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/human/{id}")
    public ResponseEntity<HumanDto> updateHuman(@PathVariable Long id, @RequestBody HumanDto humanDto) {
        try {
            HumanDto updatedHuman = humanService.updateHuman(id, humanDto);
            return new ResponseEntity<>(updatedHuman, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/human/{id}")
    public ResponseEntity<HumanDto> deleteHuman(@PathVariable Long id) {
        try {
            HumanDto deletedHuman = humanService.deleteHuman(id);
            return new ResponseEntity<>(deletedHuman, HttpStatus.valueOf(204));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
