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

import injector.ServiceInjector;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.FileProcessingService;

/**
 *
 * @author fernando.caballero
 */
public class FileProcessingConsumerTest {
    
 private ServiceInjector injector;
    
    public FileProcessingConsumerTest() {
    }
    
    @Before
    public void setUp() {
        //mock the injector with anonymous class
        injector = new ServiceInjector() {            
            @Override
            public FileConsumer getConsumer() {
                //mock the message service
                return new FileProcessingConsumer(new FileProcessingService() {                     
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
                            return hm;  
                           }
                        catch(IOException | NumberFormatException e){
                            System.err.println("Caught Exception: " +  e.getMessage());
                            return null;
                        }    
                    }
                });
            }
        };
    }
    
    @After
    public void tearDown() {
        injector = null;
    }

    /**
     * Test of processFile method, of class FileProcessingService.
     */
    @Test
    public void testProcessFile_Addition_10() {
        System.out.println("processFile");
        String str="aasdfff1jdkjf9";        
        StringReader mock=new StringReader(str);  
        FileConsumer consumer = injector.getConsumer();
        HashMap result = consumer.processFile(mock);
        assertEquals(10, result.get("total"));
    }
    
}
