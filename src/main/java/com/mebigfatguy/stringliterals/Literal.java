/*
 * stringliterals - A tool for parsing out all string literals used in a code base
 * Copyright 2016-2017 MeBigFatGuy.com
 * Copyright 2016-2017 Dave Brosius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mebigfatguy.stringliterals;

public final class Literal implements Comparable<Literal> {

    private String literal;
    private String className;

    public Literal(String literal, String className) {
        this.literal = literal;
        this.className = className;
    }

    @Override
    public int hashCode() {
        return literal.hashCode() ^ className.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Literal)) {
            return false;
        }

        Literal that = (Literal) o;
        return literal.equals(that.literal) && className.equals(that.className);
    }

    @Override
    public int compareTo(Literal o) {
        if (o == null) {
            return -1;
        }

        int cmp = literal.compareTo(o.literal);
        if (cmp != 0) {
            return cmp;
        }

        return className.compareTo(o.className);
    }

    @Override
    public String toString() {
        return String.format("\"%s\" in %s", literal, className);
    }

}
