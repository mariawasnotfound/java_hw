package edu.phystech.hw3.result;

import java.util.function.Supplier;

public final class ResultUtil {
    private ResultUtil() {}

    public static <T> Result<T> execute(Supplier<T> block) {
        try {
            return new Success<>(block.get());
        } catch (Exception ex) {
            return new Failure<>(ex);
        }
    }
}