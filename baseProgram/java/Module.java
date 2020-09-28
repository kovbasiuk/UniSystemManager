package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

public class Module {
//components of the module
	private  String moduleName;
	private  String code;
	private int credits;

	//module is the module name
	public Module(String module) {
	this.moduleName=module;

	}

	
	//all the needed getters and setters
	public String getModule() {
		return moduleName;
	}

	
	@Override
	public String toString() {
		return this.getCode()+": "+this.getModule();
	}
	
	public String getCode() {
		return code;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	

}
