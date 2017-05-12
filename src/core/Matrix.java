package core;

/**
 * Created by Dell on 29.04.2017.
 */
public class Matrix {
    private int N;
    private double[][] arr;
    public Matrix(int N, Number[] numbers) {
        this.N = N;
        this.arr = new double[N][N+1];
        for (int i = 0; i < numbers.length || i < N*(N+1); i++) {
            arr[i/(N+1)][i%(N+1)] = numbers[i].doubleValue();
        }
    }
    public Matrix clone()
    {
        Number[] numbers = new Number[N*(N+1)];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = arr[i/(N+1)][i%(N+1)];
        }
        Matrix temp = new Matrix(N,numbers);
        return temp;
    }
    public void print()
    {
        for (int i = 0; i < N; i++) {
            System.out.print("(  ");
            for (int j = 0; j < N; j++) {
                System.out.print(String.format("%.3f  ", arr[i][j]));
            }
            System.out.print("| "+String.format("%.3f  )\n", arr[i][N]));
        }
    }
    public double[] solve()
    {
        double s,c;
        Matrix temp = this.clone();
        for (int k = 0; k < N-1; k++) {


            for (int i = k+1; i < N; i++) {
                c = temp.arr[k][k]/Math.sqrt(temp.arr[k][k]*temp.arr[k][k] + temp.arr[i][k]*temp.arr[i][k]);
                s = temp.arr[i][k]/Math.sqrt(temp.arr[k][k]*temp.arr[k][k] + temp.arr[i][k]*temp.arr[i][k]);
                for (int j = k; j < N+1; j++) {
                    double a = temp.arr[k][j],b = temp.arr[i][j];
                    temp.arr[k][j] = c*a + s*b;
                    temp.arr[i][j] = -s*a + c*b;
                }
            }
        }
        //temp.print();
        double[] solutions = new double[N];
        for (int i = N-1; i >=0 ; i--) {
            solutions[i] = temp.arr[i][N];
            //System.out.println(N-i);
            for (int j = 0; j < N-1-i; j++) {
                //System.out.println(N-1-j);
                solutions[i] -= temp.arr[i][N-1-j] * solutions[N-1-j];
            }
            solutions[i]/= temp.arr[i][i];
        }

        return solutions;
    }
    public double[][] getArray()
    {
        return this.arr;
    }

}
