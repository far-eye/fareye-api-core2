package utils.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GenericMethods {
    private GenericMethods(){}

    public static String encryptToSHA256(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        byte[] hash = new byte[0];
        if (md != null)
            hash = md.digest(input.getBytes(StandardCharsets.UTF_8));

        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }


    @SneakyThrows
    public static void waitInSec(int sec) {
        Thread.sleep(sec* 1000L);
    }




    public static Map<String,String> getMultipartMap(String controlName, Object file,String fileName, String mimeType){
        HashMap<String,String> map = new HashMap<>();
        map.put("controlName",controlName);
        map.put("file",String.valueOf(file));
        map.put("fileName",fileName);
        map.put("mimeType",mimeType);
        return map;
    }

    public static String getRandomString(int limit){
        String generatedString = RandomStringUtils.random(limit,true,true);
        log.info("Generated random string: "+generatedString);
        return generatedString;
    }

    public static String getRandomString(int limit,boolean useLetters, boolean useNumbers){
        String generatedString = RandomStringUtils.random(limit,useLetters,useNumbers);
        log.info("Generated random string: "+generatedString);
        return generatedString;
    }

    public static int getRandomNumber(int limit){
        int generatedNumber = Integer.parseInt(RandomStringUtils.randomNumeric(8));
        log.info("Generated random number: "+generatedNumber);
        return generatedNumber;
    }

}
