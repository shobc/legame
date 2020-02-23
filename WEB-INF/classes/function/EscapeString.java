package function;

public class EscapeString{
    public static String escape(String val) {
        if (val == null) return "";
        val = val.replaceAll("&", "&amp;");
        val = val.replaceAll("<", "&lt;");
        val = val.replaceAll(">", "&gt;");
        val = val.replaceAll("\"", "&quot;");
        val = val.replaceAll("'", "&apos;");
        val = val.replaceAll("&quot;n","\"n");
        return val;
    }
}