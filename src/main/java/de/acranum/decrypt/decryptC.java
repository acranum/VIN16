package de.acranum.decrypt;

import de.acranum.decrypt.Bytes.DePackage;
import de.acranum.encrypt.Bytes.encryptKey;
import de.acranum.encrypt.MixFront;
import de.acranum.encrypt.XOR;
import de.acranum.decrypt.Bytes.baseToBinary;
import de.acranum.encrypt.Bytes.getBytes;
import de.acranum.encrypt.Bytes.packets;

import java.util.ArrayList;

public class decryptC {
    packets packets = new packets();
    MixFront mixFront = new MixFront();
    XOR xor = new XOR();
    de.acranum.encrypt.Bytes.encryptKey encryptKey = new encryptKey();
    DeShiftRow deShiftRow = new DeShiftRow();
    DeBinaryShift deBinaryShift = new DeBinaryShift();
    getBytes getBytes = new getBytes();
    DePackage dePackage = new DePackage();

    public String decrypt(String txt, String key) {
        //gen Key
        ArrayList<String> keybin = encryptKey.encryptKey(key);
        //key reversed
        ArrayList<String> keyReversed = encryptKey.reverseKey(keybin);
        //keyXor
        ArrayList<String> keyXor = encryptKey.XorKey(keybin, keyReversed);
        //Base64 >> Binary
        baseToBinary baseToBinary = new baseToBinary();
        ArrayList<String> base = baseToBinary.btb(txt);
        //packets
        String Binary = String.join(" ", base);
        ArrayList<String> pacets = packets.createBinaryPackets(Binary);
        //XOR
        ArrayList<String> xorVal = xor.XOR(pacets, keyReversed);


        //Round1
        ArrayList<String> Round1 = Runde1(xorVal, keyXor);
        System.out.println("Runde1: " + Round1);
        //Round2
        ArrayList<String> Round2 = Runde2(Round1, keyReversed);
        System.out.println("Runde2: " + Round2);
        //Round3
        ArrayList<String> Round3 = Runde3(Round2, keybin);
        System.out.println("Runde3: " + Round3);
        //Round4
        ArrayList<String> Round4 = Runde4(Round3, keybin);
        System.out.println("Runde4: " + Round4);

        String end_bin = dePackage.dePackage(Round4);

        String Output = getBytes.getString(end_bin);
        System.out.println(Output);

        return Output;
    }

    public ArrayList<String> Runde1(ArrayList txt, ArrayList keyXor) {
        //Mix Front
        ArrayList<String> mixed = mixFront.mixFront(txt);
        //Mix Columns
        ArrayList<String> deshift = deShiftRow.shiftRow(mixed);
        //XOR
        ArrayList<String> xorVal = xor.XOR(deshift, keyXor);
        return xorVal;
    }
    public ArrayList<String> Runde2(ArrayList txt, ArrayList keyReversed) {
        //Mix Front
        ArrayList<String> mixed = mixFront.mixFront(txt);
        //Mix Binarys
        ArrayList<String> deshift = deBinaryShift.binaryShift(mixed);
        //XOR
        ArrayList<String> xorVal = xor.XOR(deshift, keyReversed);
        return xorVal;
    }
    public ArrayList<String> Runde3(ArrayList txt, ArrayList keyXor) {
        //Mix Front
        ArrayList<String> mixed = mixFront.mixFront(txt);
        //Mix Columns
        ArrayList<String> deshift = deBinaryShift.binaryShift(mixed);
        //XOR
        ArrayList<String> xorVal = xor.XOR(deshift, keyXor);
        return xorVal;
    }
    public ArrayList<String> Runde4(ArrayList txt, ArrayList keyXor) {
        //Mix Front
        ArrayList<String> mixed = mixFront.mixFront(txt);
        //Mix Columns
        ArrayList<String> deshift = deShiftRow.shiftRow(mixed);
        //XOR
        ArrayList<String> xorVal = xor.XOR(deshift, keyXor);
        return xorVal;
    }
}
