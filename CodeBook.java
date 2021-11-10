package StreamCipher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class with utility methods to convert String to binary and probably the other way as well
 * */
public class CodeBook {

    String input = "testInput";

    public static int[] XOR(int[] keyStream, int[] plainText){
//        System.out.println("keystream.lenght: " + keyStream.length + " plaintext.lenght: " +plainText.length );
        if(keyStream.length >= plainText.length) {
            int[] result = new int[plainText.length];
            for (int i = 0; i < plainText.length; i++) {
                result[i] = keyStream[i] ^ plainText[i];
            }
            return result;
        }
        return new int[0];
    }
    public static int[] xor(int[] keyStream, int[] text){
        int[] result = new int[text.length];
        for (int i = 0; i < text.length; i++) {
            result[i] = (text[i]+keyStream[i])%2;
        }
        return result;
    }

    public static int[] convertBinaryStringToIntArray(String input){
        int[] result = new int[input.length()];
        for(int i = 0; i < result.length; i++){
            result[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return result;
    }

    public static String convertStringToBinary(String input){
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for(char aChar: chars){
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar)) // char -> int, auto-cast
                            .replaceAll(" ", "0") //zero pads
            );
        }
        return result.toString();
    }

    public static String prettyBinary(String binary, int blockSize, String separator){
        List<String> result = new ArrayList<>();
        int i = 0;
        while(i < binary.length()){
            result.add(binary.substring(i, Math.min(i + blockSize, binary.length())));
            i += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));

    }

    public static String convertBinaryToString(String input){
        String raw = Arrays.stream(input.split(" "))
                .map(binary -> Integer.parseInt(binary,2))
                .map(Character::toString)
                .collect(Collectors.joining()); //cut the space

        return raw;
    }

    public static String convertIntArrToString(int[] input){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);
        }
        return sb.toString();
    }
//    public static String convertIntArrToStringSimple(int[] input){
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < input.length; i++) {
//            sb.append(input[i]);
//        }
//        return sb.toString();
//    }

    public static String splitBinaryStringInBlocksOf8(String input){
        String result;
        String[] array = input.split("(?<=\\G.{8})");
        StringBuilder sb = new StringBuilder();
        for (String s: array) {
            sb.append(s+" ");
        }
        result = sb.toString().trim();
//        StringBuilder sb = new StringBuilder();
//        int step = 8;
//        for (int i = 0; i < input.length(); i++) {
//            int counter = 0;
//            while(counter<step && counter+i < input.length()){
//                sb.append(input.charAt(i+counter));
//                counter++;
//            }
//            sb.append(" ");
//        }
//        result = sb.toString();
        return result;
    }


}
