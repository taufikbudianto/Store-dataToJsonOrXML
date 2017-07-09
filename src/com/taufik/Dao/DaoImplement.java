package com.taufik.Dao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.taufik.Model.DataStored;
import com.taufik.Model.Filename;

public class DaoImplement implements Dao {

	@Override
	public void Register(DataStored data) {
		// TODO Auto-generated method stub
		
		System.out.println(data.getItemType());
		int type =data.getItemType();
		String name =data.getItemName();
		
		if(type==1){
		String fileName = "d:\\test\\"+name+".json";
    	File f = new File(fileName);
		JSONObject obj = new JSONObject();
		obj.put("itemName",data.getItemName());
		obj.put("itemContent", data.getItemContent());
		obj.put("itemType", data.getItemType());
		if(f.exists() && !f.isDirectory()) { 
    		System.out.println("Save Failed");
    	}else{
    	
    		try (FileWriter file = new FileWriter("d:\\test\\"+name+".json")) {	
    			file.write(obj.toJSONString());
    			file.flush();
    			System.out.println("Save Succes");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
		
		}else if(type==2){
			
			String fileName = "d:\\test\\"+name+".xml";
	    	File f = new File(fileName);
	    	if(f.exists() && !f.isDirectory()) { 
	    		System.out.println("Save Failed To XML");
	    	}else{
			 try {
					File file = new File("D:\\test\\"+name+".xml");
					JAXBContext jaxbContext = JAXBContext.newInstance(DataStored.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

					// output pretty printed
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

					jaxbMarshaller.marshal(data, file);
				//	jaxbMarshaller.marshal(data, System.out);
					System.out.println("Save Succes To XML");
				      } catch (JAXBException e) {
					e.printStackTrace();
				      }

				}
			}
		}
			
		

	@Override
	public String Retrieve(String itemName) {
		String a = "Test";
		File dir = new File("d:\\test");
		FilenameFilter filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.startsWith(itemName);
			}
			
		};
		 String[] children = dir.list(filter);
		 String namaFile = null;
		 ArrayList<String> list=new ArrayList<String>();
		 if (children == null) {
	         System.out.println("dir does not exist or is not a directory"); 
	      } else { 
	         for (int i=0; i< children.length; i++) {
	            String filename = children[i];
	            list.add(children[i]);
	            namaFile=filename; 
	         } 
	      }
		
	      int no=1;
	      System.out.println("------------View File------------");
	      for(String obj :list){
	    	  String dataparser="d:\\test\\"+obj;
				 no++;
				 final String FPATH = "d:\\test\\"+obj;
			      Filename myHomePage = new Filename(FPATH, '\\', '.');
			      String dataType = myHomePage.extension();
			      
			      switch(dataType){
			      case "xml":
			    	  System.out.println();
			    	  System.out.println("----------"+obj+"---------");
			    	  try {
			    		  
			    			File file = new File(dataparser);
			    			JAXBContext jaxbContext = JAXBContext.newInstance(DataStored.class);

			    			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			    			DataStored data = (DataStored) jaxbUnmarshaller.unmarshal(file);
			    			System.out.println("Item Name          ====>   "+data.getItemName());
				            System.out.println("Item Content       ====>   "+data.getItemContent());
				            System.out.println("Item Type          ====>   "+data.getItemType());

			    		  } catch (JAXBException e) {
			    			e.printStackTrace();
			    		  }

			    	  break;
			      case "json":
			    	  System.out.println();
			    	  System.out.println("---------"+obj+"---------");
			    	  
			    	  JSONParser parser = new JSONParser();
			          try {
			        	  
			              Object object = parser.parse(new FileReader(dataparser));
			              JSONObject jsonObject = (JSONObject) object;
			              String itmName= (String) jsonObject.get("itemName");
			              String Content= (String) jsonObject.get("itemContent");
			              long type = (Long) jsonObject.get("itemType");
			             // String type=  (String) jsonObject.get("itemType");
			              System.out.println("Item Name          ====>   "+itmName);
			              System.out.println("Item Content       ====>   "+Content);
			              System.out.println("Item Type          ====>   "+type);
//			              /System.out.println(jsonObject);
			          } catch (FileNotFoundException e) {
			              e.printStackTrace();
			          } catch (IOException e) {
			              e.printStackTrace();
			          } catch (ParseException e) {
			              e.printStackTrace();
			          }
			      break;
			      }
			 }

		return namaFile;
	}

	@Override
	public int GetType(String itemName) {
		// TODO Auto-generated method stub
		File dir = new File("d:\\test");
		System.out.println();
		System.out.println("------------Type File JSON/XML------------");
		 ArrayList<String> list=new ArrayList<String>();
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.startsWith(itemName);
			}
			
		};
		 String[] children = dir.list(filter);
		 String namaFile = null;
		 if (children == null) {
	         System.out.println("Either dir does not exist or is not a directory"); 
	      } else { 
	         for (int i=0; i< children.length; i++) {
	            String filename = children[i];
	            list.add(children[i]);
	            namaFile=filename;
	         } 
	      }
		 int contentType=0;
		 int no =1;
		 for(String obj :list){
			 System.out.println("No "+no+": "+obj);
			 no++;
			 final String FPATH = "d:\\test\\"+obj;
		      Filename myHomePage = new Filename(FPATH, '\\', '.');
		      String dataType = myHomePage.extension();
		      
		      switch(dataType){
		      case "xml":
		    	  contentType=2;
		    	  System.out.println("Content Type ==>2 || Format File XML");
		    	  break;
		      case "json":
		    	  contentType=1;
		    	  System.out.println("Content Type ==>1 || Format File json");
		      break;
		      }
		 }
	    
		return contentType;
	}

	@Override
	public void Deregister(String itemName) {
		System.out.println();
		System.out.println("------------Delete File------------");
		// TODO Auto-generated method stub
		File dir = new File("d:\\test");
		 ArrayList<String> list=new ArrayList<String>();
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.startsWith(itemName);
			}
			
		};
		 String[] children = dir.list(filter);
		 String namaFile = null;
		 if (children == null) {
	         System.out.println("Either dir does not exist or is not a directory"); 
	      } else { 
	         for (int i=0; i< children.length; i++) {
	            String filename = children[i];
	            list.add(children[i]);
	            namaFile=filename;
	         } 
	      }
		 int no =1;
		 for(String obj :list){
			 System.out.println("No "+no+": "+obj);
			 no++;
		 }
			try{
				Scanner sc = new Scanner(System.in);
				System.out.println();
				System.out.print("insert your number : ");
				int delete  = sc.nextInt();
				System.out.println(delete);
				//String[] stockArr = list.size();
				//System.out.println(list[1]);
				String[] stringArray = list.toArray(new String[0]);
				String del = stringArray[delete];
				System.out.println(stringArray[delete]);
	    		File file = new File("d:\\test\\"+del);

	    		if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}

	    	}catch(Exception e){

	    		e.printStackTrace();

	    	}

	}

}
