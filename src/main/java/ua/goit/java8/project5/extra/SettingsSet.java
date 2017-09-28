package ua.goit.java8.project5.extra;

/**
 * Created by t.oleksiv on 27/09/2017.
 */
public class SettingsSet {
    private boolean useCache;
    private boolean showTime;
    private String pathToCache;

    public void setUseCache(boolean useCache){
        this.useCache = useCache;
    }

    public void setShowTime(boolean showTime){
        this.showTime = showTime;
    }

    public void setPathToCache(String pathToCache){
        this.pathToCache = pathToCache;
    }

    public boolean getUseCache(){
        return useCache;
    }

    public boolean getShowTime(){
        return showTime;
    }

    public String getPathToCache(){
        return pathToCache;
    }
}
