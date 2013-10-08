import GenerateData.DataGenerator;
import Model.Student;
import Model.Submission;
import Model.Unit;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Started");

		Unit root = DataGenerator.generateDataStructure();
		System.out.println("Assignments: " + root.countAssignments());
		System.out.println("Submissions: " + root.countSubmissions());
		
		long start = System.currentTimeMillis();
		System.out.println("Mean grade:  " + root.getMeanGrade() 
				+ " " + (System.currentTimeMillis()-start)+"ms");
		
		Student s = (Student) Student.allStudents.toArray()[0];
		start = System.currentTimeMillis();
		System.out.println("Student "+s.getId()+" has mean grade: "+root.getMeanGrade(s)
				+ " " + (System.currentTimeMillis()-start)+"ms");
		
		start = System.currentTimeMillis();
		for(Student s1 : Student.allStudents){
			root.getMeanGrade(s1);
		}
		System.out.println("All student mean grades " + (System.currentTimeMillis()-start)+"ms");
		
		System.out.println("Total grade calculations: "+Submission.gradeCalculations);

	}

}
