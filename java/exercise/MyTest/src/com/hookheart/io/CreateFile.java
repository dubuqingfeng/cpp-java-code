package com.hookheart.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("/home/user/key.key");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			for(int i = 0; i < 128; i++){
				fos.write((int)(Math.random() * 128));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
