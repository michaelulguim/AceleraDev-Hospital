package gestao.errors;

import gestao.exceptions.HospitalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HospitalErrorsHandler {

	@ExceptionHandler(HospitalNotFoundException.class)
	public ResponseEntity<String> handleNotFound(final HospitalNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleArgumentNotValid(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();
		return new  ResponseEntity<String>(fieldErrors.stream().map(x -> x.toString()).collect(Collectors.joining()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException ex) {
		final Set<ConstraintViolation<?>> fieldErrors = ex.getConstraintViolations();
		return new  ResponseEntity<List<String>>(fieldErrors.stream().map(x -> x.getMessage() ).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
	}
}
