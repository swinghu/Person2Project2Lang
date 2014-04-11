package com.whu.swinghu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LoadData {
		/**
		 * Load data from the txt file 
		 * convert the file to ArrayList
		 */
		private static ArrayList<ArrayList<String>> person2pers ;	//new_edge
		private static ArrayList<ArrayList<String>> person2proj;	//position
		private static ArrayList<ArrayList<String>> proj2lang;		//project_pl		
		private String PATH_ROOT = "./sf1";
		
		public String getPATH_ROOT() {
			return PATH_ROOT;
		}

		public void setPATH_ROOT(String pATH_ROOT) {
			PATH_ROOT = pATH_ROOT;
		}

		public static ArrayList<ArrayList<String>> getPerson2pers() {
			return person2pers;
		}

		public static ArrayList<ArrayList<String>> getPerson2proj() {
			return person2proj;
		}

		public static ArrayList<ArrayList<String>> getProj2lang() {
			return proj2lang;
		}
		
		/* Input   : please set the prefix :2007-4(new edge file)
		 * Function:
		 * sf1/new_edge/2007-4_edge.txt		attach
		 * sf1/position/2007-2.txt			attach
		 * sf1/project_pl/2007-2_pl.txt	
		 */			
		public void readData(String prefix){
			
			String person2persfp = PATH_ROOT+"/new_edge/"+prefix+"_edge.txt";
			String person2projfp = PATH_ROOT+"/position/"+Util.matchString(prefix)+".txt";
			String project_plfp  = PATH_ROOT+"/project_pl/"+Util.matchString(prefix)+"_pl.txt";
			person2pers = Util.readFileByLines(person2persfp,"	");
			person2proj = Util.readFileByLines(person2projfp,"	");
			proj2lang 	= Util.readFileByLines(project_plfp,",");
					
		}
		
		
}
