package admin.function;

public class CutURL{
    private static final String path = "legame/admin/";
    public static String getPath(String url){
        return url.substring(url.indexOf(path)+path.length());
    }
}