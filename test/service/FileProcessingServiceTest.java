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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fernando.caballero
 */
public class FileProcessingServiceTest {
    
    private FileService instance;
    
    public FileProcessingServiceTest() {
    }
    
    @Before
    public void setUp() {
        //mock the message service
        instance = new FileProcessingService() {                     
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
        };        
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of processFile method, of class FileProcessingService.
     * @throws java.io.IOException
     */
    @Test
    public void testProcessFile_Addition_17() throws IOException {
        System.out.println("testProcessFile_Addition_17");
        String str="aasdfff8jdkjf9";        
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertEquals(17, result.get("total"));
    }
    
    @Test
    public void testProcessFile_SearchNotIncludedChar() throws IOException {
        System.out.println("testProcessFile_SearchNotIncludedChar");
        String str="aasdfff8jdkjf9";        
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertNull(result.get("z"));
        assertNull(result.get("/"));
    }
    
    @Test
    public void testProcessFile_EmptyFile_Addition_0() throws IOException {
        System.out.println("testProcessFile_EmptyFile_Addition_0");
        String str="";        
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertEquals(0, result.get("total"));
    }
    
    @Test
    public void testProcessFile_EmptyFile() throws IOException {
        System.out.println("testProcessFile_EmptyFile");
        String str="";        
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertEquals(false, result.containsKey(""));
        
        HashMap expected=new HashMap();
        expected.put("total", 0);
        assertEquals(expected, result);
    }
        
    @Test
    public void testProcessFile_Addition_0() throws IOException {
        System.out.println("testProcessFile_Addition_0");
        String str="asdvb):.,£";        
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertEquals(0, result.get("total"));
    }
    
    @Test
    public void testProcessFile_NonPrintCharacters_Pass() throws IOException {
        System.out.println("testProcessFile_NonPrintCharacters_Pass");
        char tab = (char) 9;
        char cr = (char) 13;
        String str = tab+"a/x"+cr+"9s"+tab+"dvb):.,£";
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertEquals(2, result.get((char) 9));
        assertEquals(1, result.get((char) 13));
        assertEquals(1, result.get('a'));
        assertEquals(1, result.get('/'));
    }
    
    @Test
    public void testProcessFile_OnlyNonPrintCharacters_Pass() throws IOException {
        System.out.println("testProcessFile_OnlyNonPrintCharacters_Pass");
        char tab = (char) 9;
        char cr = (char) 13;
        char stx = (char) 2;
        char bs = (char) 8;
        char lf = (char) 10;
        char esc = (char) 27;
        char del = (char) 127;
        char [] arr = {tab, tab, stx, tab, esc, del, del, del, del, lf, bs, cr};
        String str = new String(arr);
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertEquals(4, result.get((char) 127));
        assertEquals(1, result.get((char) 27));
        assertEquals(3, result.get((char) 9));
        assertEquals(1, result.get((char) 2));
        assertEquals(4, result.get((char) 127));
        assertEquals(1, result.get((char) 10));
        assertEquals(1, result.get((char) 8));
        assertEquals(1, result.get((char) 13));
    } 
    
    @Test
    public void testProcessFile_SampleInput_Pass() throws IOException {
        System.out.println("testProcessFile_SampleInput_Pass");
        String str = "7sadsa tesd@$=n453 323o0owsmsj 57 =oqqnd8 5sad873b ieu83bbi672ndnbshyb#sd4324hg";
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);

        HashMap expected=new HashMap();
        expected.put("total", 6805);
        expected.put('@', 1);
        expected.put('g', 1);
        expected.put('#', 1);
        expected.put(' ', 6);
        expected.put('d', 6);
        expected.put('e', 2);
        expected.put('b', 5);
        expected.put('$', 1);
        expected.put('a', 3);
        expected.put('n', 4);
        expected.put('o', 3);
        expected.put('m', 1);
        expected.put('j', 1);
        expected.put('h', 2);
        expected.put('i', 2);
        expected.put('3', 6);
        expected.put('w', 1);
        expected.put('2', 3);
        expected.put('u', 1);
        expected.put('t', 1);
        expected.put('0', 1);
        expected.put('s', 8);
        expected.put('7', 4);
        expected.put('6', 1);
        expected.put('5', 3);
        expected.put('q', 2);
        expected.put('4', 3);
        expected.put('8', 3);
        expected.put('y', 1);
        expected.put('=', 2);
        assertEquals(expected,result);
    }
    
    @Test
    public void testProcessFile_WhiteSpace_Pass() throws IOException {
        System.out.println("testProcessFile_WhiteSpace_Pass");
        String str = "7sad sat";
        StringReader mock=new StringReader(str);  
        HashMap result = instance.processFile(mock);
        assertEquals(1, result.get(' '));
    }
}
