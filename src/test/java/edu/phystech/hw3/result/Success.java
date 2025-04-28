package edu.phystech.hw3.result;

import java.util.function.Function;

public record Success<T>(T value) implements Result<T> {
    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public T getOrDefault(T defaultValue) {
        return value;
    }

    @Override
    public Throwable getExceptionOrNull() {
        return null;
    }

    @Override
    public <R> Result<R> map(Function<? super T, ? extends R> mapper) {
        try {
            return new Success<>(mapper.apply(value));
        } catch (Exception e) {
            return new Failure<>(e);
        }
    }
}