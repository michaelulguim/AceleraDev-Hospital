package gestao.errors;

import gestao.exceptions.HospitalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HospitalErrorsHandler {

	@ExceptionHandler(HospitalNotFoundException.class)
	public ResponseEntity<String> handleNotFound(final HospitalNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
