package dcdmod.Manager;

import java.util.ArrayList;

/**
 * Created by Panthea on 2018/2/21.
 */

public class ModSaverFile {
    public ArrayList<String> Events = new ArrayList<>();
    public ModSaverFile(){
        Events = PlayerManager.getInstance().Events;
    }
}
