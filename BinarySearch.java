
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BinarySearch {

    public BinarySearch() throws IOException {
        FileWriter fw = new FileWriter("output.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        invokeWorst(bw);
        invokeAvr(bw);
        invokeBest(bw);
        bw.close();
    }

    private void invokeAvr(BufferedWriter bw) throws IOException {
        bw.write("begin avr binary");
        bw.newLine();
        int N = 2;
        for (int i = 1; i < 8; i++) {
            searchOther(N, 16,bw);
            N = 2 * (N);
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    private void invokeWorst(BufferedWriter bw) throws IOException {
        bw.write("begin worst binary");
        bw.newLine();
        int N = 2;
        for (int i = 1; i < 8; i++) {
            searchOther(N, (N + 10),bw);
            N = 2 * (N);
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    private void invokeBest(BufferedWriter bw) throws IOException {
        bw.write("begin best binary");
        bw.newLine();
        int N = 2;
        for (int i = 1; i < 8; i++) {
            searchBest(N, (N) / 2,bw);
            N = 2 * (N);
        }
        bw.write("end");
    }

    private void searchBest(int arraySize, int SearchNumber, BufferedWriter bw) throws IOException {
        int array[] = createAscendingOrder(arraySize);
        long start = System.nanoTime();
        binary(array, SearchNumber);
        long end = System.nanoTime();
        bw.write(arraySize + ",");
        bw.write(Long.toString((end - start)));
        bw.newLine();

    }

    private void searchOther(int arraySize, int SearchNumber, BufferedWriter bw) throws IOException {
        int array[] = createRandomAscendingOrder(arraySize);
        long start = System.nanoTime();
        binary(array, SearchNumber);
        long end = System.nanoTime();
        bw.write(arraySize + ",");
        bw.write(Long.toString((end - start)));
        bw.newLine();

    }

    private int[] createAscendingOrder(int N) {
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private int[] createRandomAscendingOrder(int N) {
        int[] array = new int[N];
        Random r = new Random();
        array[0] = 1;
        for (int i = 1; i < N; i++) {
            array[i] = r.nextInt() % 1000 + 1000 + 2 * array[i - 1];
        }
        return array;
    }

    private int binary(int[] array, int SearchNumber) {
        int right = array.length - 1;
        int left = 0, middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (array[middle] == SearchNumber) {
                return middle;
            } else if (array[middle] > SearchNumber) {
                right = middle - 1;
            } else if (array[middle] < SearchNumber) {
                left = middle + 1;
            }
        }
        return -1;
    }
}
