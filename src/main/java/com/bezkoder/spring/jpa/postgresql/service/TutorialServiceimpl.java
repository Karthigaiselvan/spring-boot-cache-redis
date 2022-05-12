package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import com.bezkoder.spring.jpa.postgresql.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceimpl implements TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    @Override
    @Cacheable(value = "bootCache")
    public List<Tutorial> getAllTutorials(String title) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            return tutorials;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = "bootCache", key="#id",  unless = "#result == null")
    public Tutorial getTutorialById(long id) throws InterruptedException {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            return tutorialData.get();
        } else {
            return null;
        }
    }

    @Override
    @CachePut(value = "bootCache", key = "#tutorial.id")
    public Tutorial createTutorial(Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialRepository
                    .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            return _tutorial;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @CacheEvict(value = "bootCache", key = "#id")
    public String deleteTutorial(Long id) {
        try {
            tutorialRepository.deleteById(id);
            return "done";
        } catch (Exception e) {
            return "error";
        }
    }
}
