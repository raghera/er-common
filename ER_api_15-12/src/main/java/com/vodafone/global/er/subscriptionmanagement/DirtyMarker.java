/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.vodafone.global.er.subscriptionmanagement;

/**
 * @author piotrad
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface DirtyMarker {

  public void setDirty();
  public boolean isDirty();
  public void resetDirty();
  public void setNew();
  public boolean isNew();
  public void resetNew();

}
