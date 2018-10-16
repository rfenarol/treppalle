package it.forfun.coding.tp.controller;

import org.springframework.validation.BindingResult;

/**
 * 
 * @author rfenarol
 *
 */
public class ValidatorUtil {
	
	/**
	 * Is not a standard this is a way of rendering to the client the server error
	 * @param result
	 */
	public static void raiseFirstError(BindingResult result) {
		if (result.hasFieldErrors()) {
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        } else if (result.hasGlobalErrors()) {
			throw new IllegalArgumentException(result.getGlobalError().getDefaultMessage());
		} else if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().get(0).getDefaultMessage());
		} 
	}

}
