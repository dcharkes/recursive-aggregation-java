package Model;

import java.util.HashSet;

public class Unit {
	
	private static int nextId = 1;
	public static HashSet<Unit> allUnits;

	private int id;
	Unit parentUnit;
	HashSet<Unit> childUnits;
	HashSet<Assignment> assignments;
	
	public Unit(){
		id = nextId++;
		childUnits = new HashSet<Unit>();
		assignments = new HashSet<Assignment>();
		
		if(allUnits==null){
			allUnits = new HashSet<Unit>();
		}
		allUnits.add(this);
	}

	public double getMeanGrade() {
		if (childUnits.size() > 0) {
			double total = 0.0;
			for (Unit s : childUnits) {
				total += s.getMeanGrade();
			}
			return total / (double) childUnits.size();
		} else if (assignments.size() > 0) {
			double total = 0.0;
			for (Assignment s : assignments) {
				total += s.getMeanGrade();
			}
			return total / (double) assignments.size();
		} else {
			return 0.0;
		}
	}
	
	public double getMeanGrade(Student student) {
		if (childUnits.size() > 0) {
			double total = 0.0;
			for (Unit s : childUnits) {
				total += s.getMeanGrade(student);
			}
			return total / (double) childUnits.size();
		} else if (assignments.size() > 0) {
			double total = 0.0;
			for (Assignment s : assignments) {
				total += s.getMeanGrade(student);
			}
			return total / (double) assignments.size();
		} else {
			return 0.0;
		}
	}
	
	public void addUnit(Unit child){
		child.parentUnit = this;
		childUnits.add(child);
	}
	
	public void addAssignment(Assignment a){
		assignments.add(a);
	}
	
	public int countAssignments(){
		if (childUnits.size() > 0) {
			int total = 0;
			for (Unit s : childUnits) {
				total += s.countAssignments();
			}
			return total;
		} else {
			return assignments.size();
		}
	}
	
	public int countSubmissions(){
		if (childUnits.size() > 0) {
			int total = 0;
			for (Unit s : childUnits) {
				total += s.countSubmissions();
			}
			return total;
		} else if (assignments.size() > 0) {
			int total = 0;
			for (Assignment s : assignments) {
				total += s.countSubmissions();
			}
			return total;
		} else {
			return 0;
		}
	}
	
	public Unit getParent(){
		return parentUnit;
	}
	
	public int getId(){
		return id;
	}
}
