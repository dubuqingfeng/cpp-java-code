package com.hookheart.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			File outputFile = new File("/home/user/outp.txt");
			File inputKey = new File("/home/user/key.key");
			
			FileInputStream fiskey = new FileInputStream(inputKey);
			
			int[] key = new int[128];
			for(int i = 0; i < 128; i++){
				key[i] = fiskey.read();
			}
			
			FileInputStream fisf = new FileInputStream(outputFile);
			int sizeoutput = fisf.available();
			System.out.println(sizeoutput);
			for(int y = 0; y < sizeoutput; y++){
				System.out.print((char)(fisf.read() - key[y % 128]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
