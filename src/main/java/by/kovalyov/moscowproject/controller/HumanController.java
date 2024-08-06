package by.kovalyov.moscowproject.controller;

import by.kovalyov.moscowproject.entity.Human;
import by.kovalyov.moscowproject.service.HumanService;
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

    @GetMapping("/all")
    public List<Human> getAllHuman() {
        return humanService.getAllHumans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Human> getAllHuman(@PathVariable Long id) {
        Human human = humanService.getHumanById(id);
        return new ResponseEntity<>(human, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Human> createNewHuman(@RequestBody Human human) {
        Human newHuman = humanService.createHuman(human);
        return new ResponseEntity<>(newHuman, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Human> updateHuman(@PathVariable Long id, @RequestBody Human human) {
        Human updatedHuman = humanService.updateHuman(id, human);
        return new ResponseEntity<>(updatedHuman, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Human> deleteHuman(@PathVariable Long id) {
        Human deletedHuman = humanService.deleteHuman(id);
        return new ResponseEntity<>(deletedHuman, HttpStatus.OK);
    }
}
