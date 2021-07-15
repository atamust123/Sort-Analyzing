
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Selection {

    public Selection() throws IOException {
        FileWriter fw = new FileWriter("output.txt", true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            invokeWorst(bw);
            invokeAvr(bw);
            invokeBest(bw);
            bw.close();
        }
    }

    public void invokeBest(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin best selection");bw.newLine();
        for (int i = 1; i < 5; i++) {
            callAscending(N,bw);
            N += 2;
        }
        for (int i = 1; i < 6; i++) {
            callAscending(N,bw);
            N *= 2;
        }
        bw.write("end");bw.newLine();
        bw.newLine();
    }

    public void invokeAvr(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin avr selection");bw.newLine();
        for (int i = 1; i < 5; i++) {
            callRandom(N,bw);
            N += 2;
        }
        for (int i = 1; i < 6; i++) {
            callRandom(N,bw);
            N *= 2;
        }
        bw.write("end");bw.newLine();
        bw.newLine();
        
    }

    public void invokeWorst(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin worst selection");bw.newLine();

        for (int i = 1; i < 5; i++) {
            callDescending(N,bw);
            N += 2;
        }
        for (int i = 1; i < 6; i++) {
            callDescending(N,bw);
            N *= 2;
        }
        bw.write("end");bw.newLine();bw.newLine();
    }

    private void callAscending(int N,BufferedWriter bw) throws IOException {
        int array[] = createAscendingOrder(N);
        bw.write(N + ",");
        selection(array,bw);
    }

    private void callRandom(int N,BufferedWriter bw) throws IOException {
        int array[] = createRandomArray(N);
        bw.write(N + ",");
        selection(array,bw);
    }

    private void callDescending(int N,BufferedWriter bw) throws IOException {
        int array[] = createDescendingOrder(N);
        bw.write(N + ",");
        selection(array,bw);
    }

    private int[] createRandomArray(int N) {
        Random r = new Random();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = (r.nextInt() % 10000 + 10000);
        }
        return array;
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

    private void selection(int[] array,BufferedWriter bw) throws IOException {
        long start, end;
        int temp;
        int N = array.length;
        start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        end = System.nanoTime();
        bw.write(Long.toString((end - start)));bw.newLine();
    }

}
