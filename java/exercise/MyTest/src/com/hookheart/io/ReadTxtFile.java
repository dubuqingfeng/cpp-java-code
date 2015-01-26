package com.hookheart.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadTxtFile {

	public static void main(String args[]){
		try {
			File inFile = new File("/home/rjxy-01-171/aaa.txt");
			File outFile = new File("/home/rjxy-01-171/bbb.txt");
		
			FileReader fr = new FileReader(inFile);
			BufferedReader br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(outFile);
			PrintWriter pw = new PrintWriter(fw);
			
			while(br.ready()){
				pw.println(br.readLine());
			}
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
