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

import main.FileProcessData;
import java.io.Reader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author fernando.caballero
 */
public class FileHandler implements FileService{
    
    private Reader inputStream;
    private FileProcessData fileProcessData;

//    public FileHandler(Reader inputStream, FileProcessData fileProcessData){    
//        this.inputStream=inputStream;
//        this.fileProcessData=fileProcessData;
//    }
    
    public FileHandler(){    
        this.inputStream=null;
        this.fileProcessData=null;
    }

    @Override
    public FileProcessData processFile(Reader inputStream) throws IOException{ 
        try {
            FileProcessData fileProcessData=new FileProcessData();
            this.inputStream=inputStream;
            this.fileProcessData=fileProcessData;
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
                if (num !="")
                    fileProcessData.addition(Integer.parseInt(num));
            }
            return fileProcessData;
        }
        catch(IOException | NumberFormatException e){
            System.err.println("Caught Exception: " +  e.getMessage());
            return fileProcessData;
        }    
    }

//    public FileProcessData getFileProcessData(){
//        return fileProcessData;
//    }
//
//    public void setFileProcessData(FileProcessData fileProcessData){
//        this.fileProcessData=fileProcessData;
//    }
//
//    public Reader getInputStream(){
//        return inputStream;
//    }
//
//    public void setInputStream(Reader inputStream){
//        this.inputStream=inputStream;
//    }

}



    

