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
package service;

import main.FileProcessingData;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author fernando.caballero
 */
public class FileProcessingService implements FileService{
    
    public FileProcessingService(){}

    @Override
    public FileProcessingData processFile(Reader inputStream){ 
        try {
            FileProcessingData fileProcessData=new FileProcessingData();
            String num;
            int charInt;
            char c; 
            boolean end=false;
            while (!end && (charInt = inputStream.read()) != -1){ 
                c=(char)charInt;
                fileProcessData.hashMapOperation(c); 
                num="";
                while (Character.isDigit(c)) {
                    num+=c;
                    if ((charInt = inputStream.read()) == -1){
                        end=true;
                        break;
                    }
                    else{
                        c=(char)charInt;                     
                        fileProcessData.hashMapOperation(c);
                    }
                } 
                if (!"".equals(num))
                    fileProcessData.addition(Integer.parseInt(num));
            }            
            File file = new File("Output.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            HashMap<Character,Integer> hm=fileProcessData.getHashMap();
            writer.write("total="+fileProcessData.getSum()+"\r\n");            
            for(Character key: hm.keySet()){
                writer.write("Character ["+key+"] occurs:"+hm.get(key)+"\r\n");
            }
            writer.flush();
            writer.close(); 
            
            return fileProcessData;
        }
        catch(IOException | NumberFormatException e){
            System.err.println("Caught Exception: " +  e.getMessage());
            return null;
        }    
    }


}



    

