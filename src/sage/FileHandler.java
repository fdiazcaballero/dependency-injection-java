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

import java.io.Reader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author fernando.caballero
 */
public class FileHandler {
    
protected Reader inputStream;
protected FileProcessData fileProcessData;

public FileHandler(Reader inputStream, FileProcessData fileProcessData){    
    this.inputStream=inputStream;
    this.fileProcessData=fileProcessData;  // only for option 2
}

//----------- option 1 -------------------//
public int getSumOfNumbers() throws IOException{ 
    int sum=0; 
    try {             
        String num="";
        int c; 
        boolean end=false;
        while (!end && (c = inputStream.read()) != -1){
            while (Character.isDigit(c)) {
                num+=(char)c;
                if ((c = inputStream.read()) == -1){
                    end=true;
                    break;
                }
                sum+=Integer.parseInt(num);
            }                
        }
        return sum;
    } 
    catch(IOException | NumberFormatException e){
        System.err.println("Caught Exception: " +  e.getMessage());
        return -1;
    }    
}

public void reset() throws IOException{
    inputStream.reset();
}

public HashMap getInstancesOfChars(){ 
    HashMap<Character,Integer> hm;
    hm = new HashMap();
        try {
            int c;
            while ((c = inputStream.read()) != -1){
                char character=(char)c;
                if(hm.containsKey(character)){
                    hm.put(character, hm.get(character)+1);
                }
                else{
                    hm.put(character,1);
                }
            }
            return hm;
        } 
        catch(IOException e){
            System.err.println("Caught Exception: " +  e.getMessage());
            return hm;
        }        
    }

//----------- option 2 -------------------//
public FileProcessData processFile() throws IOException{ 
    try {             
        String num="";
        int c; 
        boolean end=false;
        while (!end && (c = inputStream.read()) != -1){                       
            fileProcessData.hashMapOperation((char)c);            
            while (Character.isDigit(c)) {
                num+=(char)c;
                if ((c = inputStream.read()) == -1){
                    end=true;
                    break;
                }
                fileProcessData.addition(Integer.parseInt(num));
            }                
        }
        return fileProcessData;
    } 
    catch(IOException | NumberFormatException e){
        System.err.println("Caught Exception: " +  e.getMessage());
        return fileProcessData;
    }    
}

public FileProcessData getFileProcessData(){
    return fileProcessData;
}

public void setFileProcessData(FileProcessData fileProcessData){
    this.fileProcessData=fileProcessData;
}

public Reader getInputStream(){
    return inputStream;
}

public void setInputStream(Reader inputStream){
    this.inputStream=inputStream;
}

}



    

