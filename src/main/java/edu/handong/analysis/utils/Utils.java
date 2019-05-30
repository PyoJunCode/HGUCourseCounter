package edu.handong.analysis.utils;

import java.util.ArrayList;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import java.io.*;
import org.apache.commons.csv.*;


public class Utils {

	public static ArrayList<String> getLines(String file, boolean removeHeader){
		
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			File filetest = new File(file);
			if(!filetest.exists()) throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		try {
			
		
		
		Reader in = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		
		for(CSVRecord record : records) {
			String studentId = record.get(0);
			
			lines.add(studentId);
			
			
			//System.out.println(studentId);
			
		}
		
			
			
			}
		
		
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		 catch (IOException e) {
			 System.exit(0);
			 
		 }
		
		
		
		return lines;
	}
	
public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
		
		File wFile = new File("./"+targetFileName);
		wFile.getParentFile().mkdirs();
		//else if (!wFile.exists()) wFile.createNewFile();
		
		
		try {

			
		
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(wFile));
			wFile.createNewFile();
			
			CSVPrinter csvPrinter = new CSVPrinter(bw, CSVFormat.DEFAULT.withHeader("StudentID", "TotalNumberOfSemestersRegistered", "Semester", "NumCoursesTakenInTheSemester"));
			
			for(String line : lines) {
				String[] split = line.split(",");
				csvPrinter.printRecord(split[0],split[1],split[2],split[3]);
			}
			
			csvPrinter.flush();
			csvPrinter.close();

		}
		
		//"StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester"
		 catch (Exception e) {
			 System.out.println(e.getMessage());
			 System.exit(0);
			 
		 }
		
		
	}
	
	public static void writeA2File(ArrayList<String> lines, String targetFileName) {
		
		
		File wFile = new File("./"+targetFileName);
		wFile.getParentFile().mkdirs();
		//else if (!wFile.exists()) wFile.createNewFile();
		
		
		try {

			
		
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(wFile));
			wFile.createNewFile();
			
			CSVPrinter csvPrinter = new CSVPrinter(bw, CSVFormat.DEFAULT.withHeader("Year","Semester","CourseCode","CourseName","TotalStudents","StudentsTaken","Rate"));
			
			for(String line : lines) {
				String[] split = line.split(",");
				csvPrinter.printRecord(split[0],split[1],split[2],split[3],split[4],split[5],split[6]);
			}
			
			csvPrinter.flush();
			csvPrinter.close();

		}
		
		//"StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester"
		 catch (Exception e) {
			 System.out.println(e.getMessage());
			 System.exit(0);
			 
		 }
		
		
	}
}
