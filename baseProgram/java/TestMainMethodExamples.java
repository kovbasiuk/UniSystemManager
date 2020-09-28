package baseProgram.java;

/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestMainMethodExamples {

	public static void main (String[] args) throws FileNotFoundException {
		Calendar dateofbirth=Calendar.getInstance();
		dateofbirth.set(1992, 7, 2);
		Date d = dateofbirth.getTime();
		UniversityManagement a = new UniversityManagement();
		String [] firstNames= {"John", "Harry", "Ryan", "Nikolai", "Peter", "Frankie", "Suzy", "Sarah"};
		String [] lastNames= {"Appleby", "Livingston", "Smith", "Robinsons", "Grey"};
		Random r = new Random();
		a.inputSupervisors("src/listofsupervisors.txt");
		a.inputModules("src/listofmods.txt");
		int fn=r.nextInt(8);
		int ln = r.nextInt(5);
		
	
		Student someUnderGraduateStudent = new UnderGraduate(new Name(firstNames[fn], lastNames[ln]), d );
		
		
		a.registerModule(someUnderGraduateStudent, "CSC1021");//current credits=20
		a.registerModule(someUnderGraduateStudent, "CSC1022");//current credits=40
		a.registerModule(someUnderGraduateStudent, "CSC1023");//current credits=60
		a.registerModule(someUnderGraduateStudent, "CSC1024");//current credits=80
		a.registerModule(someUnderGraduateStudent, "CSC1027");//current credits=120
		
		a.registerStudent(someUnderGraduateStudent);
		System.out.println("Number of this type: " + a.noOfStudents(someUnderGraduateStudent));
		System.out.println("HERE1");
		System.out.println(someUnderGraduateStudent);//here1
		/*
		 * Now UnderGraduate's data will be amended, I will change his name to "Harry Walters"
		 * remove the module 2021( ProgrammingI) and replace it with CSC2026(Computer Networks)
		 * UnderGraduate should also have different initials and code on his smartcard
		 */
		a.amendStudentData(someUnderGraduateStudent, "Harry Walters", "CSC2026", "CSC1021");//here 2
		System.out.println("HERE2 RESULT:");
		System.out.println(someUnderGraduateStudent);
		
		//at HERE3 I will print the number of students of the same class as UnderGraduate using noOfStudents() 
		//which is from the University management class
		System.out.println("HERE3: "+"Number of this type of student: " +a.noOfStudents(someUnderGraduateStudent));//here3
		
	
		//Example of changing only the name of the student and leaving the modules the same
		a.amendStudentData(someUnderGraduateStudent, "Harry k", "n/a", "n/a"); //HERE4
		System.out.println("HERE4 RESULT: ");
		System.out.println(someUnderGraduateStudent);
		
		//Example of removing and adding a module but keeping the  name the same
				a.amendStudentData(someUnderGraduateStudent, "n/a", "CSC2025", "CSC1022"); //HERE5
				System.out.println("HERE5 RESULT: ");
				System.out.println(someUnderGraduateStudent);
				
				//Example of removing only one module
				a.amendStudentData(someUnderGraduateStudent, "n/a", "n/a", "CSC2025"); //HERE6
				System.out.println("HERE6 RESULT: ");
				System.out.println(someUnderGraduateStudent);
				
				
				//Example of removing only one module
				a.amendStudentData(someUnderGraduateStudent, "n/a", "CSC1029", "n/a"); //HERE7
				System.out.println("HERE7 RESULT: ");
				System.out.println(someUnderGraduateStudent);
				
				//Example of trying to add module which would result in more than max credits
				//Shouldnt add a module
				a.amendStudentData(someUnderGraduateStudent, "n/a", "CSC1028", "n/a"); //HERE8
				System.out.println("HERE8 RESULT: ");
				System.out.println(someUnderGraduateStudent);
				
				
				
		
		//I will now terminate someUnderGraduateStudent then print out the number of registered Student of his type
		a.terminateStudent(new StudentID("a0000"));//HERE9
		System.out.println("HERE9 RESULT:" + a.noOfStudents(someUnderGraduateStudent));
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		
		
		//HERE10
		
		System.out.println("HERE10:");
		
		Student ug = new UnderGraduate(new Name(firstNames[fn/2], lastNames[ln/2]), d );
		a.registerModule(ug, "CSC1021");//current credits=20
		a.registerModule(ug, "CSC1022");//current credits=40
		a.registerModule(ug, "CSC1023");//current credits=60
		a.registerModule(ug, "CSC1024");//current credits=80
		a.registerModule(ug, "CSC1027");//current credits=120
		a.registerStudent(ug);

		for(int i=0; i<4;i++) {
			int f=r.nextInt(8);
			int l = r.nextInt(5);
			
			Student pg = new PostGraduate(new Name(firstNames[f], lastNames[l]), d );
			
			
			a.registerModule(pg, "CSC1021");//current credits=20
			a.registerModule(pg, "CSC1022");//current credits=40
			a.registerModule(pg, "CSC1023");//current credits=60
			a.registerModule(pg, "CSC1024");//current credits=80
			a.registerModule(pg, "CSC1027");//current credits=120
			a.registerModule(pg, "CSC1028");//current credits=150
			a.registerModule(pg, "CSC1025");//current credits=170
			a.registerModule(pg, "CSC1029");//current credits=180
		
			a.registerStudent(pg);
			
			//printint out number of students of the 2 object I initialised 
			System.out.println("Type of student counter (pg): "+a.noOfStudents(pg));
			System.out.println("Type of student counter (ug): "+a.noOfStudents(ug));
		}
			
			
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		
		//HERE11 I'm going to create a PostGraduateResearch object and print out their supervisor's name as theyre automatically assigned one
		System.out.println("HERE11:");
		Student pgu = new PostGraduateResearch(new Name(firstNames[fn], lastNames[ln]), d );
		
		System.out.println(((PostGraduateResearch) pgu).getSupervisorName());
		//HERE12 Before I try to assign the module
		System.out.println("\nHERE 12"+pgu.toString());
		//HERE 13 Im going to add a module but it shouldnt register as PostGraduateResearch dont have modules
		a.registerModule(pgu, "CSC1028");
		System.out.println("\nHERE13 RESULT: "+pgu);
		}



}

