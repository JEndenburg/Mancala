package nl.sogyo.mancala.domain;

public class InvalidPlayException extends RuntimeException 
{
	private static final long serialVersionUID = 5365053190803849878L;
	
	public InvalidPlayException()
	{
		super();
	}
	
	public InvalidPlayException(String message)
	{
		super(message);
	}
	
	public InvalidPlayException(Throwable cause)
	{
		super(cause);
	}
	
	public InvalidPlayException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
