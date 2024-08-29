package de.acranum.encrypt.Bytes;

import java.util.ArrayList;

public class packets {
    public ArrayList<String> createBinaryPackets(String binaryString) {
        ArrayList<String> packets = new ArrayList<>();

        String[] binaryArray = binaryString.split("\\s+");

        StringBuilder packetBuilder = new StringBuilder();
        for (String binary : binaryArray) {
            packetBuilder.append(binary).append(" ");

            if (packetBuilder.toString().trim().split("\\s+").length == 16) {
                packets.add(packetBuilder.toString().trim());
                packetBuilder = new StringBuilder();
            }
        }

        if (packetBuilder.length() > 0) {
            String lastPacket = packetBuilder.toString().trim();
            int remainingChars = 16 - lastPacket.split("\\s+").length;
            lastPacket += " 00100000".repeat(remainingChars);
            packets.add(lastPacket);
        }

        return packets;
    }
}
