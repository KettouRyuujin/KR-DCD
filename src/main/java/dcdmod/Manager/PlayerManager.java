package dcdmod.Manager;

import java.util.ArrayList;

/**
 * Created by Panthea on 2018/2/21.
 */

public class PlayerManager {
    //region 单例
    private static final PlayerManager ourInstance = new PlayerManager();
    public static PlayerManager getInstance() {
        return ourInstance;
    }
    private PlayerManager() {
    }
//endregion

    public ArrayList<String> Events = new ArrayList<>();

    public void AddEvent(String event){
        Events.add(event);
    }

    public void RemoveEvent(String event){
        Events.remove(event);
    }
}
