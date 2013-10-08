package Model;

import java.util.HashMap;
import java.util.HashSet;

public class Assignment {
	
	public static HashSet<Assignment> allAssignments;
	private static int nextId = 1;
	

//	HashSet<Submission> submissions;
	HashMap<Student,Submission> submissions;
	int id;
	Unit unit;
	
	public Assignment(Unit u){
		id = nextId++;
		unit = u;
		submissions = new HashMap<Student,Submission>();
		
		if(allAssignments==null){
			allAssignments = new HashSet<Assignment>();
		}
		allAssignments.add(this);
	}

	public double getMeanGrade() {
		if (submissions.size() == 0)
			return 0.0;

		double total = 0.0;
		for (Submission s : submissions.values()) {
			total += s.getGrade();
		}
		return total / (double) submissions.size();
	}

	public int countSubmissions() {
		return submissions.size();
	}

	public double getMeanGrade(Student student) {
		return submissions.get(student).getGrade();
	}
	
	public int getId(){
		return id;
	}
	
	public Unit getUnit(){
		return unit;
	}

}
