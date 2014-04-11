package com.whu.swinghu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Util {
	
	/**
	 * swinghu
	 * 获取Dev开发者列表
	 * @param person2pers
	 * @return ArrayList<ArrayList<String>>
	 */
	public static ArrayList<ArrayList<String>>  getPersonList(ArrayList<ArrayList<String>> person2pers ){
		ArrayList<ArrayList<String>> perAAList = new ArrayList<ArrayList<String>> ();
		
		for(int i = 0; i<person2pers.size(); i++){
			String per1 =  person2pers.get(i).get(0).replaceAll("\"","");
			String per2 =  person2pers.get(i).get(1).replaceAll("\"","");
			
			ArrayList<String> temp1ArrList = new ArrayList<String>();
			ArrayList<String> temp2ArrList = new ArrayList<String>();
			
			temp1ArrList.add(per1);
			temp2ArrList.add(per2);
			
			if(!perAAList.contains(temp1ArrList)){
				ArrayList perList =new ArrayList();
				perList.add(per1);
				perAAList.add(perList);				
			}
			
			if(!perAAList.contains(temp2ArrList)){
				ArrayList perList =new ArrayList();
				perList.add(per2);
				perAAList.add(perList);		
			}
		}
		return perAAList;
	}
	
	/**
	 * swinghu
	 * Time: 2014年3月31日
	 * Return_Type: boolean
	 * Function: 创建目录，以便保存文件到指定目录
	 * @param directory
	 * @return
	 */
	public static boolean createDirectory(String directory){
		boolean result = false;
		File file = new File(directory);
		if(!file.exists()){
			result = file.mkdirs();
		}		
		return result;
	}
	
	/**
	 * swinghu
	 * Time: 2014年3月31日
	 * Return_Type: boolean
	 * Function:保存文件到指定目录
	 * @param filename
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static boolean save2file(String dirpathname,String filename,String content) throws IOException{
		
		boolean result	 = false;
		Util.createDirectory(dirpathname);
		
		String filePath  = dirpathname +"/"+ filename;
		File file = new File(filePath);  
	    synchronized (file) {  
	        FileOutputStream fos = new FileOutputStream(filePath);  
	        fos.write(content.getBytes("GBK"));  
	        fos.close();  
	    }  
	    return true;
	}
		
	/**
	 * swinghu		
	 * Time: 2014年3月31日
	 * Return_Type: void
	 * Function:TODO ArrayList 删除重复元素
	 * @param arlList
	 */
	public static void removeDuplicateWithOrder(ArrayList arlList)
	 {
	 Set set = new HashSet();
	 List newList = new ArrayList();
	 for (Iterator iter = arlList.iterator();  iter.hasNext(); ) {
	 Object element = iter.next();
	   if (set.add(element))
	      newList.add(element);
	    }
	    arlList.clear();
	    arlList.addAll(newList);
	}
	/**
	 * Time: 2014年3月31日
	 * Return_Type: ArrayList<ArrayList<String>>
	 * Function:按行读取文件
	 * @param filePath
	 * @param sep
	 * @return
	 */
	public static ArrayList<ArrayList<String>> readFileByLines(String filePath,String sep){
		File file = new File(filePath);
		if(!file.exists() || !file.isFile()){
			return null;
		}
		ArrayList<ArrayList<String>> utilArrayList = new ArrayList<ArrayList<String>>();
		ArrayList<String> content = new ArrayList<String>();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String lineContent = "";
			while ((lineContent = reader.readLine()) != null) {
				//content.add(lineContent);
				String[] lineArr= lineContent.split(sep);					
				content = new ArrayList<String> (Arrays.asList(lineArr));
				utilArrayList.add(content);
				//System.out.println(lineContent);
			}				
			fileInputStream.close();
			inputStreamReader.close();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return utilArrayList;
	}
	
	/**
	 * Time: 2014年3月31日
	 * Return_Type: String
	 * Function: 字符串匹配
	 * @param str
	 * @return
	 */
	public static String matchString(String str){
		String chstr = "";
		ArrayList<String> dateList = new ArrayList<String>();			
		String[] utilArr =str.split("-");
		dateList = new ArrayList(Arrays.asList(utilArr));
		if(Integer.parseInt(dateList.get(1)) == 2){
			chstr = (Integer.parseInt(dateList.get(0))-1)+"-"+Integer.toString(12);
		}else{
			chstr = dateList.get(0)+"-"+(Integer.parseInt(dateList.get(1))-2);			
		}
		return chstr;		
	}
	/**
	 * swinghu
	 * Time: 2014年3月31日
	 * Return_Type: String
	 * Function:TODO 指定ArrayList的开始位置(indexBegin),获取后续元素，以String返回
	 * @param beginindex
	 * @param arrlist
	 * @return
	 */
	public static String getsubArrayList(int indexBegin,ArrayList<String> arrlist){
		
		String result = "";
		if(arrlist.size() == 1){
			return result;
		}
		
		for(int i = indexBegin; i<arrlist.size(); i++){				
			result  += arrlist.get(i)+",";		
		}			
		result = result.substring(0,result.length()-1);
		return result;
	}

	/**
	 * swinghu
	 * Time: 2014年3月31日
	 * Return_Type: int
	 * Function:根据第一个元素，对ArrayList<ArrayList<String>>进行索引
	 * @param arr2list
	 * @param str
	 * @return 索引号
	 */
	public static int getDoubleArrayListIndex(ArrayList<ArrayList<String>> arr2list,String str){
		int index = -1;
		str = str.replaceAll("\"", "");
		for(int i = 0; i<arr2list.size();i++){
			if(arr2list.get(i).get(0).equals(str)){
				return index = i;
			}
		}		
		return index;
		
	}
}
