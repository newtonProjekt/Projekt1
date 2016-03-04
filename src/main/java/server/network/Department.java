/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;

public class Department
{
    
    private String deptName;
    
    
   public Department(String deptName)
   {
      this.deptName = deptName;
   }
 
   
 
   public String getDeptName()
   {
      return deptName;
   }
 
   public void setDeptName(String deptName)
   {
      this.deptName = deptName;
   }
    
   @Override
   public String toString()
   {
      return "Department [deptName="+deptName+"]";
   }
}



    

