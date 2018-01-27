/**
 * 
 */
package com.test.adan.adan.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author Aramis
 *
 */
@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Unauthorized")
public class UnauthorizedException extends RuntimeException{
	
	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 1L;
	
	
}
