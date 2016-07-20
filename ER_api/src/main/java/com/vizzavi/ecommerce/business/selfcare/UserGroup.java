package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Table(name="er_usergroups")
@Entity
public class UserGroup implements Serializable {
	
	private static final long serialVersionUID = 2036678235756292341L;

	protected long id;
	
	protected String mName;
	
	protected String mDescription;

	
	int countryId;
	
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

	/**the primary key of the user group (USERGROUP_OBJ_ID)*/
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ugseq")
	@SequenceGenerator(name="ugseq", sequenceName="ER_USERGROUPS_SEQ", allocationSize=1)
	@Id
	@Column(name="USERGROUP_OBJ_ID")
	public long getId() {
		return id;
	}
	
	public void setId(long id) { this.id = id; }
	
	@Column(name="name")
	public String getName() { 
		if (isNotBlank(mName)) {
			return mName.toLowerCase();
		} else {
			return null;
		}
	}

	public void setName(String name) { this.mName = name; }

	@Column(name="description")
	public String getDescription() { return mDescription; }

	public void setDescription(String description) { this.mDescription = description; }
	
	@Column(name="country_obj_id")
	public int getCountryId() { return countryId; }
	
	public void setCountryId(int countryId) { this.countryId = countryId; }

	
	    
	//ET-129 
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{UserGroup\n");
		buf.append("mName=" + mName + "\n");
		buf.append("mDescription=" + mDescription + "\n");
		buf.append("}");
		return buf.toString();
	}
	
//	public String toString() {
//		
//		if(mName == null) {
//			return mName;
//		}
//		
//		return mName.trim();
//		
//	}
	
	
	//ET-86 : add user group editing to customer care starts here
	@Override
	public boolean equals(Object other)	{
		if (other == null) 
			return false;
		if (other == this) 
			  return true;
		if (other instanceof UserGroup){
			UserGroup usrGrp = (UserGroup)other;
    		return usrGrp.getName().equals(getName());
    	}
    	return false;
    }	
    
    @Override
	public int hashCode()	{
    	return new Long(getName()).hashCode();
    }
    //ET-86 : add user group editing to customer care ends here
}

