package freeuni.edu.ge.Helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUsingSHA1 implements Hash{
    private String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;
            if (val < 16) buff.append('0');
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }


    @Override
    public String generateHash(String target) {
        try {
            String result;
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(target.getBytes());
            result = hexToString(md.digest());
            return result;
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }
}
