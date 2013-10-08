package GenerateData;

import Model.Assignment;
import Model.Student;
import Model.Submission;
import Model.Unit;

public class DataGenerator {

	public static Unit generateDataStructure() {
		Unit root = new Unit();

		for (int a = 0; a < 1000; a++) {
			new Student();
		}

		for (int a = 0; a < 4; a++) {
			Unit level1 = new Unit();
			root.addUnit(level1);
			for (int b = 0; b < 5; b++) {
				Unit level2 = new Unit();
				level1.addUnit(level2);
				for (int c = 0; c < 5; c++) {
					Assignment x = new Assignment(level2);
					level2.addAssignment(x);
					for (Student s : Student.allStudents) {
						new Submission("submission" + (level1.getId()+level2.getId()+x.getId()^2+s.getId()*37), s, x);
					}
				}
			}
		}

		return root;
	}

}
