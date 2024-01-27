package com.oozinoz.iterator;

import java.util.Set;

// TODO: 1/26/2024 Iterator Design Pattern - sample Iterating over a Composite
public interface AcycliclyIterable<E> extends Iterable<E> {
    ComponentIterator<E> iterator(Set<E> visited);
}
