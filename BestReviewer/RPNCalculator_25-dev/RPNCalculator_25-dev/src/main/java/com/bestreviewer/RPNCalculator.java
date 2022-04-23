package com.bestreviewer;

import java.util.*;
import java.util.function.BiFunction;

public class RPNCalculator {
    public RPNCalculator() {
    }

    private static Map<String, BiFunction<Integer, Integer, Integer>> operators = new HashMap<>();
    static {
        operators.put("+", (num1, num2) -> num2 + num1);
        operators.put("-", (num1, num2) -> num2 - num1);
        operators.put("*", (num1, num2) -> num2 * num1);
        operators.put("/", (num1, num2) -> num2 / num1);
        operators.put("SQRT", (num1, num2) -> (int)Math.sqrt(num1));
        operators.put("MAX", (num1, num2) -> Math.max(num1, num2));
    }

    public int calculate(String input) {
        int result = 0;
        Stack<Integer> calcStack = new Stack<Integer>();

        String[] splitInput = input.split(" ");
        for(String token : splitInput) {
            if(token.chars().allMatch(Character::isDigit)) {
                calcStack.add(Integer.parseInt(token));
            }
            else {
                if(token.equals("SQRT")) {
                    int num1 = calcStack.pop();
                    result = operators.get(token).apply(num1, 0);
                }
                else if(token.equals("MAX")) {
                    while(true) {
                        if(calcStack.isEmpty()) { break; }
                        int num1 = calcStack.pop();
                        if(calcStack.isEmpty()) { break; }
                        int num2 = calcStack.pop();
                        result = operators.get(token).apply(num1, num2);
                        calcStack.add(result);
                    }
                    return result;
                }
                else {
                    int num1 = calcStack.pop();
                    int num2 = calcStack.pop();
                    result = operators.get(token).apply(num1, num2);
                }
                calcStack.add(result);
            }
        }
        return result;
    }
}
