package fsml.exception;

import java.util.InputMismatchException;

public class FSMInputMismatchException extends InputMismatchException{

	private static final long serialVersionUID = 1L;
	
	public FSMInputMismatchException(String msg){
		super(msg);
	}

}
