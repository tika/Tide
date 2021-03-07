package one.tika.tide.utils;

import java.util.*;
import java.util.stream.Stream;

public class CollectionUtil {
    public static <K, V> TreeMap<K, V> treeMap(K key1, V value1, Object... objects) {
        TreeMap<K, V> ret = new TreeMap<>();

        ret.put(key1, value1);

        Iterator<Object> iter = Arrays.asList(objects).iterator();
        while (iter.hasNext()) {
            K key = (K) iter.next();
            V value = (V) iter.next();
            ret.put(key, value);
        }

        return ret;
    }


    public static <K, V> Map<K, V> map(K key1, V value1, Object... objects) {
        Map<K, V> ret = new HashMap<>();

        ret.put(key1, value1);

        Iterator<Object> iter = Arrays.asList(objects).iterator();
        while (iter.hasNext()) {
            K key = (K) iter.next();
            V value = (V) iter.next();
            ret.put(key, value);
        }

        return ret;
    }

    public static <E> E getWeightedRandom(Map<E, Double> weights, Random random) {
        E result = null;
        double bestValue = Double.MAX_VALUE;

        for (E element : weights.keySet()) {
            double value = -Math.log(random.nextDouble()) / weights.get(element);

            if (value < bestValue) {
                bestValue = value;
                result = element;
            }
        }

        return result;
    }

    // Requires Java 8
    public static <E> E getWeightedRandom(Stream<Map.Entry<E, Double>> weights, Random random) {
        return weights
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), -Math.log(random.nextDouble()) / e.getValue()))
                .min(Map.Entry.comparingByValue())
                .orElseThrow(IllegalArgumentException::new).getKey();
    }
}
