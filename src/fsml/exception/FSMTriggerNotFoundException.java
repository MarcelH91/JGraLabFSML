package fsml.exception;

import java.util.InputMismatchException;

public class FSMTriggerNotFoundException extends InputMismatchException{

	private static final long serialVersionUID = 1L;
	
	public FSMTriggerNotFoundException(String msg){
		super(msg);
	}

	
}
