package nerie.e_resources.non_schedule.services;

import nerie.e_resources.non_schedule.entity.State;
import nerie.e_resources.non_schedule.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> getAllStates() {
        return stateRepository.findAll();
    }
}
