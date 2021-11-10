package StreamCipher;

import java.util.Arrays;

public class LFSR {
    int[] register;
    int[] taps;
    int size;

    public LFSR(int[] register, int[] taps){
        this.register = register;
        this.taps = taps;
        this.size = register.length;
    }

    public LFSR() {
    }

    /**
     * rotates the register 1 position to the right
     * @return
     */
     private static int rotate(LFSR lfsr) {
        int[] array = lfsr.register;
        int feedBack = calcFeedBack(lfsr);
        int output = array[array.length-1];
//        printArray(array); //uncomment these two lines to see state of the LFSR between each rotate step
//        System.out.println("\n----------");
        System.arraycopy(array, 0, array, 1, array.length - 1);
        //sets the bit in pos 0 as the result of the feedback function.
        array[0] = feedBack;
        return output;
    }
    private static int getOutput(LFSR lfsr){
         return lfsr.register[lfsr.register.length-1];
    }

    private static int[] rotateRegister(LFSR lfsr){
         int feedBack = calcFeedBack(lfsr);
//         System.arraycopy(array,0, array, 1 ,array.length - 1);
         System.arraycopy(lfsr.register, 0, lfsr.register, 1, lfsr.register.length-1);

         lfsr.register[0] = feedBack;
         return lfsr.register;
    }

    /**
     * Calculates the bit to be inserted in the first position of the register
     * Using the taps array to control what positions the bits are to be taken from.
     * */
    private static int calcFeedBack(LFSR lfsr){
        int[] array = lfsr.getRegister();
        int[] taps = lfsr.getTaps();

        int feedback=0;
//        if (isAllZeroTaps(lfsr)){
//            return -1;
//        }

        for (int i = 0; i < taps.length; i++) {
            if(taps[i] == 1){
               feedback = feedback^array[i];
            }
        }
        return feedback;

    }
    //utility method to check if all taps are zero
    private static boolean isAllZeroTaps(LFSR lfsr) {
        int[] taps = lfsr.getTaps();
        for (int i: taps) {
            if(i == 1) return false;
        }
        return true;
    }

    public static void printRegister(LFSR lfsr){
        int[] arr = lfsr.getRegister();
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
    public static void printArray(int[] arr){
        for (int i: arr) {
            System.out.print(i + " ");
        }
    }

    private static boolean isRegAndTapsSameLength(LFSR lfsr) {
        try {
            if (lfsr.register.length == lfsr.taps.length){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("Taps must have the same length as register: " + e);
            return false;
        }
        return false;
    }

    //the max period of an lfsr is ((2^n)-1) where n is the length of the register
    public static int[] run(LFSR lfsr){
        if(isRegAndTapsSameLength(lfsr)){
            int maxPeriod = (1<<lfsr.getSize())-1;
            int[] keyStream = new int[maxPeriod];
            for (int i = 0; i < maxPeriod; i++) {
                keyStream[i] = rotate(lfsr);
//                keyStream[i] = getOutput(lfsr);
//                lfsr.register = rotateRegister(lfsr);
            }
            return keyStream;
        }
        else{
            return new int[0];
        }
    }




    @Override
    public int hashCode() {
        int result = Arrays.hashCode(register);
        result = 31 * result + Arrays.hashCode(taps);
        result = 31 * result + size;
        return result;
    }

    public int[] getRegister() {
        return register;
    }

    public void setRegister(int[] register) {
        this.register = register;
    }

    public int[] getTaps() {
        return taps;
    }

    public void setTaps(int[] taps) {
        this.taps = taps;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
