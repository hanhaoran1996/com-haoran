package com.haoran.common.u;

import com.haoran.common.Constants;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author hanhaoran
 * @date 2019/8/24 10:36
 */

public final class U4Collection {
    private U4Collection() {
    }

    public static <T, C extends Collection<T>> C toCollection(
            String input,
            String separator,
            Function<String, ? extends T> toItemAction,
            IntFunction<C> destSupplier,
            Supplier<C> emptySupplier
    ) {
        U4Object.checkNotNull(separator, toItemAction, destSupplier, emptySupplier);
        if (U4Object.isNullOrEmpty(input)) {
            return emptySupplier.get();
        }

        String[] inputs = input.split(separator);
        C dest = destSupplier.apply(inputs.length);
        for (String value: inputs) {
            T item = toItemAction.apply(value);
            if (U4Object.isNull(item)) {
                continue;
            }
            dest.add(item);
        }
        return dest;
    }

    public static <S, T, C extends Collection<T>> C toCollection(
            S[] inputs,
            Function<? super S, ? extends T> toItemAction,
            IntFunction<C> destSupplier,
            Supplier<C> emptySupplier
    ) {
        U4Object.checkNotNull(toItemAction, destSupplier, emptySupplier);
        if (U4Object.isNullOrEmpty(inputs)) {
            return emptySupplier.get();
        }

        C dest = destSupplier.apply(inputs.length);
        for (S value: inputs) {
            T item = toItemAction.apply(value);
            if (U4Object.isNull(item)) {
                continue;
            }
            dest.add(item);
        }
        return dest;
    }

    public static <K, V, M extends Map<K, V>> M toMap(
            String input,
            String separator,
            String keyValSeparator,
            Function<String, ? extends K> toKeyAction,
            Function<String, ? extends V> toValAction,
            IntFunction<M> destSupplier,
            Supplier<M> emptySupplier
    ) {
        U4Object.checkNotNull(separator, keyValSeparator, toKeyAction, toValAction, destSupplier, emptySupplier);
        if (U4Object.isNullOrEmpty(input)) {
            return emptySupplier.get();
        }

        String[] entries = input.split(separator);
        M dest = destSupplier.apply(entries.length);
        for (String entry: entries) {
            String[] keyVal = entry.split(keyValSeparator);
            if (keyVal.length != Constants.TWO) {
                continue;
            }
            K key = toKeyAction.apply(keyVal[0]);
            V val = toValAction.apply(keyVal[1]);
            dest.put(key, val);
        }
        return dest;
    }

    public static <K, V, C extends Collection<V>, M extends Map<K, C>> M toMap(
            String input,
            String separator,
            String keyValSeparator,
            Function<String, ? extends K> toKeyAction,
            Function<String, ? extends V> toValAction,
            Supplier<C> valContainerSupplier,
            IntFunction<M> destSupplier,
            Supplier<M> emptySupplier
    ) {
        U4Object.checkNotNull(separator, keyValSeparator, toKeyAction, toValAction, valContainerSupplier, destSupplier, emptySupplier);
        if (U4Object.isNullOrEmpty(input)) {
            return emptySupplier.get();
        }

        String[] entries = input.split(separator);
        M dest = destSupplier.apply(entries.length);
        for (String entry: entries) {
            String[] keyVal = entry.split(keyValSeparator);
            if (keyVal.length != Constants.TWO) {
                continue;
            }
            K key = toKeyAction.apply(keyVal[0]);
            V val = toValAction.apply(keyVal[1]);
            dest.computeIfAbsent(key, k -> valContainerSupplier.get()).add(val);
        }
        return dest;
    }
}
