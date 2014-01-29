package fsml.exception;

import java.util.InputMismatchException;

public class FSMConstraintCheckException extends InputMismatchException{
	
	private static final long serialVersionUID = 1L;

	public FSMConstraintCheckException(String msg){
		super(msg);
	}

}
