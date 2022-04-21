package Connect;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil { private static final Properties PROPERTIES = new Properties();
    private PropertiesUtil(){
    }
    static {
        loadproperties();
    }
    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }
    private static void loadproperties() {
        try(var intputstream=  PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(intputstream);
        } catch (IOException e) {
            throw new RuntimeException(e);      }
    }
}
