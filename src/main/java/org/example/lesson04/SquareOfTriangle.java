package org.example.lesson04;


public class SquareOfTriangle {

    public double squareOfTriangle(int a, int b, int c) throws ExceptionForSquareOfTriangle {

        if (  a <= 0 ||  b <= 0 ||  c <= 0 ) throw new ExceptionForSquareOfTriangle(" The triangle can not have side = 0 or have negative value");

        if ((a + b) <= c || (b + c) <= a || (c + a) <= b) throw new ExceptionForSquareOfTriangle("There are not such triangles with such sides ");

        double p = ((double) a + (double) b + (double) c) / 2.0;
        return Math.sqrt(p * (p - (double) a) * (p - (double) b) * ( p - (double) c));
    }
}
