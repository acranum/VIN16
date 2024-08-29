package de.acranum.encrypt;

import de.acranum.encrypt.Bytes.encryptKey;
import de.acranum.encrypt.Bytes.getBytes;
import de.acranum.encrypt.Bytes.hex;
import de.acranum.encrypt.Bytes.packets;

import java.util.ArrayList;

public class encryptC {
    XOR xor = new XOR();
    ShiftRow shiftRow = new ShiftRow();
    MixFront mixFront = new MixFront();
    BinaryShift binaryShift = new BinaryShift();
    de.acranum.encrypt.Bytes.encryptKey encryptKey = new encryptKey();
    de.acranum.encrypt.Bytes.getBytes getBytes = new getBytes();
    de.acranum.encrypt.Bytes.hex hex = new hex();
    packets packet = new packets();

    public String encrypt(String txt, String key) {
        System.out.println("Key: " + key);
        //genKey
        ArrayList<String> keybin = encryptKey.encryptKey(key);
        //getBytes
        String binArr = getBytes.getByte(txt);
        //create Packets
        ArrayList<String> packets = packet.createBinaryPackets(binArr);
        //Key verkehrt
        ArrayList<String> reversedKey = encryptKey.reverseKey(keybin);
        //KeyXor
        ArrayList<String> XorKey = encryptKey.XorKey(keybin, reversedKey);

        //Runde 1
        ArrayList<String> Round1 = Runde1(packets, keybin);
        System.out.println("Runde1: " + Round1);
        //Runde 2
        ArrayList<String> Round2 = Runde2(Round1, keybin);
        System.out.println("Runde2: " + Round2);
        //Runde 3
        ArrayList<String> Round3 = Runde3(Round2, reversedKey);
        System.out.println("Runde3: " + Round3);
        //Runde 4
        ArrayList<String> Round4 = Runde4(Round3, XorKey);
        System.out.println("Runde4: " + Round4);
        //XOR + KeyVer
        ArrayList<String> xorVal = xor.XOR(Round4, reversedKey);

        //Base64 encode
        String hexVal = hex.binaryListToBase64(xorVal);
        System.out.println(hexVal);

        return hexVal;
    }

    public ArrayList<String> Runde1(ArrayList txt, ArrayList key) {
        //XOR
        ArrayList<String> xorVal = xor.XOR(txt, key);
        //Mix columns
        ArrayList<String> shift = shiftRow.shiftRow(xorVal);
        //MixFront
        ArrayList<String> mixed = mixFront.mixFront(shift);
        return mixed;
    }
    public ArrayList<String> Runde2(ArrayList txt, ArrayList key) {
        //XOR
        ArrayList<String> xorVal = xor.XOR(txt, key);
        //Binary shuffle
        ArrayList<String> binaryShiftVal = binaryShift.binaryShift(xorVal);
        //Mix Columns
        ArrayList<String> mixed = mixFront.mixFront(binaryShiftVal);
        return mixed;
    }
    public ArrayList<String> Runde3(ArrayList txt, ArrayList keyReversed) {
        //XOR
        ArrayList<String> xorVal = xor.XOR(txt, keyReversed);
        //Binary Shuffle
        ArrayList<String> binaryShiftVal = binaryShift.binaryShift(xorVal);
        //Mix Front
        ArrayList<String> mixed = mixFront.mixFront(binaryShiftVal);
        return mixed;
    }
    public ArrayList<String> Runde4(ArrayList txt, ArrayList keyXor) {
        //XOR
        ArrayList<String> xorVal = xor.XOR(txt, keyXor);
        //Mix Columns
        ArrayList<String> shift = shiftRow.shiftRow(xorVal);
        //Mix Front
        ArrayList<String> mixed = mixFront.mixFront(shift);
        return mixed;
    }

}
