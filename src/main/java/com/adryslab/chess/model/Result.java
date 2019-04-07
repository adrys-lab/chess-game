package com.adryslab.chess.model;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Wrapper class that encapsulates a Result of something, with valid or invalid.
 * Provides cappabilities for it's content mapping & consuming.
 * @param <T>
 */
public class Result<T> {

    private final boolean isValid;

    private Optional<T> instance;

    private Result(boolean isValid, T instance) {
        this.isValid = isValid;
        this.instance = Optional.of(instance);
    }

    private Result(boolean isValid) {
        this.isValid = isValid;
        this.instance = Optional.empty();
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isFailure() {
        return !isValid;
    }

    public boolean isPresent() {
        return instance.isPresent();
    }

    public static <T> Result<T> of(boolean valid, T instance) {
        return new Result<>(valid, instance);
    }

    public static <T> Result<T> valid() {
        return new Result<>(true);
    }

    public static <T> Result<T> valid(T instance) {
        return new Result<>(true, instance);
    }

    public static <T> Result<T> failure() {
        return new Result<>(false);
    }

    public static <T> Result<T> failure(T instance) {
        return new Result<>(false, instance);
    }

    public Result<T> map(Function<T, T> mapper) {
        Objects.requireNonNull(mapper);
        if (!instance.isPresent()) {
            return this;
        } else {
            this.instance = Optional.of(mapper.apply(instance.get()));
            return this;
        }
    }

    public Result<T> ifValid(Consumer<T> consumer) {

        if (isValid && instance.isPresent()) {
            consumer.accept(instance.get());
            return this;
        } else {
            return this;
        }
    }

    public Result<T> ifFailure(Consumer<T> consumer) {

        if (!isValid && instance.isPresent()) {
            consumer.accept(instance.get());
            return this;
        } else {
            return this;
        }
    }

    public Optional<T> getInstance() {
        return instance;
    }
}
