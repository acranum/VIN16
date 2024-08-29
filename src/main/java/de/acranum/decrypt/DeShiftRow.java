package de.acranum.decrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeShiftRow {
    public ArrayList shiftRow(ArrayList<String> in) {
        ArrayList<String> shiftedBytes = new ArrayList<>();
        for (String pakage : in) {
            ArrayList<List<String>> shiftedPackage = new ArrayList<>();
            String[] binarys = pakage.split("\\s+");
            List<String> packs = Arrays.asList(binarys);
            for (int i = 0; i < 16; i++) {
                if (i == 3) {
                    List<String> subList = packs.subList(0, 4);

                    String last = subList.get(0);
                    for (int ii = 0; ii < subList.size() - 1; ii++) {
                        String current = subList.get(ii + 1);
                        subList.set(ii, current);
                    }
                    subList.set(subList.size() - 1, last);
                    shiftedPackage.add(subList);
                } else if (i == 7) {
                    List<String> subList = packs.subList(4, 8);

                    String last = subList.get(0);
                    for (int ii = 0; ii < subList.size() - 1; ii++) {
                        String current = subList.get(ii + 1);
                        subList.set(ii, current);
                    }
                    subList.set(subList.size() - 1, last);
                    shiftedPackage.add(subList);
                } else if (i == 15) {
                    List<String> subList = packs.subList(8, 16);

                    String last = subList.get(0);
                    for (int ii = 0; ii < subList.size() - 1; ii++) {
                        String current = subList.get(ii + 1);
                        subList.set(ii, current);
                    }
                    subList.set(subList.size() - 1, last);
                    shiftedPackage.add(subList);
                }
            }
            shiftedBytes.add(PackageBuilder(shiftedPackage));
        }

        return shiftedBytes;
    }

    private String PackageBuilder(ArrayList<List<String>> in) {
        StringBuilder sb = new StringBuilder();
        for (List<String> innerList : in) {
            for (String Binary : innerList) {
                sb.append(Binary + " ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
