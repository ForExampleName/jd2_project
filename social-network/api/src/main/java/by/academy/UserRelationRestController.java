package by.academy;

import by.academy.pojo.relation.UserRelation;
import by.academy.repository.UserRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserRelationRestController {
    @Autowired
    private UserRelationRepository userRelationRepository;

    @GetMapping("/relations/{id}")
    public ResponseEntity<UserRelation> getUserRelation(@PathVariable("id") String id) {
        Optional<UserRelation> userRelation = userRelationRepository.findById(id);
        if (!userRelation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRelation.get(), HttpStatus.OK);
    }

    @GetMapping("/relations")
    public ResponseEntity<List<UserRelation>> getUserRelations() {
        final List<UserRelation> userRelations = userRelationRepository.findAll();
        if (userRelations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRelations, HttpStatus.OK);
    }

    @GetMapping("/relations/user/{userId}")
    public ResponseEntity<List<UserRelation>> getUserRelationsByUserId(@PathVariable("userId") String userId) {
        List<UserRelation> userRelation = userRelationRepository.getUserRelationsByUserId(userId);
        if (!userRelation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRelation, HttpStatus.OK);
    }

    @GetMapping("/relations/user/{userId}")
    public ResponseEntity<List<UserRelation>> getUserRelations(
            @PathVariable("userId") String userId) {
        final List<UserRelation> userRelations = userRelationRepository.findAll();
        if (userRelations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRelations, HttpStatus.OK);
    }

    @PostMapping("/relations")
    public ResponseEntity<UserRelation> saveUserRelation(@RequestBody UserRelation relation) {
        UserRelation userRelation = userRelationRepository.save(relation);
        if (userRelation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRelation, HttpStatus.CREATED);
    }

    @DeleteMapping("/relations/{id}")
    public ResponseEntity<UserRelation> deleteUserRelation(@PathVariable String id) {
        Optional<UserRelation> userRelation = userRelationRepository.findById(id);
        if (!userRelation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRelationRepository.delete(userRelation.get());
        return new ResponseEntity<>(userRelation.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/relations/{id}")
    public ResponseEntity<UserRelation> updateUserRelation(
            @RequestBody UserRelation newUserRelation, @PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}
