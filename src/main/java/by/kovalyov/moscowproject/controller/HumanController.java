package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.dto.HumanDto;
import by.kovalyov.moscowproject.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.kovalyov.moscowproject.controller.HumanController.PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(PATH)
public class HumanController {

    public static final String PATH = "/api/humans";
    public static final String ID_PATH = "/{id}";

    private final HumanService humanService;

    @GetMapping
    public ResponseEntity<List<HumanDto>> getAllHuman() {
        List<HumanDto> listHumans = humanService.getAllHumans();

        if (listHumans.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(listHumans, HttpStatus.OK);
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<HumanDto> getHumanById(@PathVariable Long id) {
        try {
            HumanDto human = humanService.getHumanById(id);
            return new ResponseEntity<>(human, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HumanDto> createNewHuman(@RequestBody HumanDto humanDto) {
        try {
            HumanDto newHuman = humanService.createHuman(humanDto);
            return new ResponseEntity<>(newHuman, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<HumanDto> updateHuman(@PathVariable Long id, @RequestBody HumanDto humanDto) {
        try {
            HumanDto updatedHuman = humanService.updateHuman(id, humanDto);
            return new ResponseEntity<>(updatedHuman, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<HumanDto> deleteHuman(@PathVariable Long id) {
        try {
            HumanDto deletedHuman = humanService.deleteHuman(id);
            return new ResponseEntity<>(deletedHuman, HttpStatus.valueOf(204));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
