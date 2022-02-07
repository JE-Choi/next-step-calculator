package calculator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(final String inputText) {
        if (isEmpty(inputText)) {
            return 0;
        }
        final String[] split;
        final Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(inputText);

        if (matcher.find()) {
            final String customDelimiter = matcher.group(1);
            final String text = matcher.group(2);
            split = text.split(customDelimiter);
        } else {
            split = inputText.split(",|:");
        }

        int result = 0;
        for (final String s : split) {
            int number = Integer.parseInt(s);
            if (number < 0) {
                throw new IllegalArgumentException("양수를 입력해주세요.");
            }
            result += Integer.parseInt(s);
        }
        return result;
    }

    private boolean isEmpty(final String text) {
        return Objects.isNull(text)
                || text.trim().isEmpty() // 공백 제거 후, isEmpty 체크
                ;
    }
}
