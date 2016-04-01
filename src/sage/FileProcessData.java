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

import java.util.*;

/**
 *
 * @author fernando.caballero
 */
public class FileProcessData {
    private int sum;
    private HashMap<Character,Integer> hm;
    
    public FileProcessData(){
        sum=0;
        hm = new HashMap();        
    }
    
    public int getSum(){
        return sum;
    }
    
    public void setSum(int sum){
        this.sum=sum;
    }
    public void addition(int number){
        sum+=number;
    }
    
    public HashMap<Character,Integer> getHashMap(){
        return hm;
    }
    
    public void setHashMap(HashMap<Character,Integer> hm){
        this.hm=hm;
    }
    
    public void hashMapOperation(char c){        
        if(hm.containsKey(c)){
            hm.put(c, hm.get(c)+1);
        }
        else{
            hm.put(c,1);
        }
    }
    
}
