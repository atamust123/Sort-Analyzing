
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AtakanAYYILDIZ
 */
public class Radix {

    public Radix() throws IOException {
        FileWriter fw = new FileWriter("output.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        invokeWorst(bw);
        invokeAvr(bw);
        invokeBest(bw);
        bw.close();
    }

    private void invokeBest(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin best radix");
        bw.newLine();
        for (int i = 1; i < 5; i++) {
            callAscending(N, bw);
            N *= 2;
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    private void invokeAvr(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin avr radix");
        bw.newLine();
        for (int i = 1; i < 5; i++) {
            callRandom(N, bw);
            N *= 2;
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    private void invokeWorst(BufferedWriter bw) throws IOException {
        int N = 10;
        bw.write("begin worst radix");
        bw.newLine();
        for (int i = 1; i < 5; i++) {
            callDescending(N, bw);
            N *= 2;
        }
        bw.write("end");
        bw.newLine();
        bw.newLine();
    }

    private void callAscending(int N, BufferedWriter bw) throws IOException {
        int array[] = createOneDigit(N);
        bw.write(N + ",");
        radixSort(array, bw);
    }

    private void callRandom(int N, BufferedWriter bw) throws IOException {
        int array[] = createRandomArray(N);
        bw.write(N + ",");
        radixSort(array, bw);
    }

    private void callDescending(int N, BufferedWriter bw) throws IOException {
        int array[] = createDescendingOrder(N);
        bw.write(N + ",");
        radixSort(array, bw);
    }

    private int[] createRandomArray(int N) {
        Random r = new Random();
        int[] array = new int[N];
        array[0] = 1;
        for (int i = 1; i < N; i++) {
            array[i] = ((r.nextInt() % 100) + 100);
        }
        return array;
    }

    private int[] createOneDigit(int N) {
        int[] array = new int[N];
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            array[i] = (r.nextInt() % 10 + 10) % 9;
        }
        return array;
    }

    private int[] createDescendingOrder(int N) {
        int[] array = new int[N];
        Random r = new Random();
        array[0]=N*100;
        for (int i = 1; i < N; i++) {
            array[i] = ((r.nextInt() % 1000) + 1000);
        }
        return array;
    }

    private void radixSort(int[] array, BufferedWriter bw) throws IOException {
        long start = System.nanoTime();
        int N = array.length;
        int max = array[0];
        for (int i = 1; i < N; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        for (int exp = 1; max / exp > 0; exp *= 10) {
            int temp[] = new int[N];
            int i = 0;
            int count[] = new int[10];
            while (i < 10) {
                count[i] = 0;
                i++;
            }
            for (i = 0; i < N; i++) {
                count[(array[i] / exp) % 10]++;
            }
            for (i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (i = N - 1; i >= 0; i--) {
                temp[count[(array[i] / exp) % 10] - 1] = array[i];
                count[(array[i] / exp) % 10]--;
            }
            for (i = 0; i < N; i++) {
                array[i] = temp[i];
            }
        }
        long end = System.nanoTime();
        bw.write(Long.toString((end - start)));
        bw.newLine();
    }

}
