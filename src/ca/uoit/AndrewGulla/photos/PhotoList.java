package ca.uoit.AndrewGulla.photos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PhotoList{
	
	File file;
	List<String> images; 

	public PhotoList() {
		images = new ArrayList<String>();
		createFile();
		update(); 
	}

	private void update(){
		try{
			BufferedReader buffer = new BufferedReader(new FileReader (file)); 
			String image; 
			while ((image = buffer.readLine()) != null){
				images.add(image); 
			}
			buffer.close(); 
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	protected void add(String image){  
		try{
			BufferedWriter buffer = new BufferedWriter(new FileWriter(file, true)); 
			buffer.append(image);
			buffer.newLine();
			buffer.close();
			images.add(image); 
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	protected String getName(int index){
		return images.get(index);
	}

	protected String getTime(int index){
		String str = images.get(index);
		StringTokenizer st = new StringTokenizer(str,"_");
		st.nextToken();
		return st.nextToken();
	}

	protected String getLocation(int index){
		String str = images.get(index);
		StringTokenizer st = new StringTokenizer(str,"_");
		st.nextToken();
		st.nextToken();
		return st.nextToken();
	}

	protected List<String> getList(){
		return images;
	}

	private void createFile(){
		file = new File("sdcard/imageInfo.txt");
		if (!file.exists()){
			try{
				file.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

}

