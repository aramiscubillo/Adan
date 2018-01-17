package com.test.adan.adan.contracts;

import com.test.adan.adan.pojos.PersonPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseResponse.
 */
public class BaseResponse {

	/** The code. */
	private Integer code;
	
	/** The code message. */
	private String codeMessage;
	
	/** The error message. */
	private String errorMessage;

	/** The total pages. */
	private Integer totalPages;
	
	/** The total elements. */
	private Long totalElements;
	
	/** The token params. */
	private String params;
	
	private PersonPOJO person;

	/**
	 * Instantiates a new base response.
	 */
	public BaseResponse() {
		super();
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Gets the code message.
	 *
	 * @return the code message
	 */
	public String getCodeMessage() {
		return codeMessage;
	}

	/**
	 * Sets the code message.
	 *
	 * @param codeMessage the new code message
	 */
	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the total pages.
	 *
	 * @return the total pages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * Sets the total pages.
	 *
	 * @param totalPages the new total pages
	 */
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * Gets the total elements.
	 *
	 * @return the total elements
	 */
	public Long getTotalElements() {
		return totalElements;
	}

	/**
	 * Sets the total elements.
	 *
	 * @param totalElements the new total elements
	 */
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * Gets the token params.
	 *
	 * @return the token params
	 */
	public String getTokenParams() {
		return params;
	}

	/**
	 * Sets the token params.
	 *
	 * @param tokenParams the new token params
	 */
	public void setTokenParams(String tokenParams) {
		this.params = tokenParams;
	}

	public PersonPOJO getPerson() {
		return person;
	}

	public void setPerson(PersonPOJO person) {
		this.person = person;
	}

	
}
