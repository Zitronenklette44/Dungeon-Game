package game;

import java.util.HashMap;
import java.util.Map;

import action.Logger;
import questSystem.QuestManager;
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
        
        dialogs.put("log3", new String[]{
                Translation.get("dialogs.log3.part1"),
                Translation.get("dialogs.log3.part2"),
                Translation.get("dialogs.log3.part3"),
                Translation.get("dialogs.log3.part4")
            });
        
        dialogs.put("log4", new String[]{
                Translation.get("dialogs.log4.part1")
            });
        
        dialogs.put("log5", new String[]{
                Translation.get("dialogs.log5.part1")
            });
        dialogs.put("log6", new String[]{
                Translation.get("dialogs.log6.part1")
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
		case "log2": 
			QuestManager.setNewQuest(0);
			QuestManager.approveQuest();
			break;
			
		default:
			
		}
    	
    	
	}
    
    
}
