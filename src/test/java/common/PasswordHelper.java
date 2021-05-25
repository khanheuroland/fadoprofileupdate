package common;

import java.util.Random;

public class PasswordHelper {
    private String arrChar = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String getPassword(int length)
    {
        String pass = "";
        Random rdn = new Random();
        while (pass.length()<length)
        {
            int nextChar = rdn.nextInt(arrChar.length());
            pass+=arrChar.charAt(nextChar);
        }
        return pass;
    }
}
