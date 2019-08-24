package com.haoran.common.u;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.function.Function;

/**
 * @author hanhaoran
 * @date 2019/8/24 10:36
 */

public final class U4Collection {
    private U4Collection() {}

    public static <E> ArrayList<E> toArrayList(String input, String delimiter, Function<String, E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((ArrayList<E>) Collections.emptyList());
        }

        Preconditions.checkNotNull(delimiter);
        Preconditions.checkNotNull(action);
        String[] src = input.split(delimiter);
        ArrayList<E> dest = Lists.newArrayListWithExpectedSize(src.length);
        to(src, dest, action);
        return dest;
    }

    public static <E> LinkedList<E> toLinkedList(String input, String delimiter, Function<String, E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((LinkedList<E>) Collections.emptyList());
        }

        Preconditions.checkNotNull(delimiter);
        Preconditions.checkNotNull(action);
        String[] src = input.split(delimiter);
        LinkedList<E> dest = Lists.newLinkedList();
        to(src, dest, action);
        return dest;
    }

    public static <E> HashSet<E> toHashSet(String input, String delimiter, Function<String, E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((HashSet<E>) Collections.emptySet());
        }

        Preconditions.checkNotNull(delimiter);
        Preconditions.checkNotNull(action);
        String[] src = input.split(delimiter);
        HashSet<E> dest = Sets.newHashSetWithExpectedSize(src.length);
        to(src, dest, action);
        return dest;
    }

    public static <E> LinkedHashSet<E> toLinkedHashSet(String input, String delimiter, Function<String, E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((LinkedHashSet<E>) Collections.emptySet());
        }

        Preconditions.checkNotNull(delimiter);
        Preconditions.checkNotNull(action);
        String[] src = input.split(delimiter);
        LinkedHashSet<E> dest = Sets.newLinkedHashSetWithExpectedSize(src.length);
        to(src, dest, action);
        return dest;
    }

    public static <E> void to(String input, String delimiter, Collection<E> dest, Function<String, E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return;
        }

        Preconditions.checkNotNull(delimiter);
        Preconditions.checkNotNull(action);
        String[] src = input.split(delimiter);
        to(src, dest, action);
    }

    private static <E> void to(String[] src, Collection<E> dest, Function<String, E> action) {
        for (String before : src) {
            if (U4Object.isNullOrEmpty(before)) {
                continue;
            }

            if (U4Object.isNullOrEmpty(before.trim())) {
                continue;
            }

            E after = action.apply(before);
            if (U4Object.nonNull(after)) {
                dest.add(after);
            }
        }
    }
}
