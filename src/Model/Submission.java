package Model;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;

public class Submission {

	public static int gradeCalculations = 0;
	public static HashSet<Submission> allSubmissions;
	private static int nextId = 1;
	
	String data;
	private int id;
	Student student;
	Assignment assignment;

	public Submission(String data, Student s, Assignment a) {
		this.data = data;
		id = nextId++;
		student = s;
		assignment = a;
		s.submissions.add(this);
		a.submissions.put(s, this);
		
		if(allSubmissions==null){
			allSubmissions = new HashSet<Submission>();
		}
		allSubmissions.add(this);
	}

	public double getGrade() {
		gradeCalculations++;

		return Double.parseDouble(data.substring(10))/10%10;
	}
	
	public int getId(){
		return id;
	}

	public Student getStudent() {
		return student;
	}
	
	public Assignment getAssignment(){
		return assignment;
	}

	public String getData() {
		return data;
	}

}
