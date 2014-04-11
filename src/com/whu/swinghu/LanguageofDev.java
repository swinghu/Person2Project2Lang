package com.whu.swinghu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LanguageofDev {
	private static String dirpath = "./match";
	private static String datafile = "2007-4";
	
	public static void main(String args[]){
		LoadData ldata =new LoadData();
		ldata.readData(datafile);
		ArrayList<ArrayList<String>> person2pers = ldata.getPerson2pers();
		ArrayList<ArrayList<String>> person2proj = ldata.getPerson2proj();
		ArrayList<ArrayList<String>> proj2lang = ldata.getProj2lang();
		
		ArrayList<ArrayList<String>> personlang =Util.getPersonList(person2pers);
		
		for(int i = 0; i<person2pers.size();i++){
			ArrayList<String> person2perLine = person2pers.get(i);
			String per1 = person2perLine.get(0).replaceAll("\"","");
			String per2 = person2perLine.get(1).replaceAll("\"","");
			
			String edgeattri = person2perLine.get(2);	//取得项目的属性O1O/O2O名字
			
			ArrayList<String> per1langList =new ArrayList<String>();//开发者1使用的语言
			ArrayList<String> per2langList =new ArrayList<String>();//开发者2使用的语言
			
			for(int j= 0; j<person2proj.size(); j++){	//在表person2proj中查找
				ArrayList<String> person2projLine = person2proj.get(j);
				String personname = person2projLine.get(0);
				if(per1.equals(personname)){
					String projName = person2projLine.get(1);
					for(int k =0 ; k<proj2lang.size(); k++){
						ArrayList<String> proj2langLine = proj2lang.get(k);
						if(projName.equals(proj2langLine.get(0))){
							//ArrayList<String> dest = new ArrayList<String>(src);
							//per1langList = proj2langLine;
							
							ArrayList<String>templangList = new ArrayList<String>(proj2langLine);							
							templangList.remove(0);
							
							per1langList = templangList;
							int perid =-1;
							for(int pl = 0; pl < personlang.size();pl++){
								if(personlang.get(pl).get(0).equals(personname))
									perid = pl;
							}							
							personlang.get(perid).addAll(per1langList);							
						}
					}
					
				}
				if(per2.equals(personname)){
					String projName = person2projLine.get(1);
					for(int k =0 ; k<proj2lang.size(); k++){
						ArrayList<String> proj2langLine = proj2lang.get(k);
						if(projName.equals(proj2langLine.get(0))){
						
							ArrayList<String>templangList = new ArrayList<String>(proj2langLine);							
							templangList.remove(0);
							
							per2langList = templangList;
							int perid = -1;
							for(int pl = 0;pl<personlang.size();pl++){
								if(personlang.get(pl).get(0).equals(personname))
									perid = pl;
							}						
							personlang.get(perid).addAll(per2langList);							
						}
					}
				}
			}		
			
		}
		
		for(int i = 0; i<personlang.size(); i++){
			Util.removeDuplicateWithOrder(personlang.get(i));					
		}
		String devlang = "";
		for(int i = 0; i<personlang.size(); i++){			
			for(int j = 0; j<personlang.get(i).size();j++){
				//System.out.print(personlang.get(i).get(j) +"   ");
				devlang += personlang.get(i).get(j)+"  ";
			}		
			devlang += "\n";
			System.out.println();
		}
		
		try {
			String filename = datafile+"_"+"langofdev.txt";
			Util.save2file(dirpath,filename,devlang);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



}
