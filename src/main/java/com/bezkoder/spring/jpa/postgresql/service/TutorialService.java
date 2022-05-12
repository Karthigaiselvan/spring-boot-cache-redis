package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TutorialService {
    List<Tutorial> getAllTutorials(String title);

    Tutorial getTutorialById(long id) throws InterruptedException;

    Tutorial createTutorial(Tutorial tutorial);

    String deleteTutorial(Long id);
}
