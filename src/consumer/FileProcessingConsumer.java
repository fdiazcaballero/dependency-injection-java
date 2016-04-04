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

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.FileService;

/**
 * 
 * Implementation of the FileConsumer interface
 * 
 * @author fernando.caballero
 */
public class FileProcessingConsumer implements FileConsumer{
    
    private FileService service;
    
    /**
     * Constructor for FileProcessingConsumer class.
     * 
     * The injection of the service is performed here.
     * 
     * @param service FileService object that is injected. 
     */
    public FileProcessingConsumer(FileService service){
        this.service=service; //injection
    }    
    
    /**
     * 
     * @param inputStream class Reader
     * @return HashMap containing the result of the process operation.
     */
    @Override
    public HashMap processFile(Reader inputStream) {                
        try {
            return this.service.processFile(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(FileProcessingConsumer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Caught Exception: " +  ex.getMessage());
            return null;
        }
    }    
}
