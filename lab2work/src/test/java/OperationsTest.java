
import org.example.Logic.Operations;
import org.example.Model.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OperationsTest {

    @Test
    public void addTest() {
        String s1 = "+x^3-2x^2+6x^1-5";
        String s2 = "+x^2-1";
        Polynomial pol1 = new Polynomial().parsing(s1);
        Polynomial pol2 = new Polynomial().parsing(s2);

        Polynomial expectedResult = new Polynomial().parsing("+x^3-x^2+6x^1-6");
        Polynomial actualResult = new Operations().addPolinoms(pol1, pol2);

        assertEquals(expectedResult.toString(), actualResult.toString());

        Polynomial wrongResult = new Polynomial().parsing("+x^3-x^2+6x^1");
        assertNotEquals(wrongResult.toString(), actualResult.toString());
    }

    @Test
    public void subtractTest() {
        String s1 = "+x^3-2x^2+6x^1-5";
        String s2 = "+x^2-1";
        Polynomial pol1 = new Polynomial().parsing(s1);
        Polynomial pol2 = new Polynomial().parsing(s2);

        Polynomial expectedResult = new Polynomial().parsing("+x^3-3x^2+6x^1-4");
        Polynomial actualResult = new Operations().substractPolinoms(pol1, pol2);

        assertEquals(expectedResult.toString(), actualResult.toString());

        Polynomial wrongResult = new Polynomial().parsing("+3x^3-3x^2+6x^1-4");
        assertNotEquals(wrongResult.toString(), actualResult.toString());
    }

    @Test
    public void multiplyTest() {
        String s1 = "+x^3-2x^2+6x^1-5";
        String s2 = "+x^2-1";
        Polynomial pol1 = new Polynomial().parsing(s1);
        Polynomial pol2 = new Polynomial().parsing(s2);

        Polynomial expectedResult = new Polynomial().parsing("+x^5-2x^4+5x^3-3x^2-6x^1+5");
        Polynomial actualResult = new Operations().multiplyPolinoms(pol1, pol2);

        assertEquals(expectedResult.toString(), actualResult.toString());

        Polynomial wrongResult = new Polynomial().parsing("+x^5-2x^4+2x^3-3x^2-6x^2+1");
        assertNotEquals(wrongResult.toString(), actualResult.toString());
    }

    @Test
    public void divideTest() {
        String s1 = "+x^3-2x^2+6x^1-5";
        String s2 = "+x^2-1";
        Polynomial pol1 = new Polynomial().parsing(s1);
        Polynomial pol2 = new Polynomial().parsing(s2);

        String expectedResult = "Quotient: +x-2.0  Remainder: +7.0x-7.0";
        String actualResult = new Operations().divisionPolynoms(pol1, pol2);

        assertEquals(expectedResult, actualResult);

        String wrongResult = "Quotient: +x-1.0  Remainder: +7.0x-7.0";
        assertNotEquals(wrongResult, actualResult);
    }

    @Test
    public void derivativeTest() {
        String s1 = "+x^3-2x^2+6x^1-5";
        Polynomial pol1 = new Polynomial().parsing(s1);

        Polynomial expectedResult = new Polynomial().parsing("+3x^2-4x^1+6");
        Polynomial actualResult = new Operations().derivatePolynomial(pol1);

        assertEquals(expectedResult.toString(), actualResult.toString());

        Polynomial wrongResult = new Polynomial().parsing("+6x^2-4x^1+3");
        assertNotEquals(wrongResult.toString(), actualResult.toString());
    }

    @Test
    public void integrateTest() {
        String s1 = "+x^3-2x^2+6x^1-5";
        Polynomial pol1 = new Polynomial().parsing(s1);
        String result = "+0.3x^4-0.7x^3+3.0x^2-5.0x";

        Polynomial actualResult = new Operations().integratePolynomial(pol1);

        assertEquals(result, actualResult.toString());

        String wrongResult = "+4.0x^4-0.67x^3+3x^2-5x";
        assertNotEquals(wrongResult, actualResult.toString());
    }
}