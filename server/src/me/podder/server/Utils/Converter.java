package me.podder.server.Utils;

public class Converter {
    private Converter() {}

    public static byte[] StringArrayToByteArray(String[] strings) {
        int i = 0;
        byte[] arr = new byte[strings.length];
        for (String str : strings) {
            arr[i++] = Byte.valueOf(str);
        }
        return arr;
    }
}
