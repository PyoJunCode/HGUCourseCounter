package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			
			if(args.length ==0)
				throw new NotEnoughArgumentException();
			else if(args.length<2)
				throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
			
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
/////////////////////////////////////////////////////		
//		for(String key : sortedStudents.keySet()) {
//			System.out.println(key);
//		}  
/////// test ascending order working/////////////////
		
		
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
//		
		int lineSize = lines.size();
		
		HashMap<String,Student> loadStudent = new HashMap<String, Student>();
		
		for(int pos = 0 ; pos < lineSize ; pos ++) {
			
			Student addStu ;
			String stid = lines.get(pos).split(",")[0].trim();
			
			if(loadStudent.containsKey(stid)) {
				addStu = loadStudent.get(stid);
				
			}
			
			else { 
				addStu = new Student(stid);
				
				loadStudent.put(stid,addStu);
			}
	
			addStu.addCourse(new Course(lines.get(pos)));
			
		}
		
//		for(String key : loadStudent.keySet()) {
//			System.out.println(key);
//		}
		
		
		return loadStudent; // do not forget to return a proper variable.
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		ArrayList<String> cntCourses = new ArrayList<String>();
		
		String temp = null;
		int size = sortedStudents.size();
		
		
		
		Iterator<String> keySet = sortedStudents.keySet().iterator();
		
		while(keySet.hasNext()) {
			
			String key = keySet.next();
			
			Map<String, Integer> sortedSeme = new TreeMap<String,Integer>(sortedStudents.get(key).getSemestersByYearAndSemester()); 
			
			String allSeme = Integer.toString(sortedStudents.get(key).getSemestersByYearAndSemester().size());
			
			for(int pos = 1 ; pos <= sortedSeme.values().size() ; pos ++ ) {
				temp = key + "," + allSeme + "," + pos + "," + sortedStudents.get(key).getNumCourseInNthSemester(pos) ;
				
				cntCourses.add(temp);
				
			
			}
		}
		

		return cntCourses; // do not forget to return a proper variable.
	}
}
