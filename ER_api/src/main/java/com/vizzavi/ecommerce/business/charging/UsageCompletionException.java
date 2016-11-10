package com.vizzavi.ecommerce.business.charging;

/**
* The exception to represent failiures in usage complete
* against the charging subsystem.
*/

public class UsageCompletionException extends Exception {
    public UsageCompletionException() { super(); }
   private    static final long serialVersionUID = -5608855347979867794L;
    public UsageCompletionException(String s) { super(s); }
}
