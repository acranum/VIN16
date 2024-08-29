package de.acranum.encrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MixFront {
    public ArrayList mixFront(ArrayList<String> in) {
        ArrayList<List<String>> List = new ArrayList<>();
        ArrayList<String> List2 = new ArrayList<>();
        for (String pakage : in) {
            String[] binarys = pakage.split("\\s+");
            List<String> packs = Arrays.asList(binarys);
            for (int i = 0; i < 16; i++) {
                if (i == 0) {
                    String binary1 = packs.get(0);
                    String binary2 = packs.get(4);
                    String Vorne1 = getVorne(binary1);
                    String Vorne2 = getVorne(binary2);
                    String swappedString1 = Vorne2 + binary1.substring(4);
                    String swappedString2 = Vorne1 + binary2.substring(4);
                    packs.set(0, swappedString1);
                    packs.set(4, swappedString2);
                } else if (i == 11) {
                    String binary1 = packs.get(11);
                    String binary2 = packs.get(15);
                    String Vorne1 = getVorne(binary1);
                    String Vorne2 = getVorne(binary2);
                    String swappedString1 = Vorne2 + binary1.substring(4);
                    String swappedString2 = Vorne1 + binary2.substring(4);
                    packs.set(11, swappedString1);
                    packs.set(15, swappedString2);
                } else if (i == 2) {
                    String binary1 = packs.get(2);
                    String binary2 = packs.get(6);
                    String Hinten1 = getHinten(binary1);
                    String Hinten2 = getHinten(binary2);
                    String swappedString1 = binary1.substring(0, 4) + Hinten2;
                    String swappedString2 = binary2.substring(0, 4) + Hinten1;
                    packs.set(2, swappedString1);
                    packs.set(6, swappedString2);
                } else if (i == 9) {
                    String binary1 = packs.get(9);
                    String binary2 = packs.get(13);
                    String Hinten1 = getHinten(binary1);
                    String Hinten2 = getHinten(binary2);
                    String swappedString1 = binary1.substring(0, 4) + Hinten2;
                    String swappedString2 = binary2.substring(0, 4) + Hinten1;
                    packs.set(9, swappedString1);
                    packs.set(13, swappedString2);
                } else if (i == 5) {
                    String binary1 = packs.get(5);
                    String binary2 = packs.get(10);
                    String Vorne1 = getVorne(binary1);
                    String Vorne2 = getVorne(binary2);
                    String swappedString1 = Vorne2 + binary1.substring(4);
                    String swappedString2 = Vorne1 + binary2.substring(4);
                    packs.set(5, swappedString1);
                    packs.set(10, swappedString2);
                } else if (i == 7) {
                    String binary1 = packs.get(7);
                    String binary2 = packs.get(8);
                    String Hinten1 = getHinten(binary1);
                    String Hinten2 = getHinten(binary2);
                    String swappedString1 = binary1.substring(0, 4) + Hinten2;
                    String swappedString2 = binary2.substring(0, 4) + Hinten1;
                    packs.set(7, swappedString1);
                    packs.set(8, swappedString2);
                }
            }
            List2.add(PackageBuilder(packs));
        }
        return List2;
    }

    private String getVorne(String binary) {
        return binary.substring(0, 4);
    }
    private String getHinten(String binary) {
        return binary.substring(4,8);
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
