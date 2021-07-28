package freeuni.edu.ge.Helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
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

    public String generateHash(String targ) {
        try {
            String s;
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(targ.getBytes());
            s = hexToString(md.digest());
            return s;
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }
}
