package com.haoran.common.u;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

/**
 * @author hanhaoran
 * @date 2019/9/23 15:17
 */

public class Collector<S, T> {

    public static <S, T> Collector<S, T> ofArrays() {
        return ((Collector<S, T>) ARRAY_COLLECTOR);
    }

    public static <S, T> Collector<S, T> on(String separator) {
        return new Collector<>(separator);
    }

    private static final Collector<?, ?> ARRAY_COLLECTOR = new Collector<>(null);

    private final String separator;
    private Iterator<S> source;
    private Iterator<T> target;
    private Function<? super S, ? extends T> action;
    private boolean skipNulls = true;

    private Collector(String separator) {
        this.separator = separator;
    }

    public Collector<S, T> source(S[] sources) {
        this.source = Arrays.stream(sources).iterator();
        return this;
    }

    public Collector<S, T> source(String source) {
        return source((S[]) source.split(separator));
    }

    public Collector<S, T> action(Function<? super S, ? extends T> action) {
        this.action = action;
        return this;
    }

    public Collector<S, T> skipNulls(boolean skip) {
        this.skipNulls = skip;
        return this;
    }

    public <C extends Collection<T>> void target(C dest) {

    }
}
