package com.example.workouttracker.api;

import com.example.workouttracker.service.SetService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/sets")
public class SetController {

    private final SetService setService;

    public SetController(SetService setService) {
        this.setService = setService;
    }

    @DeleteMapping("/{setId}")
    public void deleteSetById(@PathVariable Long setId) {
        setService.deleteSetById(setId);
    }
}
