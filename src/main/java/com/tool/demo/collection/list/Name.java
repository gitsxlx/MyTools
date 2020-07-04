package com.tool.demo.collection.list;

import java.util.Set;
import java.util.HashSet;

/**
 * Name
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Name {

    private String first, last;

    public Name(String f, String s) {
        this.first = f;
        this.last = s;
    }

    public boolean equals(Object o) {
        Name n = (Name) o;
        return n.first.equals(first) && n.last.equals(last);
    }

    public static void main(String[] args) {
        Set<Name> s = new HashSet<Name>();
        s.add(new Name("Mickey", "Mouse"));
        System.out.println(s.contains(new Name("Mickey", "Mouse")));
    }

}
