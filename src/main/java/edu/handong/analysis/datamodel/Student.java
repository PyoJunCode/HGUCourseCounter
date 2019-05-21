package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;


public class Student {

	private String studentId;
	private ArrayList<Course> coursesTaken;
	private HashMap<String, Integer> semestersByYearAndSemester;
	
	public Student(String studentId) {
		this.studentId = studentId;
		this.coursesTaken = new ArrayList<Course>();
		this.semestersByYearAndSemester = new HashMap<String, Integer>();
		
	}
	
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	
	public HashMap<String,Integer> getSemestersByYearAndSemester(){
		
		
		String temp;
		int seme = 1;
		
		for(Course courseCheck : coursesTaken) {
			
			
			temp =Integer.toString(courseCheck.getYearTaken()) + "-" + Integer.toString(courseCheck.getSemesterCourseTaken());
			
			
			if(semestersByYearAndSemester.containsKey(temp)) {
				continue;
			}
			else {
				semestersByYearAndSemester.put(temp, seme++);
			}
			
		}
		
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseInNthSemester(int semester) {
		
		int count = 0 ;
		String valCmp = null;
		String temp;
		
		for(String key : semestersByYearAndSemester.keySet()) {
			if(semestersByYearAndSemester.get(key) == semester) {
				valCmp = key;
			}
				
		}
		for(Course courseNumCheck : coursesTaken) {
			temp =Integer.toString(courseNumCheck.getYearTaken()) + "-" + Integer.toString(courseNumCheck.getSemesterCourseTaken());
			if(temp.equals(valCmp)) {
				count ++;
			}
		}
		
		return count;
	}
	

	
}
