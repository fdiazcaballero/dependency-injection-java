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

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.caballero
 */
public class FileProcessingService implements FileService{
    
    public FileProcessingService(){}
    
    /**
     * 
     * This method receives Reader object consisting of a sequence of ASCII characters.
     * 
     * It considers that a sequence of one or more digits followed by a non-digit represents a decimal integer.
     * 
     * It is assumed that there are no negative numbers in the stream.
     * 
     * It adds all the numbers in the stream, and write the total in a HashMap entry with key "total".
     * 
     * It counts how many times each ASCII character occurs in the stream, and include a HashMap entry
     * per character with key the corresponding character and value the number of appearances.
     * 
     * It writes the aforementioned HashMap into a file named "Output.txt".
     * 
     * @param inputStream Reader class
     * @return 
     */
    @Override
    public HashMap processFile(Reader inputStream){ 
        try {
            HashMap hm=new HashMap();
            hm.put("total",0);
            String num;
            int charInt;
            char c; 
            boolean end=false;
            while (!end && (charInt = inputStream.read()) != -1){ 
                c=(char)charInt;
                hm.put(c, hm.containsKey(c) ? (Integer)hm.get(c)+1 : 1);
                num="";
                while (Character.isDigit(c)) {
                    num+=c;
                    if ((charInt = inputStream.read()) == -1){
                        end=true;
                        break;
                    }
                    else{
                        c=(char)charInt;                     
                        hm.put(c, hm.containsKey(c) ? (Integer)hm.get(c)+1 : 1);
                    }
                } 
                if (!"".equals(num))
                    hm.put("total", (Integer) hm.get("total")+Integer.parseInt(num));
            }            
            File file = new File("Output.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("total="+hm.get("total")+"\r\n");            
            for(Object key: hm.keySet()){
                if(!key.equals("total"))
                    writer.write("Character ["+(Character)key+"] occurs:"+hm.get(key)+"\r\n");
            }
            writer.flush();
            writer.close(); 

            return hm;  
        }
        catch(IOException | NumberFormatException ex){
            Logger.getLogger(FileProcessingService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Caught Exception: " +  ex.getMessage());
            return null;
        }    
    }
}



    

