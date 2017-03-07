package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Jonas
 */
public class Taal {
    
    private Locale loc;
    ResourceBundle bundle;
    //kan ook gedaan worden met een hashmap, met taal als key en code als value
    private static ArrayList<String> toegelatenTalen = new ArrayList<>(Arrays.asList("en", "fr", "nl"));
    
    
    
    public Taal(String taal){
        if(toegelatenTalen.contains(taal)){
            loc = new Locale(taal);
            bundle = ResourceBundle.getBundle("pazaak_resource", loc);
        }else{
            System.out.println("Verkeerde taal gekozen");
        }
    }
    
    public ResourceBundle getBundle(){
        return bundle;
    }
    
    public String getVertaling(String key){
        return bundle.getString(key);
    }
}
