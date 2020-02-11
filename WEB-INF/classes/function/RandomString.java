package function;

import org.apache.commons.lang.RandomStringUtils;

public class RandomString{
    public static String getString(){
        return RandomStringUtils.randomAlphanumeric(20);
    }
    public static String getString(int number){
        return RandomStringUtils.randomAlphanumeric(number);
    }
}