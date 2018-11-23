package Templates;

import java.util.ArrayList;

import com.teqlip.Role.RoleEnum;

public enum TemplateEnum {
	Client_Profile{
		public String getString() {
	        return "ClientProfile";
	    }
	},
	Community_Connections{
		public String getString() {
	        return "Community Connections";
	    }
	},
	Employment{
		public String getString() {
	        return "Employment";
	    }
	},
	Info_And_Orien{
		public String getString() {
	        return "Info&Orien";
	    }
	},
	LT_Client_Enrol{
		public String getString() {
	        return "LT Client Enrol";
	    }
	},
	LT_Client_Exit{
		public String getString() {
	        return "LT Client Exit";
	    }
	},
	LT_Course_Setup{
		public String getString() {
	        return "LT Client Exit";
	    }
	},
	Needs_Assessment_And_Referrals{
		public String getString() {
	        return "NeedsAssessmentAndReferrals";
	    }
	};

    public static ArrayList<RoleEnum> enumIteration() {  
      RoleEnum[] roles = RoleEnum.values(); 
      ArrayList<RoleEnum> allRoleEnum = new ArrayList<RoleEnum>();
      for (RoleEnum role : roles) {
        allRoleEnum.add(role);  
      }
      return allRoleEnum;  
    }
	
	public abstract String getString();

}
