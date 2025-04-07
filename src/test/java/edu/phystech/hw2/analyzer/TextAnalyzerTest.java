package edu.phystech.hw2.analyzer;

import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TextAnalyzerTest {

    public interface TextAnalyzer {
        Label processText(String text);
    }

    public enum Label {
        SPAM, TOO_LONG, OK, NEGATIVE
    }

    public static class TooLongTextAnalyzer implements TextAnalyzer {
        private final int maxi;
        public TooLongTextAnalyzer(int maxi) {
            this.maxi = maxi;
        }

        @Override
        public Label processText(String text) {
            if (text.length() > maxi) {
                return Label.TOO_LONG;
            } else {
                return Label.OK;
            }
        }
    }

    public static class SpamAnalyzer implements TextAnalyzer {
        private final List<String> keywords;

        public SpamAnalyzer(List<String> keywords) {
            this.keywords = keywords;
        }

        @Override
        public Label processText(String text) {
            if (keywords.stream().anyMatch(text::contains)) {
                return Label.SPAM;
            } else {
                return Label.OK;
            }
        }
    }

    public static class NegativeTextAnalyzer implements TextAnalyzer {
        private static final List<String> emoji = List.of(":(", ":|", ":c", "=(", "=|");

        @Override
        public Label processText(String text) {
            String[] word = text.split("\\s+");
            for (String emoji : word) {
                if (NegativeTextAnalyzer.emoji.contains(emoji)) {
                    return Label.NEGATIVE;
                }
            }
            return Label.OK;
        }
    }

    @Test
    public void tooLongTextTest() {
        TooLongTextAnalyzer tooLongTextAnalyzer = new TooLongTextAnalyzer(3);
        Assertions.assertEquals(Label.TOO_LONG, tooLongTextAnalyzer.processText("123123"));
        Assertions.assertEquals(Label.OK, tooLongTextAnalyzer.processText("12"));
    }

    @Test
    public void spamTextTest() {
        SpamAnalyzer spamAnalyzer = new SpamAnalyzer(List.of("kek", "lol"));
        Assertions.assertEquals(Label.SPAM, spamAnalyzer.processText("kek 123"));
        Assertions.assertEquals(Label.OK, spamAnalyzer.processText("123"));
        Assertions.assertEquals(Label.SPAM, spamAnalyzer.processText("123 lol"));
    }

    @Test
    public void negativeTextTest() {
        NegativeTextAnalyzer negativeTextAnalyzer = new NegativeTextAnalyzer();
        Assertions.assertEquals(Label.NEGATIVE, negativeTextAnalyzer.processText("hello :("));
        Assertions.assertEquals(Label.NEGATIVE, negativeTextAnalyzer.processText(":) =("));
        Assertions.assertEquals(Label.NEGATIVE, negativeTextAnalyzer.processText("))) :|"));
        Assertions.assertEquals(Label.OK, negativeTextAnalyzer.processText("))) :||"));
    }
}