package de.acranum.encrypt.Bytes;

import java.util.ArrayList;
import java.util.Collections;

public class encryptKey {
    public ArrayList encryptKey(String key) {
        ArrayList<String> binArr = new ArrayList<>();

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            String binVal = Integer.toBinaryString(ch);
            binVal = String.format("%8s", binVal).replace(' ', '0');
            binArr.add(binVal);
        }
        System.out.println(binArr);
        return binArr;
    }
    public ArrayList<String> reverseKey(ArrayList<String> key) {
        ArrayList<String> reversedList = new ArrayList<>(key);
        Collections.reverse(reversedList);
        return reversedList;
    }
    public ArrayList<String> XorKey(ArrayList<String> key, ArrayList<String> reversedKey) {
        ArrayList<String> XorKey = new ArrayList<>();
        for (String binary : key) {
            for (String binary2 : reversedKey) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 8; i++) {
                    int letter1 = binary.charAt(i);
                    int letter2 = binary2.charAt(i);
                    int Xorval = letter1 ^ letter2;
                    sb.append(Xorval);
                }
                String XorBin = sb.toString();
                XorKey.add(XorBin);
            }
        }
        return XorKey;

    }
}
