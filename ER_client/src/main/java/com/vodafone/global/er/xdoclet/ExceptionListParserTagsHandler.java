package com.vodafone.global.er.xdoclet;



import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import xdoclet.XDocletTagSupport;





/**

 *  XDoclet customization for vizzavi, inserts text for from/to object to/from datatype

 *  

 *   

 *  @author Shankar Sundaram

 *  @version $Revision: 0.1 

 */

public class ExceptionListParserTagsHandler extends XDocletTagSupport

{		

	

	public String isThrowsException(Properties input)

	{

		String value         =  input.getProperty("value");

		String exceptionType = 	input.getProperty("exceptionType").trim();

		String exceptionVar  =  input.getProperty("exceptionVariable").trim();

		String ret="";

		

		

		if ( !(value.equals("")) && (value.trim() != ""))

		{

			//StringBuffer resultString = new StringBuffer();				 		

	 		value = value.substring(6,value.length());

	 		value = value.trim();

	

	 		StringTokenizer stringTokenizer = new StringTokenizer(value,",");

	

	  	    while (stringTokenizer.hasMoreTokens())

	  	    {

	          	String currentToken = stringTokenizer.nextToken().trim();

			if(exceptionType.equals(currentToken)){

				ret="throw new "+exceptionType+"("+exceptionVar+");\n";

			}

		    }

		}

		

		return ret;

	}

	

	public String parse(Properties input)

	{

		String value         =  (String) input.get("value");

		String exceptionType = 	(String) input.get("exceptionType");

		String exceptionVar  =  (String) input.get("exceptionVariable");

		

		if ( !(value.equals("")) && (value.trim() != ""))

		{

			

			StringBuffer resultString = new StringBuffer();				 		

	 		value = value.substring(6,value.length());

	 		value = value.trim();

	

	

	 		StringTokenizer stringTokenizer = new StringTokenizer(value,",");

	

	  	    if (exceptionType.equals("ExceptionAdapter"))

	  	        resultString.append("String exceptionName = (((" + exceptionType + ") " + exceptionVar + ").originalException).getClass().getName();\n");

			else

				resultString.append("String exceptionName = ((" + exceptionType + ") " + exceptionVar + ").getClass().getName();\n");

	  	    

	  	    resultString.append("					Vector exceptionVector = new Vector();\n" );

	

	

	  	    Vector<String> exceptionList = new Vector<String>();

	  	    

	  	    while (stringTokenizer.hasMoreTokens())

	  	    {

	          	String currentToken = stringTokenizer.nextToken();

  	          	resultString = resultString.append("					exceptionVector.add(\""+ currentToken.trim() + "\");\n");

  	          	exceptionList.add(currentToken.trim());

	          	

	      	}	

	     	

	     	if (exceptionType.equals("ExceptionAdapter"))

	     	   resultString = resultString.append("					if (exceptionVector.contains(exceptionName)){\n					 Exception generatedException = ((" + exceptionType + ")" + exceptionVar + ").originalException ;\n                     ");

	     	else

	     	   resultString = resultString.append("					if (exceptionVector.contains(exceptionName)){\n					 Exception generatedException = (" + exceptionType + ")" + exceptionVar + ";\n                     ");

	     	

	     	Enumeration<String> enums = exceptionList.elements();

	     	

	     	String constantString = "if (generatedException instanceof";

	     	String throwString = "                          throw ";

	     	

			if (exceptionType.equals("EcommerceException"))	

			{

				if  (exceptionList.contains("com.vizzavi.ecommerce.business.common.EcommerceException"))

				{

					resultString = resultString.append(constantString+"  EcommerceException){\n");

				    resultString = resultString.append(throwString + "(EcommerceException) generatedException ;\n                      }\n");

				}

				

			}

			else     

	     	while (enums.hasMoreElements())

	     	{

	     		String currentToken = enums.nextElement();

	     		resultString = resultString.append(constantString+" "+ currentToken+")\n");

	     		resultString = resultString.append(throwString + "(" + currentToken +") generatedException ;\n");

	     	}	     		     	

	     	

	     	if (exceptionType.equals("ExceptionAdapter")){

	     	   	resultString = resultString.append("                     } \n                    else \n					  log.error(\" Exception during serialization \", ((ExceptionAdapter)" + exceptionVar + ").originalException);");

			}

			else{

				resultString = resultString.append("                     } \n                    else \n					  log.error(\" Exception during serialization \", " + exceptionVar +");");

	     	}

	     	

	     	return resultString.toString();		

		}

		else{

			   if (exceptionType.equals("ExceptionAdapter"))

			   {

		         return "log.error(\" Exception during serialization \", ((ExceptionAdapter) " + exceptionVar + ").originalException);";

			   }

			   return "";  

			}

	}

}

