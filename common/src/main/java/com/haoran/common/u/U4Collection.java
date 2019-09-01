package com.haoran.common.u;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.function.Function;

/**
 * @author hanhaoran
 * @date 2019/8/24 10:36
 */

public final class U4Collection {
    private U4Collection() {}

    public static <E> ArrayList<E> toArrayList(String input, String separator, Function<String, ? extends E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((ArrayList<E>) Collections.emptyList());
        }

        U4Object.checkNotNull(separator, action);
        String[] src = input.split(separator);
        ArrayList<E> dest = Lists.newArrayListWithExpectedSize(src.length);
        to(src, dest, action);
        return dest;
    }

    public static <E> LinkedList<E> toLinkedList(String input, String separator, Function<String, ? extends E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((LinkedList<E>) Collections.emptyList());
        }

        U4Object.checkNotNull(separator, action);
        String[] src = input.split(separator);
        LinkedList<E> dest = Lists.newLinkedList();
        to(src, dest, action);
        return dest;
    }

    public static <E> HashSet<E> toHashSet(String input, String separator, Function<String, ? extends E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((HashSet<E>) Collections.emptySet());
        }

        U4Object.checkNotNull(separator, action);
        String[] src = input.split(separator);
        HashSet<E> dest = Sets.newHashSetWithExpectedSize(src.length);
        to(src, dest, action);
        return dest;
    }

    public static <E> LinkedHashSet<E> toLinkedHashSet(String input, String separator, Function<String, ? extends E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((LinkedHashSet<E>) Collections.emptySet());
        }

        U4Object.checkNotNull(separator, action);
        String[] src = input.split(separator);
        LinkedHashSet<E> dest = Sets.newLinkedHashSetWithExpectedSize(src.length);
        to(src, dest, action);
        return dest;
    }

    public static <E extends Comparable<E>> SortedSet<E> toSortedSet(String input, String separator, Function<String, ? extends E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return Collections.emptySortedSet();
        }

        U4Object.checkNotNull(separator, action);
        String[] src = input.split(separator);
        TreeSet<E> dest = Sets.newTreeSet();
        to(src, dest, action);
        return dest;
    }

    public static <K, V> HashMap<K, V> toHashMap(String input, String level1Separator, String level2Separator, Function<String, ? extends K> toKeyAction, Function<String, ? extends V> toValueAction) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((HashMap<K, V>) Collections.emptyMap());
        }

        HashMap<K, V> dest = Maps.newHashMap();
        to(input, level1Separator, level2Separator, dest, toKeyAction, toValueAction);
        return dest;
    }

    public static <K, V> LinkedHashMap<K, V> toLinkedHashMap(String input, String level1Separator, String level2Separator, Function<String, ? extends K> toKeyAction, Function<String, ? extends V> toValueAction) {
        if (U4Object.isNullOrEmpty(input)) {
            return ((LinkedHashMap<K, V>) Collections.emptyMap());
        }

        LinkedHashMap<K, V> dest = Maps.newLinkedHashMap();
        to(input, level1Separator, level2Separator, dest, toKeyAction, toValueAction);
        return dest;
    }

    public static <K extends Comparable<K>, V> SortedMap<K, V> toSortedMap(String input, String level1Separator, String level2Separator, Function<String, ? extends K> toKeyAction, Function<String, ? extends V> toValueAction) {
        if (U4Object.isNullOrEmpty(input)) {
            return Collections.emptySortedMap();
        }

        TreeMap<K, V> dest = Maps.newTreeMap();
        to(input, level1Separator, level2Separator, dest, toKeyAction, toValueAction);
        return dest;
    }

    public static <K, V> void to(String input, String level1Separator, String level2Separator, Map<K, V> dest, Function<String, ? extends K> toKeyAction, Function<String, ? extends V> toValueAction) {
        if (U4Object.isNullOrEmpty(input)) {
            return;
        }

        U4Object.checkNotNull(level1Separator, level2Separator, dest, toKeyAction, toValueAction);
        String[] arrays = input.split(level1Separator);
        for (String array: arrays) {
            if (U4Object.isNullOrEmpty(array)) {
                continue;
            }

            if (U4Object.isNullOrEmpty(array.trim())) {
                continue;
            }

            String[] kvs = array.split(level2Separator);
            K key = toKeyAction.apply(kvs[0]);
            V value = toValueAction.apply(kvs[1]);
            dest.put(key, value);
        }
    }

    public static <E> void to(String input, String separator, Collection<E> dest, Function<String, ? extends E> action) {
        if (U4Object.isNullOrEmpty(input)) {
            return;
        }

        U4Object.checkNotNull(separator, dest, action);
        String[] src = input.split(separator);
        to(src, dest, action);
    }

    private static <E> void to(String[] src, Collection<E> dest, Function<String, ? extends E> action) {
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
