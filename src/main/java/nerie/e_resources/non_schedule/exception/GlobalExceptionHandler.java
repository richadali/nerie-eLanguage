/**
 *
 */
package nerie.e_resources.non_schedule.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String TRACKING_ID = "trackingId";

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(PracticeCustomBaseException.class)
	public ResponseEntity<String> handleCustomException(HttpServletRequest request, PracticeCustomBaseException e) {
		return new ResponseEntity<String>(e.getMessage(), e.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(HttpServletRequest request, Exception e) {
		String exceptionMessage = e.getMessage().substring(e.getMessage().indexOf(":") + 1);
		exceptionMessage.trim();
		return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public String updateErrorCode(HttpServletRequest request, int statusCode) {
		String prefixedErrorCode = "";
		String trackingId = (String) request.getAttribute(TRACKING_ID);

		if (!StringUtils.isEmpty(trackingId)) {
			prefixedErrorCode = "ERROR-".concat(String.valueOf(statusCode)).concat("-")
					.concat(trackingId.substring(trackingId.length() - 4));
		}
		return prefixedErrorCode;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> validationHandler(MethodArgumentNotValidException exception) {
		String errorMsg = "validation is failed!";
		if (exception.getErrorCount() > 0) {
			List<String> errorDetails = new ArrayList<>();
			for (ObjectError error : exception.getBindingResult().getAllErrors()) {
				errorDetails.add(error.getDefaultMessage());
			}

			if (errorDetails.size() > 0)
				errorMsg = errorDetails.get(0);
		}
		return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
