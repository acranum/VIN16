package de.acranum.encrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//      3 2 1 3
//      2 1 2 1
//      3 2 3 3
//      1 1 2 3

public class BinaryShift {
    public ArrayList binaryShift(ArrayList<String> in) {
        ArrayList<String> ShiftedBytes = new ArrayList<>();

        for (String packet : in) {
            String[] binarys = packet.split("\\s+");
            List<String> packs = Arrays.asList(binarys);
            for (int i = 0; i < 16; i++) {
                if (i == 2 || i == 5 || i == 7 || i == 12 || i == 13) {
                    String shifted = shift(packs.get(i), 1);
                    packs.set(i, shifted);
                } else if (i == 1 || i == 4 || i == 6 || i == 9 || i == 14) {
                    String shifted = shift(packs.get(i), 2);
                    packs.set(i, shifted);
                } else if (i == 0 || i == 3 || i == 8 || i == 10 || i == 11 || i == 15) {
                    String shifted = shift(packs.get(i), 3);
                    packs.set(i, shifted);
                }
            }
            ShiftedBytes.add(PackageBuilder(packs));
        }
        return ShiftedBytes;
    }
    private String shift(String in, int shiftAmount) {
        StringBuilder sb = new StringBuilder(in);
        for (int i = 0; i < shiftAmount; i++) {
            char firstChar = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(firstChar);
        }
        String shiftedBinary = sb.toString();
        return shiftedBinary;
    }
    private String PackageBuilder(List<String> in) {
        StringBuilder sb = new StringBuilder();
        for (String binary : in) {
            sb.append(binary + " ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

}
