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
package consumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.FileProcessData;
import service.FileService;

/**
 *
 * @author fernando.caballero
 */
public class OutputConsumerApp implements FileConsumer{
    
    private FileService service;
    
    public OutputConsumerApp(FileService service){
        this.service=service;
    }    
    
    /**
     * 
     * @param inputStream
     * @param fileProcessData
     */
    @Override
    public void writeOutputFile(Reader inputStream, FileProcessData fileProcessData) {
        try {          
            FileProcessData result=this.service.processFile(inputStream,fileProcessData);
            File file = new File("Output.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            HashMap<Character,Integer> hm=result.getHashMap();
            writer.write("total="+result.getSum()+"\r\n");
            
            for(Character key: hm.keySet()){
                writer.write("Character ["+key+"] occurs:"+hm.get(key)+"\r\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(OutputConsumerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
