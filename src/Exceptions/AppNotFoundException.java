package Exceptions;

/**
 * This is an exception class used to signify that
 * a specified app could not be found
 * @author Michael Topolski
 * February 2016.
 */
public class AppNotFoundException extends Exception
{
    /**
     * Initialize this exception.
     * @param msg the message associated with this exception.
     */
    public AppNotFoundException(String msg) { super(msg); }
}