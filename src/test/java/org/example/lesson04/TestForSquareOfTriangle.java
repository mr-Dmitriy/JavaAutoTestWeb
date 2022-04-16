package org.example.lesson04;

import org.example.lesson04.SquareOfTriangle;
import org.example.lesson04.ExceptionForSquareOfTriangle;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestForSquareOfTriangle {

    private static final Logger logger = LoggerFactory.getLogger(TestForSquareOfTriangle.class);

    static Integer myTestInt = 0;

    @BeforeAll
     static void beforeAll() {
        logger.info("Let's start the tests");
    }


    @BeforeEach
    void initTest(){
        myTestInt++;
        System.out.println("Test №"+myTestInt);
    }

    @ParameterizedTest
    @Tag("Negative")
    @DisplayName("Test with negative value ")
    @CsvSource({"-1,2,3",
                "2,-3,5",
                "3,4,-1"})
    void testWithNegativeValues(int a, int b, int c) throws ExceptionForSquareOfTriangle {
        logger.info("a = " + a + "; b = " + b + "; с = " + c );
        SquareOfTriangle triangle = new SquareOfTriangle();
        Assertions.assertThrows(ExceptionForSquareOfTriangle.class,()-> triangle.squareOfTriangle(a, b, c));
    }




    @ParameterizedTest
    @Tag("Positive")
    @DisplayName("Передадим в метод треугольник с разными сторонами")
    @CsvSource({ "12,34,43,150.9252712437516",
                 "43,23,34,388.84444190447164" })
    void triangleDifferentSidesTest(int a, int b, int c, double s) throws ExceptionForSquareOfTriangle {
        logger.info("a = " + a + "; b = " + b + "; с = " + c + "; s = " + s );
        SquareOfTriangle triangle = new SquareOfTriangle();
        Assertions.assertEquals(s,triangle.squareOfTriangle(a, b, c));
    }





    @AfterAll
    static void AfterAll() {
        logger.info("The end of tests");
    }

}
