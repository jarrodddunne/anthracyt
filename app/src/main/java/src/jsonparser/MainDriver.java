
package src.jsonparser;
 
import java.io.*;
import java.util.Iterator;
import java.util.Scanner; 

import org.json.*;
 
public class MainDriver {
 
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in); 
        System.out.print("Top Text: "); 
        String top = in.nextLine();
        System.out.print("Bottom Text: ");
        String bottom = in.nextLine();
        
    	String jsonData = readFile("crayola.json");
        try {
            JSONArray jarr = new JSONArray(jsonData);
            for (int i = 0; i < jarr.length(); i++) {
                JSONObject jobj = jarr.getJSONObject(i);
                JSONObject jobjemote = jobj.getJSONObject("scores");
                //System.out.println(jobjemote);
                //	for(Iterator iterator = jobjemote.keySet().iterator(); iterator.hasNext();) {
                //	    String key = (String) iterator.next();
                //	    System.out.print(key + ": ");
                //	    System.out.println(jobjemote.get(key));
                //	}
                //System.out.println();
                writeFile(top, bottom, jobjemote);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
 
    }

    
    public static void writeFile(String topText, String btmText, JSONObject jobject){
        JSONObject memeBase = new JSONObject();
        try {
            memeBase.put("top", topText);
            memeBase.put("bottom", btmText);
            memeBase.put("emotions", jobject);
        } catch (Exception e) {
            e.printStackTrace();
        }
   // System.out.println(memeBase.toString());
    BufferedWriter buffWrite = null;
    
    try{
    	File file = new File("/Users/smahe/Documents/memeDatabase.txt");
    	if (!file.exists()) {
   	     file.createNewFile();
   	  }
    	FileWriter fw = new FileWriter(file, true);
    
    	buffWrite = new BufferedWriter(fw);
    	buffWrite.write(memeBase.toString() + ",");
    	System.out.println("File Written");
    	System.out.println("\nJSON Object:" + memeBase.toString());
    }catch (IOException ioe) {
 	   ioe.printStackTrace();
 	}
 	finally
 	{ 
 	   try{
 	      if(buffWrite!=null)
 		 buffWrite.close();
 	   }catch(Exception ex){
 	       System.out.println("Error in closing the BufferedWriter"+ex);
 	    }
 	}

    

    }
    
 
 
    public static String readFile(String filename) {
    	String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
 
    }
 
}