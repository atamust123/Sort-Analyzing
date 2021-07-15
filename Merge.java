
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Merge {

    public Merge() throws IOException {
        FileWriter fw = new FileWriter("output.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        invokeWorst(bw);
        invokeAvr(bw);
        invokeBest(bw);
        bw.close();
    }

    public void invokeBest(BufferedWriter bw) throws IOException {
        bw.write("begin best merge");bw.newLine();
        int N = 100;
        for (int i = 1; i < 5; i++) {
            callAscending(N, 0, N - 1,bw);
            N = N *2;
        }
        bw.write("end");bw.newLine();bw.newLine();
        
    }

    public void invokeAvr(BufferedWriter bw) throws IOException {
        int N = 100;
        bw.write("begin avr merge");bw.newLine();
        for (int i = 1; i < 5; i++) {
            callDescending(N, 0, N - 1,bw);
            N = N *2;
        }
        bw.write("end");bw.newLine();bw.newLine();
        
    }

    public void invokeWorst(BufferedWriter bw) throws IOException {
        int N = 100;
        bw.write("begin worst merge");bw.newLine();
        for (int i = 1; i < 5; i++) {
            callRandom(N, 0, N - 1,bw);
            N = N *2;
        }
        bw.write("end");bw.newLine();bw.newLine();
        
    }

    private void callRandom(int N, int left, int right,BufferedWriter bw) throws IOException {
        int array[] = createRandomArray(N);
        bw.write(N + ",");
        int[] aux = new int[N];
        long start = System.nanoTime();
        mergeSort(array, aux, left, right);
        long end = System.nanoTime();
        bw.write(Long.toString((end - start)));bw.newLine();
    }

    private void callAscending(int N, int left, int right,BufferedWriter bw) throws IOException {
        int[] array = createAscendingOrder(N);
        int[] aux = new int[N];
        bw.write(N + ",");
        long start = System.nanoTime();
        mergeSort(array, aux, left, right);
        long end = System.nanoTime();
        bw.write(Long.toString((end - start)));bw.newLine();
    }

    private void callDescending(int N, int left, int right,BufferedWriter bw) throws IOException {
        int[] array = createDescendingOrder(N);
        int[] aux = new int[N];
        bw.write(N + ",");
        long start = System.nanoTime();
        mergeSort(array, aux, left, right);
        long end = System.nanoTime();
        bw.write(Long.toString((end - start)));bw.newLine();
    }

    private void mergeSort(int[] array, int[] aux, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (right + left) / 2;
        mergeSort(array, aux, left, middle);
        mergeSort(array, aux, middle + 1, right);
        merge(array, aux, left, right, middle);
    }

    private void merge(int[] array, int aux[], int left, int right, int middle) {
        int counter;
        for (counter = left; counter <= right; counter++) {
            aux[counter] = array[counter];
        }
        int i = left, j = middle + 1;
        for (counter = left; counter <= right; counter++) {
            if (i > middle) {
                array[counter] = aux[j++];
            } else if (j > right) {
                array[counter] = aux[i++];
            } else if (aux[i] < aux[j]) {
                array[counter] = aux[i++];
            } else {
                array[counter] = aux[j++];
            }

        }

    }

    private int[] createRandomArray(int N) {
        Random r = new Random();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = (r.nextInt() % 1000000 + 100000);
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
}
