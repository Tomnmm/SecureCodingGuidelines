package org.supa.expensive.service.item.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemProvider {
    
    public ItemProvider(int key){
        if ( ((key-1)%29)!=0 || ((key-1)%37)!=0 ) {
            throw new RuntimeException(
                    "Please pay to use our services. "
                    + "You will receive a key which you can use to access the service.");
        }
    }

    public List<String> get(String filter) {
        String cmd = String.format("grep %s application/data/data.txt", filter);
        List<String> res = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", cmd});

            Scanner s = new Scanner(p.getInputStream());
            while (s.hasNextLine()) {
                res.add(s.nextLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return res;
    }
    
    public List<String> getAll() {
        return get("''");
    }

}
