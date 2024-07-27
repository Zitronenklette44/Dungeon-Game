package game;

import java.util.HashMap;
import java.util.Map;

import action.Logger;
import translation.Translation;

public class Dialogs {
    private static Map<String, String[]> dialogs = new HashMap<>();

    public static void init() {
        Logger.logInfo("Loading Dialogs...");
        dialogs.clear();
        dialogs.put("log1", new String[]{
            Translation.get("dialogs.log1.part1"),
            Translation.get("dialogs.log1.part2"),
            Translation.get("dialogs.log1.part3")
        });
        
        dialogs.put("log2", new String[]{
                Translation.get("dialogs.log2.part1"),
                Translation.get("dialogs.log2.part2"),
                Translation.get("dialogs.log2.part3"),
                Translation.get("dialogs.log2.part4")
            });
        
            Logger.logInfo("Finished loading Dialogs");
            
       
    }

    public static String[] get(String key) {
        String[] result = dialogs.get(key);
        if (result == null) {
            Logger.logError("Dialog for key " + key + " not found.");
        }
        return result;
    }

    public static void setQuest(String dialog) {
    	
    	switch (dialog) {
		case "log1": 
			Logger.logWarning("new Quest for dialog: log1");
			break;
			
		default:
			
		}
    	
    	
	}
    
    
}
