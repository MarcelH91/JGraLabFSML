package fsml.exception;

import java.util.InputMismatchException;

public class FSMMismatchException extends InputMismatchException{
	
	private static final long serialVersionUID = 1L;

	public FSMMismatchException(String msg){
		super(msg);
	}

}
