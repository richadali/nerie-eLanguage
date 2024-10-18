package nerie.e_resources.non_schedule.controllers;

import nerie.e_resources.non_schedule.entity.State;
import nerie.e_resources.non_schedule.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nerie/e-resources/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping("/all")
    public List<State> getAllStates() {
        return stateService.getAllStates();
    }
}
