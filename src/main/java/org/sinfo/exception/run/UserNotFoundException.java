package org.sinfo.exception.run;

/**
 * @author yelouardi
 * UserNotFoundException
 */
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String format) {
		super(format);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5064459645855260632L;

}
