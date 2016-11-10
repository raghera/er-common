package com.vodafone.global.er.subscriptionmanagement;

import com.vizzavi.ecommerce.business.selfcare.UserGroup;

public class ERUserGroup extends UserGroup implements java.io.Serializable, DirtyMarker
{
   private    static final long serialVersionUID = 3804492876716282437L;

   	
    private Long mAccount_obj_id;
    private String mChangeFlag;
	private boolean dirty = false;
	private boolean newInstance = false;

	public static final String NEW_USERGROUP_FLAG = "N";
	public static final String DELETE_USERGROUP_FLAG = "D";
	public static final String UNCHANGED_USERGROUP_FLAG = "U";

   public ERUserGroup() {
	   	mName = "";
	   	mDescription = "";
	   	mChangeFlag = "";
   }

   public ERUserGroup(String name, String description, Long account_obj_id, String changeFlag) {

        this.mName = name;
        this.mDescription = description;
		mAccount_obj_id = account_obj_id;
		mChangeFlag = changeFlag;
   }


   public ERUserGroup(String name, String description, Long account_obj_id, String changeFlag, boolean dirtyFlag, boolean newFlag) {

	   this.mName = name;
	   this.mDescription = description;
			mAccount_obj_id = account_obj_id;
			mChangeFlag = changeFlag;
			dirty=dirtyFlag;
			newInstance=newFlag;

   }
   
    public void setAccount_obj_id(Long account_obj_id) { mAccount_obj_id = account_obj_id; }


    public Long getAccount_obj_id() { return mAccount_obj_id; }


	public void setChangeFlag(String changeFlag) { mChangeFlag = changeFlag; }


    public String getChangeFlag() { return mChangeFlag; }

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setDirty()
	 */
	@Override
	public void setDirty() {
		this.dirty = true;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetDirty()
	 */
	@Override
	public void resetDirty() {
		dirty = false;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setNew()
	 */
	@Override
	public void setNew() {
		newInstance = true;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isNew()
	 */
	@Override
	public boolean isNew() {
		return newInstance;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetNew()
	 */
	@Override
	public void resetNew() {
		newInstance = false;
	}
}
