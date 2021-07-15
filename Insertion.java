
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Insertion {

    public Insertion() throws IOException {
        FileWriter fw = new FileWriter("output.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        invokeWorst(bw);
        invokeAvr(bw);
        invokeBest(bw);
        bw.close();
    }

    public void invokeBest(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin best insertion");
        bw.newLine();
        for (int i = 1; i < 5; i++) {
            callAscending(N, bw);
            N += 1;
        }
        for (int i = 1; i < 6; i++) {
            callAscending(N, bw);
            N *= 2;
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    public void invokeAvr(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin avr insertion");
        bw.newLine();
        for (int i = 1; i < 5; i++) {
            callRandom(N, bw);
            N += 1;
        }
        for (int i = 1; i < 6; i++) {
            callRandom(N, bw);
            N *= 2;
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    public void invokeWorst(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin worst insertion");bw.newLine();
        for (int i = 1; i < 5; i++) {
            callDescending(N, bw);
            N += 1;
        }
        for (int i = 1; i < 6; i++) {
            callDescending(N, bw);
            N *= 2;
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    private void insertion(int[] array, BufferedWriter bw) throws IOException {
        long start = System.nanoTime();
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        long end = System.nanoTime();
        bw.write(Long.toString((end - start)));
        bw.newLine();

    }

    private void callAscending(int N, BufferedWriter bw) throws IOException {
        int array[] = createAscendingOrder(N);
        bw.write(N + ",");
        insertion(array, bw);
    }

    private void callRandom(int N, BufferedWriter bw) throws IOException {
        int array[] = createRandomArray(N);
        bw.write(N + ",");
        insertion(array, bw);
    }

    private void callDescending(int N, BufferedWriter bw) throws IOException {
        int array[] = createDescendingOrder(N);
        bw.write(N + ",");
        insertion(array, bw);
    }

    private int[] createAscendingOrder(int N) {
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i;
        }
        return array;
    }

    private int[] createDescendingOrder(int N) {
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = N - i;
        }
        return array;
    }

    private int[] createRandomArray(int N) {
        Random r = new Random();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = (r.nextInt() % 10000 + 10000);
        }
        return array;
    }
}
