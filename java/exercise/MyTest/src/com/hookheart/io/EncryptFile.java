package com.hookheart.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncryptFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File inputFile = new File("/home/user/test.txt");
			File outputFile = new File("/home/user/outp.txt");
			File inputKey = new File("/home/user/key.key");
			
			FileInputStream fiskey = new FileInputStream(inputKey);
			FileInputStream fis = new FileInputStream(inputFile);
			FileOutputStream fos = new FileOutputStream(outputFile);
			
			int[] key = new int[128];
			for(int i = 0; i < 128; i++){
				key[i] = fiskey.read();
			}
			
			int size = fis.available();
			for(int i = 0; i < size; i++){
				fos.write(fis.read() + key[i % 128]);
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
