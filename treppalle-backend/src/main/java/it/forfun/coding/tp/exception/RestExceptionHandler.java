package it.forfun.coding.tp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (body instanceof String) {
			return new ResponseEntity<Object>(new ErrorInfo((String) body, status.value()), headers, status);
		}
		return new ResponseEntity<Object>(new ErrorInfo(ex, status.value()), headers, status);
	}

	/**
	 * 10.4.1 400 Bad Request The request could not be understood by the server
	 * due to malformed syntax. The client SHOULD NOT repeat the request without
	 * modifications.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return handleExceptionInternal(ex, "The provided request body is not readable!", headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { ReservationConflictException.class })
	protected ResponseEntity<Object> handleReservationConflict(ReservationConflictException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	@ExceptionHandler(value = { ReservationNotFoundException.class })
	protected ResponseEntity<Object> handleReservationNotFound(ReservationNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	
//	@ExceptionHandler(value = { UsernameNotFoundException.class })
//	protected ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex, WebRequest request) {
//		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
//	}

	/**
	 * Internal Error 500 The server encountered an unexpected condition which
	 * prevented it from fulfilling the request.
	 *
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ NullPointerException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
	    logger.error("THROWN", ex);
		return handleExceptionInternal(ex,
				"An internal error happened during the request! Please try again or contact an administrator.",
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class, IllegalArgumentException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
	    logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
