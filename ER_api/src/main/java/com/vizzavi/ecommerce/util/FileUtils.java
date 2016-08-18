package com.vizzavi.ecommerce.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private final static String	ENV_DOT_PROPS = "environment/token/env.properties";
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * @param fileName the filename
	 * @return the contents of the file as a string
	 */
	public static String getFile( String fileName ) 
			throws FileNotFoundException, FileNotFoundException, IOException
			{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String input = null;
		StringBuffer inBuf = new StringBuffer();
		while((input = in.readLine()) != null) {
			inBuf.append(input);
			inBuf.append("\n");
		}
		in.close();
		return inBuf.toString();
			}

	public static String replace(String input, String var, String value)
	{
		StringBuffer buf = new StringBuffer(input);
		int index = input.lastIndexOf(var);

		while (index>0) {
			buf.replace(index, index+var.length(), value);
			index = input.lastIndexOf(var, index-1);
		}
		return buf.toString();
	}

	/**
	 * CR1222 recursive delete of files in a dir
	 * @param args
	 */
	static public boolean deleteDirectory(File path) {
		if( path.exists() ) {
			for(File file : path.listFiles()) {
				if(file.isDirectory()) {
					deleteDirectory(file);
				}
				else {
					file.delete();
				}
			}
		}
		return( path.delete() );
	}

	public static void main(String args[])
	{
		try {
			writeProperty("test", "hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * updates the value of a property in env.properties
	 * @param propertyName
	 * @param propertyValue
	 * @param fileName
	 * @throws Exception 
	 */
	public static void writeProperty(String propertyName, String propertyValue) throws IOException	{
		writeProperty(propertyName, propertyValue, ENV_DOT_PROPS);
	}


	/**
	 * updates the value of a property in the specified file 
	 * @param propertyName
	 * @param propertyValue
	 * @param fileName
	 */
	public static void writeProperty(String propertyName, String propertyValue, String fileName) throws IOException	{
		//find env.properties
		//find the property in the file
		//replace the value of the property

		RandomAccessFile f = new RandomAccessFile(new File(fileName ),"rw");
		//BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "", newline="";

		while((line = f.readLine()) != null)	{

			if (line.indexOf('=')+1<line.indexOf('#') || line.indexOf('=')>0 && line.indexOf('#')<0)	
				//picks up 'prop = value #some comment on property' but not '#comment: a = b'
			{
				//now split on the equals and extract property name
				String[] linevals=line.split("=");
				String propName= linevals[0];
				//String propVal=linevals[1];
				log("found property " +linevals[0] );

				if (propertyName.trim().equals(propName.trim()))	{
					int lengthOfOriginalLine = line.length();

					log("updating property "+ propName);
					//now replace the value with the one supplied
					log("pointer="+f.getFilePointer());
					newline=propName+"="+propertyValue+"\n";
					int lengthOfNewLine = newline.length()-1;	//don't forget the \n character
					log("writing "+newline);
					long endOfOldPropLine = f.getFilePointer();
					long startOfPropLine = endOfOldPropLine - lengthOfOriginalLine;
					if (lengthOfNewLine > lengthOfOriginalLine)	{
						log("lengthOfNewLine > lengthOfOriginalLine");
						//in this case we need to push out the existing bytes beyond the end of the new line, so they don't get overwritten
						//then write the new line
						//so we start by making the file longer:
						long originalLength = f.length();
						f.setLength(originalLength+lengthOfNewLine - lengthOfOriginalLine);
						//now go to the end of the file and move the last bytes out to fill the new space
						long writePos =startOfPropLine+lengthOfNewLine;
						long readPos = endOfOldPropLine;
						byte[] buf = new byte[(int) (originalLength-endOfOldPropLine)];

						f.seek(readPos);	 
						f.read(buf);
						f.seek(writePos);
						f.write(buf);

					}	else if (lengthOfNewLine < lengthOfOriginalLine)	{
						//in this case we need to pull in the existing bytes beyond the end of the new line, so they don't get overwritten
						//then move all the following chars up to avoid leaving traces of the original value
						long writePos = startOfPropLine+lengthOfNewLine;	//ie start writing from exactly the place after the new property was written
						long readPos = endOfOldPropLine;
						f.seek(readPos);
						byte[] buf = new byte[1024];
						int n;
						while (-1 != (n = f.read(buf))) {
							f.seek(writePos);
							f.write(buf, 0, n);
							readPos += n;
							writePos += n;
							f.seek(readPos);
						}

						f.setLength(writePos);
					}	


					f.seek(startOfPropLine-1);
					//now overwrite the existing line
					f.writeBytes(newline);


				}
			}

		}	//end while loop through file

		f.close();


	}






	private static void log(String s)	{
		logger.debug(s);
	}

}