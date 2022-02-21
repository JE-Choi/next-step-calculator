package calculator;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private final static String FORMAT_SYMBOL_DEFAULT = ",|:";
    private final static String FORMAT_SYMBOL_CUSTOM = "//(.)\n(.*)";

    /**
     * 합계
     */
    public int add(final String inputText) {
        if (isEmpty(inputText)) {
            return 0;
        }
        final String[] split = splitText(inputText);
        final int[] numberList = toNumberList(split);
        return add(numberList);
    }

    /**
     * 합계
     */
    private int add(final int[] numberList) {
        return Arrays.stream(numberList).sum();
    }

    /**
     * String 배열을 Number 배열로 변환
     */
    private int[] toNumberList(final String[] strNumbers) throws IllegalArgumentException{
        return Arrays.stream(strNumbers).mapToInt((strNumber) -> {
            try {
                final int number = Integer.parseInt(strNumber);
                if (number < 0) {
                    throw new IllegalArgumentException("양수만 가능합니다.");
                }
                return number;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자만 가능합니다.");
            }
        }).toArray();
    }

    /**
     * 구분자를 통해서 문자열을 분리
     */
    private String[] splitText(final String inputText) {
        final Matcher customDelimiterMatcher = Pattern.compile(FORMAT_SYMBOL_CUSTOM).matcher(inputText);

        // 커스텀 구분자를 사용했다면
        if (customDelimiterMatcher.find()) {
            final String customDelimiter = customDelimiterMatcher.group(1);
            final String text = customDelimiterMatcher.group(2);
            return text.split(customDelimiter);
        }

        return inputText.split(FORMAT_SYMBOL_DEFAULT);
    }

    private boolean isEmpty(final String text) {
        return Objects.isNull(text)
                || text.trim().isEmpty() // 공백 제거 후, isEmpty 체크
                ;
    }
}
