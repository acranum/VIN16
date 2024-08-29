package de.acranum.decrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeBinaryShift {
    public ArrayList binaryShift(ArrayList<String> in) {
        ArrayList<String> ShiftedBytes = new ArrayList<>();
        for (String packet : in) {
            String[] binarys = packet.split("\\s+");
            List<String> packs = Arrays.asList(binarys);
            for (int i = 0; i < 16; i++) {
                if (i == 2 || i == 5 || i == 7 || i == 12 || i == 13) {
                    StringBuilder sb = new StringBuilder(packs.get(i));
                    for (int ii = 0; ii < 1; ii++) {
                        char lastChar = sb.charAt(sb.length() - 1);
                        sb.deleteCharAt(sb.length() - 1);
                        sb.insert(0, lastChar);
                    }
                    packs.set(i, sb.toString());
                }
                if (i == 1 || i == 4 || i == 6 || i == 9 || i == 14) {
                    StringBuilder sb = new StringBuilder(packs.get(i));
                    for (int iii = 0; iii < 2; iii++) {
                        char lastChar = sb.charAt(sb.length() - 1);
                        sb.deleteCharAt(sb.length() - 1);
                        sb.insert(0, lastChar);
                    }
                    String shiftedBinary = sb.toString();
                    packs.set(i, shiftedBinary);
                }
                if (i == 0 || i == 3 || i == 8 || i == 10 || i == 11 || i == 15) {
                    StringBuilder sb = new StringBuilder(packs.get(i));
                    for (int iiii = 0; iiii < 3; iiii++) {
                        char lastChar = sb.charAt(sb.length() - 1);
                        sb.deleteCharAt(sb.length() - 1);
                        sb.insert(0, lastChar);
                    }
                    packs.set(i, sb.toString());
                }
            }
            ShiftedBytes.add(PackageBuilder(packs));
        }
        return ShiftedBytes;
    }
    private String shift(String binary, int shiftAmount) {
        StringBuilder sb = new StringBuilder(binary);
        for (int i = 0; i < shiftAmount; i++) {
            char lastChar = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(lastChar);
        }
        return sb.toString();
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
