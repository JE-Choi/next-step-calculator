package calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {
    StringCalculator stringCalculator;

    @Before
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void 빈값체크() {
        assertEquals(0, stringCalculator.add(null));
        assertEquals(0, stringCalculator.add(" "));
    }

    @Test
    public void 숫자_하나를_문자열로_입력한_경우() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void 숫자_두개를_쉼표로_구분하여_입력한_경우() {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    public void 구분자를_쉼표랑_콜론_함께_사용() {
        assertEquals(6, stringCalculator.add("1,2:3"));
    }

    @Test(expected = NumberFormatException.class)
    public void 숫자가_아닌_값_사용() {
        stringCalculator.add("1,2:3:a");
    }

    @Test
    public void 커스텀_구분자사용() {
        assertEquals(6, stringCalculator.add("//;\n1;2;3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 음수가_있을경우() {
        stringCalculator.add("//;\n-1;2;3");
    }
}