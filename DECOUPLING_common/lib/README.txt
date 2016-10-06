the jaxb-osgi jar file is a build of the head of the jaxb project, patched to include a bugfix for http://java.net/jira/browse/JAXB-927 

It includes the xjc component, which is only required for generating the java source files from the xml.

For typical client usage, this jar file is not required. The standard jaxb 2.2.7 api, core and impl jars can be used instead.

Matt Darwin, January 2014

The problem:
http://stackoverflow.com/questions/13035975
https://java.net/jira/browse/JAXB-927

jaxb xjc doesn't conform to the spec, which causes problems for us when using optional boolean properties


Update Jan 2015: this is fixed in version 2.2.9.

Before that fix, I had to patch it myself, as per below:

The fix:

start working on the jaxb project
git clone git://java.net/jaxb~v2 

spend 5 hours fixing dependencies

once that's working, build using maven:

maven clean install package

This produces jar files 

you need the following to run the build script:
jaxb-api-2.2.x.jar		jaxb-osgi-2.2.8-SNAPSHOT.jar

The patch:

Modify the following file:
jaxb-ri/xjc/src/main/java/com/sun/tools/xjc/reader/xmlschema/bindinfo/BIProperty.java

line ~365:
//VODAFONE patch
        if (prop.isOptionalPrimitive() && getOptionalPropertyMode() == OptionalPropertyMode.PRIMITIVE && !prop.getTypes().isEmpty() )	{
			String propertyName = prop.getTypes().get(0).getTypeName().getLocalPart();
        	if( "boolean".equals(propertyName) )	{
	        	System.out.println("setting default value for boolean [" +name+"] to false");
	        	prop.defaultValue= CDefaultValue.create(CBuiltinLeafInfo.BOOLEAN, new XmlString("false"));
			} else if ("int".equals(propertyName))	{
				System.out.println("setting default value for int [" +name+"] to 0");
	        	prop.defaultValue= CDefaultValue.create(CBuiltinLeafInfo.INT, new XmlString("0"));
			}	else if("double".equals(propertyName))	{
				System.out.println("setting default value for double [" +name+"] to 0");
	        	prop.defaultValue= CDefaultValue.create(CBuiltinLeafInfo.DOUBLE, new XmlString("0"));
			}	else if("long".equals(propertyName))	{
				System.out.println("setting default value for long [" +name+"] to 0");
	        	prop.defaultValue= CDefaultValue.create(CBuiltinLeafInfo.LONG, new XmlString("0"));
	        }
        }

only jaxb-xjc*.jar is modified by the patch

jaxb-osgi-2.2.8-SNAPSHOT.jar contains all the necessary files to build the schemas

so copy that to DECOUPLING_common/lib

to run the build scripts you need the latest version of ant - 1.6.5 doesn't work

