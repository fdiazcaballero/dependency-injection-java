/*
 * Copyright (C) 2016 fernando.caballero
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sage;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.*;

/**
 *
 * @author fernando.caballero
 * 
 1. A file consists of a sequence of ASCII characters.
               A sequence of one or more digits followed by a non-digit represents a decimal integer.
               The file includes no negative numbers.
               Write a program to
                               * add all the numbers in the file,
                               * count how many times each ASCII character occurs in the file,
                               * output the result to a file.
               You may assume that the sum of the numbers will not exceed the maximum value of an integer.
               A sample input file and a valid output file are attached.
 
2. Develop tests or advise a test strategy to adequately test the developed code.
 */
public class FileProcessingMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            String fileName;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the name of a file (the file must be in the Sage folder):");
            fileName = in.nextLine();
            fileReader = new FileReader(fileName);            
            FileProcessData fileProcessData=new FileProcessData();            
            FileHandler fh=new FileHandler(fileReader, fileProcessData);            
            FileProcessData fileProcessDataOutput=fh.processFile();;       
            fileReader.close();
            
            File file = new File("Output.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file); 
            HashMap<Character,Integer> hm=fileProcessDataOutput.getHashMap();
            writer.write("total="+fileProcessDataOutput.getSum()+"\r\n");
            
            for(Character key: hm.keySet()){
                writer.write("Character ["+key+"] occurs:"+hm.get(key)+"\r\n");
            }            
            writer.flush();
            writer.close();
            
            System.out.println("Enter the name of a file in the current directory:");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProcessingMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileProcessingMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(FileProcessingMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
