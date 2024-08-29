package de.acranum.decrypt.Bytes;

import java.util.ArrayList;

public class DePackage {
    public String dePackage(ArrayList<String> in) {
        StringBuilder sb = new StringBuilder();
        for (String packag : in) {
            sb.append(packag).append(" ");
        }
        return sb.toString();
    }
}
