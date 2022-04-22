package com.ftm.vcp.spel;

import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelDemo {

    public static void main(String[] args) {
        final var parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("'Hello world'").getValue());
        System.out.println(parser.parseExpression("'Hello world'.toLowerCase()").getValue());
        System.out.println(parser.parseExpression("'Hello world'.bytes.length").getValue());
        System.out.println(parser.parseExpression("T(String).join(' ', 'Hello', 'world')").getValue());
        final var foo = new Foo("John Doe");
        final var objectExpression = parser.parseExpression("name");
        System.out.println(objectExpression.getValue(foo, String.class));
    }
}
