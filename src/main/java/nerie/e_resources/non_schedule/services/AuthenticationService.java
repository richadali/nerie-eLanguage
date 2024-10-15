package nerie.e_resources.non_schedule.services;

import nerie.e_resources.non_schedule.dto.request.RegisterRequest;

public interface AuthenticationService {

	public void register(RegisterRequest request) throws Exception;

	public Long getCurrentUserLoginActivityId() throws Exception;

}