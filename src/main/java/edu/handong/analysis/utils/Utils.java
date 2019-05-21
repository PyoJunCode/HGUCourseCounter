package edu.handong.analysis.utils;

import java.util.ArrayList;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import java.io.*;
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
			
		String line = null;
		//File rFile = new File(file);
		
		
		FileReader fileReader = new FileReader(file);
		
		BufferedReader rLine = null ;
		
		
			rLine = new BufferedReader(fileReader);
			
			if(removeHeader) {
			rLine.readLine(); //skip first line if true.
			}
			
			while((line = rLine.readLine()) != null) {
				
				lines.add(line);
				
			
			
				}
			
			rLine.close();
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
			
			
			bw.write("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
			bw.newLine();
			for(String line : lines) {
			
			bw.write(line);
			bw.newLine();
			}
			
			bw.close();
		}
		
		
		 catch (Exception e) {
			 System.out.println(e.getMessage());
			 System.exit(0);
			 
		 }
		
		
	}
}
