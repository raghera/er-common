package com.vizzavi.ecommerce.common;


public class EnvironmentException extends EcommerceRuntimeException {

   private    static final long serialVersionUID = -1762365784820133107L;
 // private final static String VERSION = "$Revision: 1.1 $";

/**
 * Default constructor of the ChargingException class.
 */
  public EnvironmentException() {
    super();
  }

/**
 * Constructor of the ChargingException class passing the exception message as parameter.<BR>
 * @param message java.lang.String. The message to be inserted in the Exception
 */
  public EnvironmentException(String message) {
    super(message);
  }

/**
 * Constructor of the ChargingException class passing the exception message and a nested exception object as parameter.<BR>
 * @param message java.lang.String. The message to be inserted in the Exception
 * @param th java.lang.Throwable. The previously generated exception
 */
  public EnvironmentException(String message, Throwable th) {
    super(message, th);
  }

  public EnvironmentException(Throwable th) {
    super(th);
  }

}
