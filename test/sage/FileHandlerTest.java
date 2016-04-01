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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.StringReader;

/**
 *
 * @author fernando.caballero
 */
public class FileHandlerTest {
    
    public FileHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of processFile method, of class FileHandler.
     */
    @Test
    public void testProcessFile() throws Exception {
        System.out.println("processFile");
        String str="aasdfff8jdkjf9";
        StringReader reader=new StringReader(str);   
        FileProcessData fileProcessData=new FileProcessData();            
        FileHandler instance=new FileHandler(reader, fileProcessData);
        FileProcessData result = instance.processFile();
        assertEquals(17, result.getSum());
    }

//    /**
//     * Test of getFileProcessData method, of class FileHandler.
//     */
//    @Test
//    public void testGetFileProcessData() {
//        System.out.println("getFileProcessData");
//        FileHandler instance = null;
//        FileProcessData expResult = null;
//        FileProcessData result = instance.getFileProcessData();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFileProcessData method, of class FileHandler.
//     */
//    @Test
//    public void testSetFileProcessData() {
//        System.out.println("setFileProcessData");
//        FileProcessData fileProcessData = null;
//        FileHandler instance = null;
//        instance.setFileProcessData(fileProcessData);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getInputStream method, of class FileHandler.
//     */
//    @Test
//    public void testGetInputStream() {
//        System.out.println("getInputStream");
//        FileHandler instance = null;
//        Reader expResult = null;
//        Reader result = instance.getInputStream();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setInputStream method, of class FileHandler.
//     */
//    @Test
//    public void testSetInputStream() {
//        System.out.println("setInputStream");
//        Reader inputStream = null;
//        FileHandler instance = null;
//        instance.setInputStream(inputStream);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
