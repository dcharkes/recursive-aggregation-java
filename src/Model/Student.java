package Model;

import java.util.HashSet;

public class Student {

	public static HashSet<Student> allStudents;
	private static int nextId = 1;

	HashSet<Submission> submissions;
	int id;
	
	public Student(){
		submissions = new HashSet<Submission>();
		id = nextId++;
		
		if(allStudents==null){
			allStudents = new HashSet<Student>();
		}
		allStudents.add(this);
	}
	
	public int getId(){
		return id;
	}

}
