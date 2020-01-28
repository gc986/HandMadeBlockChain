import com.google.common.hash.Hashing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

public class HashMaker {

    String makeSHA256(Object obj){
        return Hashing.sha256()
                .hashString(
                        bytesToHex(makeBytesFromObj(obj)),
                        StandardCharsets.UTF_8)
                .toString();
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private byte[] makeBytesFromObj(Object original){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        byte[] outArr = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(original);
            out.flush();
            outArr = bos.toByteArray();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return outArr;
    }

}
