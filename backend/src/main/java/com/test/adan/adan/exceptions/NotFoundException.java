/**
 * 
 */
package com.test.adan.adan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Aramis
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not found perrito")
public class NotFoundException extends RuntimeException{
	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
	}
	
	//custom message funciona sin el reason en el responseStatus
	public NotFoundException(String msg) {
		super(msg);
	}
	
}
