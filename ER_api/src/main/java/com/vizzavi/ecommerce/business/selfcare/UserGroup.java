package com.vizzavi.ecommerce.business.selfcare;


public class UserGroup implements java.io.Serializable {
	private static final long serialVersionUID = 2036678235756292341L;


	protected String mName;
	protected String mDescription;

	public UserGroup() {
		mName = "";
		mDescription = "";
	}

	public UserGroup(String name, String description) {
		mName = name;
		mDescription = description;
	}

	public UserGroup(UserGroup uGroup) {
		mName = uGroup.getName();
		mDescription = uGroup.getDescription();
	}

	public String getName() { return mName; }

	public String getDescription() { return mDescription; }

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{UserGroup\n");
		buf.append("mName=" + mName + "\n");
		buf.append("mDescription=" + mDescription + "\n");
		buf.append("}");
		return buf.toString();
	}
}

