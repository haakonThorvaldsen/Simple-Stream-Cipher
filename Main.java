package StreamCipher;

import java.nio.charset.StandardCharsets;

import static StreamCipher.LFSR.*;
import static StreamCipher.CodeBook.*;

public class Main {
    public static void main(String[] args){
        int[] IV = {1,1,1,1,1,1,1}; //as long as it's not the all zeroes we will get the same keystream, however it might be shifted
        int[] taps = {0,0,0,0,0,1,1}; // see https://en.wikipedia.org/wiki/Linear-feedback_shift_register for max period lfsr's

        LFSR lfsr = new LFSR(IV, taps);

        System.out.println("\n"+"===================");
        int[] keySteam = run(lfsr);
        printArray(keySteam);
        System.out.println("\n ======================");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keySteam.length; i++) {
            sb.append(keySteam[i]);
        }
        System.out.println("Keystream: ");
        System.out.println(convertIntArrToString(keySteam));
        String keyStreamString = convertIntArrToString(keySteam);


        //password or key which is the solution
//        String plaintext = "Vismacalendar";
        String plaintext = "VismaPassword";
        String altPlaintext = "SantasChristmas";
        //String plaintext = "I\u0015\u0086®\u0015¦\u0001Ð\u0013§,+(";
        //String plaintext = "¨mk<\u0085\u001Aµ\u0096y'ÑØÿ";

        //convert the string to a binary string
        String plaintextBinary = convertStringToBinary(plaintext);
        System.out.println("plaintext in binary: \n" + plaintextBinary);

        //convert the binary string to int[]array
        int[] plainTextArr = convertBinaryStringToIntArray(plaintextBinary);

        //Xor the binary values with the keystream.
        int[] cipherTextArr = XOR(keySteam, plainTextArr);


        //convert ciphertextArray to binary string, and the convert it to ascii/utf8 characters as final output.
        String cipherTextString = convertIntArrToString(cipherTextArr);
        System.out.println("ciphertextString: \n"+cipherTextString);
        System.out.println("ciphertextString.length(): "+cipherTextString.length());

        String cipherTextIn8BitBlocks = splitBinaryStringInBlocksOf8(cipherTextString);
        System.out.println("cipherTextIn8BitBlocks.length():" + cipherTextIn8BitBlocks.length());
        System.out.println("8 bit blocks of ciphertext: \n"+cipherTextIn8BitBlocks);
        System.out.println("" +
                " \n"+splitBinaryStringInBlocksOf8(plaintextBinary) + ": Plaintext" +"\n" +
         cipherTextIn8BitBlocks + ": ciphertext \n" +
                splitBinaryStringInBlocksOf8(keyStreamString) +": Keystream");



        //convert ciphertextString into ascii string
        String cipherTextAscii = convertBinaryToString(cipherTextIn8BitBlocks);
        System.out.println("cipherTextAscii: "+ cipherTextAscii);


    }
}
