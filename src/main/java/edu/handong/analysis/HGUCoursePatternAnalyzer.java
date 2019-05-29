package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.cli.*;


import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	String dataPath; // csv file to be analyzed
	String resultPath; // the file path where the results are saved.
	String startYear;
	String endYear;
	int aType = 0;
	
	boolean cExist;
	boolean help;
	
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		Options options = createOptions();
		
		
		
		
		if(parseOptions(options, args)){
			
			if (help){
				printHelp(options);
				return;
			}
			
			
		}
		
		
		
		
//		try {
//			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
//			
//			if(args.length ==0)
//				throw new NotEnoughArgumentException();
//			else if(args.length<2)
//				throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
//			
//		} catch (NotEnoughArgumentException e) {
//			System.out.println(e.getMessage());
//			System.exit(0);
//		}
//		
//	
//		
		
		
		
		
		
		
		
		
		
		
		
		
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		
		
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
/////////////////////////////////////////////////////		
//		for(String key : sortedStudents.keySet()) {
//			System.out.println(key);
//		}  
/////// test ascending order working/////////////////
		
		
/////////////////Write file a 1 /////////////////////////////	
		if(aType == 1) {
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write file a 1
		Utils.writeAFile(linesToBeSaved, resultPath);
		
		}
		
		else if(aType == 2) {
			
			
			
		}
/////////////////////////////////////////////////////////////		
		
		
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
			
			Iterator<String> yearSet = sortedSeme.keySet().iterator();
			
			
			String allSeme = Integer.toString(sortedStudents.get(key).getSemestersByYearAndSemester().size());
			
			for(int pos = 1 ; pos <= sortedSeme.values().size() ; pos ++ ) {
				temp = key + "," + allSeme + "," + pos + "," + sortedStudents.get(key).getNumCourseInNthSemester(pos) ;
				
				
				String yearKey = yearSet.next().split("-")[0];
				
				
				//System.out.println("Student" + key + "'s yearKey = " + Integer.parseInt(yearKey));
				
				if(Integer.parseInt(yearKey) >= Integer.parseInt(startYear) && Integer.parseInt(yearKey) <= Integer.parseInt(endYear)) {
				cntCourses.add(temp);
				}
				
			
			}
		}
		

		return cntCourses; // do not forget to return a proper variable.
	}
	
	
	
	///////////////////////CLI AREA///////////////////////////////
	
	
	
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			dataPath = cmd.getOptionValue("i");
			resultPath = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			aType = Integer.parseInt(cmd.getOptionValue("a"));
			cExist = cmd.hasOption("c");
			startYear = cmd.getOptionValue("s");
			endYear = cmd.getOptionValue("e");
//			verbose = cmd.hasOption("v");
//			help = cmd.hasOption("h");
			
			
			try {
				// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
				
				if(dataPath == null)
					throw new NotEnoughArgumentException();
//				
//				else if(args.length<2)
//					throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
//				
				if(aType == 2 && !cExist) {
					throw new NotEnoughArgumentException("Please type course code !");
				}
			} catch (NotEnoughArgumentException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	
	
	
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());


		// add options by using OptionBuilder
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("Analysis option")
				.hasArg()
				.argName("Output path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2 option")
				.hasArg()
				.argName("course code")
				.build());
//		
		// add options by using OptionBuilder
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("set the end year for analysis e.g., -s 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
//
//		// add options by using OptionBuilder
//		options.addOption(Option.builder("h").longOpt("help")
//		        .desc("Help")
//		        .build());
		
				

		return options;
	}
	
	
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}

	
	
	
	
	
	
}



