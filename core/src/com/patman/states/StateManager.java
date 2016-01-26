package com.patman.states;

import java.util.Stack;

/**
 * Created by Gehad on 25/12/2015.
 */
public class StateManager {
    private static Stack<States> states = new Stack<>();


    public static void pop() {
        states.pop().dispose();
    }

    public static void push(States s) {
        states.push(s);

    }

    public static States peek() {
        return states.peek();

    }

    public static boolean isEmpty() {
        return states.empty();
    }

}
