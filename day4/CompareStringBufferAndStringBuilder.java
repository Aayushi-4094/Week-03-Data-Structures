public class CompareStringBufferAndStringBuilder {

    public static void main(String[] args) {
        int numStrings = 1000000;
        String str = "hello";

        long startTime = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numStrings; i++) {
            sb.append(str);
        }
        long endTime = System.nanoTime();
        long durationStringBuffer = endTime - startTime;

        startTime = System.nanoTime();
        StringBuilder sbuilder = new StringBuilder();
        for (int i = 0; i < numStrings; i++) {
            sbuilder.append(str);
        }
        endTime = System.nanoTime();
        long durationStringBuilder = endTime - startTime;

        System.out.println("Time taken by StringBuffer: " + durationStringBuffer + " nanoseconds");
        System.out.println("Time taken by StringBuilder: " + durationStringBuilder + " nanoseconds");
    }
}
