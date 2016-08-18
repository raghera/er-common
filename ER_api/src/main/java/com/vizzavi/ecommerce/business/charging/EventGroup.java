//package com.vizzavi.ecommerce.business.charging;
//import java.io.Serializable;
//import java.util.ArrayList;
//
///**
// *
// * This is the class representing the event group
// * 
// * @author Waleed Hassan
// *   
// */
//public class EventGroup implements Serializable 
//{
//
//  private static final long serialVersionUID = -7611435244557958886L;
//	  
//  // the event cap, and child event cap, the maximum number of usage 
//  int eventCap, childEventCap;
//  
//  EventGroup childEventGroup;
//  
//  
//  String  childEventMultiplier;
//  
//  ArrayList<Priority> priority;
//  
//  public void setEventCap(int eventCap)
//  {
//    this.eventCap=eventCap;
//  }
//  
//  public int getEventCap()
//  {
//    return eventCap;
//  }
//  
//  public void setChildEventCap(int childEventCap)
//  {
//    this.childEventCap=childEventCap;
//  }
//  
//  public int getChildEventCap()
//  {
//    return childEventCap;
//  }
//  
//  public void setChildeventMultiplier(String childEventMultiplier)
//  {
//    if ("multi".equalsIgnoreCase(childEventMultiplier)
//      || ("single".equalsIgnoreCase(childEventMultiplier))
//        || (childEventMultiplier == null))
//          this.childEventMultiplier=childEventMultiplier;
//  }
//  
//  public String getChildEventMultiplier()
//  {
//    return childEventMultiplier;
//  }
//  
//  public void setPriority(ArrayList<Priority> priority)
//  {
//    this.priority=(ArrayList<Priority>) priority.clone();
//  }
//  
//  public ArrayList<Priority> getPriority()
//  {
//    return priority;
//  }
//  
//  public EventGroup getChildEventGroup()
//  {
//    return childEventGroup;
//  }
//  
//  public EventGroup(int eventCap, int childEventCap, String childEventMultiplier, ArrayList<Priority> priority)
//  {
//    setEventCap(eventCap);
//    setChildEventCap(childEventCap);
//    setChildeventMultiplier(childEventMultiplier);
//    setPriority(priority);
//  }
//}
