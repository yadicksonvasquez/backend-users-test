/**
 * 
 */
package com.nisum.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.nisum.dto.MessageDTO;

/**
 * @author yadickson
 *
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(value = { NisumTestException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public MessageDTO getRateException(NisumTestException ex, WebRequest request) {

		return new MessageDTO(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public MessageDTO methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}

	private MessageDTO processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
		StringBuilder msg = new  StringBuilder("");
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			msg.append("(field:").append(fieldError.getField()).append(", Message:").append(fieldError.getDefaultMessage()).append(")").append(";");
		}
		return new MessageDTO(msg.toString());
	}

}
