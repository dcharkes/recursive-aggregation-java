package Export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import GenerateData.DataGenerator;
import Model.Assignment;
import Model.Student;
import Model.Submission;
import Model.Unit;

public class MysqlExport {

	static StringBuffer exportBuffer;

	public static void main(String[] args) {
		exportBuffer = new StringBuffer();

		DataGenerator.generateDataStructure();
		
		addStudents();
		addUnits();
		addAssignments();
		addSubmissions();
		
		try {
			writeToFile("data.sql", exportBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addStudents() {
		exportBuffer.append("INSERT INTO `student` (`id`) VALUES ");
		boolean first = true;
		for (Student s : Student.allStudents) {
			if (!first)
				exportBuffer.append(",");
			exportBuffer.append("(").append(s.getId()).append(")");
			first = false;
		}
		exportBuffer.append(";\n");
	}
	
	private static void addUnits(){
		exportBuffer.append("INSERT INTO `unit` (`id`, `parentId`) VALUES");
		boolean first = true;
		for (Unit u : Unit.allUnits) {
			if (!first)
				exportBuffer.append(",");
			Object parent = (u.getParent()==null)?"NULL":u.getParent().getId();
			exportBuffer.append("(").append(u.getId()).append(",").append(parent).append(")");
			first = false;
		}
		exportBuffer.append(";\n");
	}
	
	private static void addAssignments(){
		exportBuffer.append("INSERT INTO `assignment` (`id`, `unitId`) VALUES");
		boolean first = true;
		for (Assignment a : Assignment.allAssignments) {
			if (!first)
				exportBuffer.append(",");
			exportBuffer.append("(").append(a.getId()).append(",").append(a.getUnit().getId()).append(")");
			first = false;
		}
		exportBuffer.append(";\n");
	}
	
	private static void addSubmissions(){
		int bulkRows = 1000;
		String qStart = "INSERT INTO `submission` (`id`, `studentId`, `assignmentId`, `data`) VALUES";
		String qEnd = ";\n";
		
		exportBuffer.append(qStart);
		boolean first = true;
		int i = 0;
		for (Submission s : Submission.allSubmissions) {
			i++;
			if(i%bulkRows==0){
				exportBuffer.append(qEnd);
				exportBuffer.append(qStart);
				first = true;
			}
			if (!first)
				exportBuffer.append(",");
			exportBuffer.append("(").append(s.getId()).append(",").append(s.getStudent().getId()).append(",")
			.append(s.getAssignment().getId()).append(",\"").append(s.getData()).append("\")");
			first = false;
		}
		exportBuffer.append(qEnd);
	}

	public static void writeToFile(String pFilename, StringBuffer pData)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(pFilename));
		out.write(pData.toString());
		out.flush();
		out.close();
	}

}
