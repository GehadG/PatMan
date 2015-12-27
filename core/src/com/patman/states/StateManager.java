package com.patman.states;

import java.util.Stack;

/**
 * Created by Gehad on 25/12/2015.
 */
public  class StateManager {
private static Stack<State> states=new Stack<>();


    public  static void pop(){
        states.pop().dispose();
    }
    public static void push(State s){
        states.push(s);

    }
    public static State peek(){
        return states.peek();

    }
}
