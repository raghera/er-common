package com.vizzavi.ecommerce.business.selfcare;

/**
 *
 * This is the class representing one transaction sub record
 *
 * @author Waleed Hassan
 *   
 */
public class TransactionSubRecord implements java.io.Serializable
{
  
  private static final long serialVersionUID = 7349445238237684807L; 
		
  String id, parentID; 
  String metaData1,metaData2;
  String eventType;
  
  @Override
public String toString()
  {
    String ret="";
//Amd001 [start]
    //ret+=id+","+parentID+","+metaData1+","+metaData2+","+eventType; 
    ret+="<id>"+id+"</id>"+"<parentID>"+parentID+"</parentID>"+"<eventType>"+eventType+"</eventType>"+"<metaData1>"+metaData1+"</metaData1>"+"<metaData2>"+metaData2+"</metaData2>";
//Amd001 [end]
    return ret;
  }
//Amd001 [start]
    /*
  public TransactionSubRecord(String content)
  {
    id=content.substring(content.indexOf("id")+3,content.indexOf("parentID")-1);  
    parentID=content.substring(content.indexOf("parentID")+9,content.indexOf("metaData1")-1);  
    metaData1=content.substring(content.indexOf("metaData1")+10,content.indexOf("metaData2")-1);  
    metaData2=content.substring(content.indexOf("metaData2")+10,content.indexOf("eventtype")-1);
    eventType=content.substring(content.indexOf("Eventtype")+10);
  }
  */
  
  public TransactionSubRecord(String id,String parentID, String eventType ,String metaData1,String metaData2)
  {
    this.id=id;
    this.parentID=parentID;
    this.metaData1=metaData1;
    this.metaData2=metaData2;
    this.eventType=eventType;
  }
//Amd001 [end]



  public String getEventType()
  {
    return eventType;
  }

  public void setEventType(String newEventType)
  {
    eventType = newEventType;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String newId)
  {
    id = newId;
  }

  public String getMetaData1()
  {
    return metaData1;
  }

  public void setMetaData1(String newMetaData1)
  {
    metaData1 = newMetaData1;
  }

  public String getMetaData2()
  {
    return metaData2;
  }

  public void setMetaData2(String newMetaData2)
  {
    metaData2 = newMetaData2;
  }

  public String getParentID()
  {
    return parentID;
  }

  public void setParentID(String newParentID)
  {
    parentID = newParentID;
  }
  
}