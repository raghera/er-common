/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.com.vodafone.global.er.subscriptionmanagement;

/**
 * Created by Ravi Aghera
 */
public interface DirtyMarker {

  public void setDirty();
  public boolean isDirty();
  public void resetDirty();
  public void setNew();
  public boolean isNew();
  public void resetNew();

}
