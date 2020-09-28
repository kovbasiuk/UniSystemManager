package baseProgram.java;

/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UniversityManagement {

	public static List<Supervisor> supervisors = new ArrayList<Supervisor>();
	public static Map<StudentID, Student> allStudents = new HashMap<StudentID, Student>();
	public static Map<String, Module> allModules = new HashMap<String, Module>();
	private String modFilePath;
	private String supFilePath;

	// returns the number of student of a specific chose student object using a for
	// each to check objects.class
	public int noOfStudents(Student type) {
		int counter = 0;
		for (Student s : allStudents.values()) {
			if (s.getClass() == (type.getClass())) {
				counter++;
			}
		}

		return counter;
	}

	// terminates the Student by using the ID to return the value in the
	// allStudents.
	// It also removes all the components including any ID objects, ID string
	// values, SmartCards and SmartCard string values;
	public void terminateStudent(StudentID id) {
		if (allStudents.containsKey(id)) {
			IDFactory.IDlist.remove(id);
			SmartCardNumberFactory.SmartCardStringList.remove((allStudents.get(id).getSmartCard().getSmartCardCode()));
			SmartCard.SmartCardList.remove(allStudents.get(id).getSmartCard());
			allStudents.remove(id);

		} else {
			throw new IllegalArgumentException("ID doesnt exist!");
		}
	}

	// registers student if validate students returns true;
	public void registerStudent(Student student) {

		if (validateStudent(student)) {
			allStudents.put(student.getID(), student);
		} else {
			new IllegalArgumentException("Student doesnt have enough credits!");
		}
	}

	/*
	 * Amends any of the data inputs except student which do not equal to "n/a" if
	 * name is changed, the student receives a new SmartCard to make sure the
	 * Initials are correct. The old SmartCard object from the type SmartCard will
	 * be removed from the list as no one is assigned to it anymore Also the
	 * SmartCard code will be removed from the String SmartCardStringList is removed
	 * for the same purpose
	 * 
	 * if the user chooses to add module, the mathod calls registerModule() which
	 * takes in the student and addModuleByCode which is the module thats going to
	 * be added.
	 * 
	 * if user fills the removeModuleByCode in with a module code the method will
	 * remove the module from the students individual module map and also updates
	 * the number of currentCredits by taking away the value of the removed modules
	 * credits from the currentCredits
	 */
	public void amendStudentData(Student student, String name, String addModuleByCode, String removeModuleByCode)
			throws FileNotFoundException {

		if (!name.equalsIgnoreCase("n/a")) {
			Name sName = Name.valueOf(name);
			student.setName(sName);
			SmartCardNumberFactory.SmartCardStringList.remove(student.getSmartCard().getSmartCardCode());
			SmartCard.SmartCardList.remove(student.getSmartCard());
			student.setSmartCard(new SmartCard((String) SmartCardNumberFactory.getNewInstance(student),
					student.getDob(), student.getName()));
		}

		if (!removeModuleByCode.equals("n/a")&&(allModules.containsKey(removeModuleByCode))) {
			if ((student.studentsModules.containsKey(removeModuleByCode))) {
				student.currentCredits -= student.studentsModules.get(removeModuleByCode).getCredits();
				student.studentsModules.remove(removeModuleByCode);
			}
		}
		else if(removeModuleByCode.equalsIgnoreCase("n/a")){
	
		}
			else {
		
			throw new IllegalArgumentException("Student isnt assigned this module or Module can't be found");
			}

		if ((!addModuleByCode.equals("n/a"))) {

			if (!student.studentsModules.containsKey("addModuleByCode"))
				if (!student.studentsModules.containsKey(removeModuleByCode)) {
					if (allModules.containsKey(addModuleByCode)) {
						registerModule(student, addModuleByCode);
					}
				}
		}
		// No exceptions because the registerModule() already throws an exceptions

	}

	// Stores the supervisors from a file using a file reader and scanner
	// name stores the fulle name of the supervisor and assigns it to the actual
	// supervisor name
	public void inputSupervisors(String fileName) throws FileNotFoundException {
		this.supFilePath = fileName;
		Scanner fl = new Scanner(new FileReader(fileName));
		while (fl.hasNext()) {
			String name = fl.nextLine();
			Supervisor supervisor = new Supervisor(Name.valueOf(name));
			supervisors.add(supervisor);

		}
	}

	/*
	 * I could have coded a function of setting all the fields in the Module class,
	 * however I thought it would be more practical defying what to do with the
	 * input in this method as it will be easier to adjust for different input
	 * styles.
	 * 
	 * Reads from a file using a scanner and file reader. Splits each component
	 * using a comma then assigns the part for its purpose
	 */
	public void inputModules(String fileName) throws FileNotFoundException {
		this.modFilePath = fileName;
		Scanner fl = new Scanner(new FileReader(fileName));
		while (fl.hasNext()) {
			String[] mod = fl.nextLine().split(",");

			Module module = new Module(mod[1].substring(1, mod[1].length()).trim());
			module.setCredits(Integer.parseInt(mod[2].trim()));
			module.setCode(mod[0]);
			allModules.put(module.getCode(), module);

		}
	}

	public String getModFilePath() {
		return modFilePath;
	}

	// registers module based on string input, (if the module exists in once the
	// module is added, the method increases the
	// currentCredits by the specific modules credits
	public void registerModule(Student student, String moduleCode) throws FileNotFoundException {

		if (allModules.containsKey(moduleCode)) {
			student.put(moduleCode, allModules.get(moduleCode));
		} else {
			throw new IllegalArgumentException("Module can't be found!");
		}
	}

	// checks if the student is assigned
	private boolean validateStudent(Student s) {
		return s.getCurrentCredits() == s.getMaxModCredits();
	}
}
