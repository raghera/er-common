package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.ReasonCode;

/**
* Simple bean which encapsulates the result of a usage capture call to the charging subsystem.
* @author georgeb
*/
public class UsageCompletionResult implements java.io.Serializable
{
   private    static final long serialVersionUID = -5208662228382268427L;


    protected ReasonCode reasonCode;


    /**
    *
    */
    public UsageCompletionResult()
    {
    }

    /**
    * Obtain the reasonCode code for this result.
    * @return the reasonCode for success/failiure.
    */
    public ReasonCode getReasonCode()
    {
        return this.reasonCode;
    }

    /**
    * Set the reason code for this result
      This is used internally by the ER2 system
    * @param c the reason code for success/failure
    */
    public void setReasonCode(ReasonCode c)
    {
        this.reasonCode = c;
    }

    /**
    * Return string representation.
    */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(  "{ " );
        sb.append(  "reasonCode=" );
        sb.append(reasonCode.toString());
        sb.append(  " }" );
        return sb.toString();

    }
}
