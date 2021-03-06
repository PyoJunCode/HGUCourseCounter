package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import org.apache.commons.csv.*;
import java.util.HashMap;

public class Course {
	
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	
//	public Course(String line) {
//		this.studentId = line.split(",")[0].trim();
//		this.yearMonthGraduated = line.split(",")[1].trim();
//		this.firstMajor = line.split(",")[2].trim();
//		this.secondMajor = line.split(",")[3].trim();
//		this.courseCode = line.split(",")[4].trim();
//		this.courseName = line.split(",")[5].trim();
//		this.courseCredit = line.split(",")[6].trim();
//		this.yearTaken = Integer.parseInt(line.split(",")[7].trim());
//		this.semesterCourseTaken = Integer.parseInt(line.split(",")[8].trim());
//		
//	}
	
	public Course(CSVRecord record) {
		this.studentId = record.get(0);
		this.yearMonthGraduated = record.get(1);
		this.firstMajor = record.get(2);
		this.secondMajor = record.get(3);
		this.courseCode = record.get(4);
		this.courseName = record.get(5);
		this.courseCredit = record.get(6);
		this.yearTaken = Integer.parseInt(record.get(7).trim());
		this.semesterCourseTaken = Integer.parseInt(record.get(8).trim());
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getYearMonthGraduated() {
		return yearMonthGraduated;
	}

	public void setYearMonthGraduated(String yearMonthGraduated) {
		this.yearMonthGraduated = yearMonthGraduated;
	}

	public String getFirstMajor() {
		return firstMajor;
	}

	public void setFirstMajor(String firstMajor) {
		this.firstMajor = firstMajor;
	}

	public String getSecondMajor() {
		return secondMajor;
	}

	public void setSecondMajor(String secondMajor) {
		this.secondMajor = secondMajor;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}

	public int getYearTaken() {
		return yearTaken;
	}

	public void setYearTaken(int yearTaken) {
		this.yearTaken = yearTaken;
	}

	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}

	public void setSemesterCourseTaken(int semesterCourseTaken) {
		this.semesterCourseTaken = semesterCourseTaken;
	}

}
