## Some concepts
- Aspect: A modularization of concerns that cuts across multiple objects
- Join point: A point during execution of a program, e.g. a method execution
- Advice: An action taken by an aspect at a particular join point
- Pointcut: A set of join points specifying exactly where to apply an advice
- Target object: An object being advised by one or more aspects
- Proxy: An object the AOP framework creates to implement the aspect contracts

## Main annotations
- `@Before` advice: Advice to be run before a join point
- `@AfterReturning` advice: Advice to be run after a join point completes normally
- `@AfterThrowing` advice: Advice to be run after a join point throws an exception
- `@After` advice: Advice to be run after a join point regardless of the way it exits
- `@Around` advice: Advice that surrounds a join point


If a class being advised implements an interface, Spring will create a jdk dynamic proxy, otherwise, a cglib. 

## Other Common Pointcut Designators
- `within`: Limits the matching of join points to a certain type.
- `target`: Limits the matching of join points to a specific type.
- `bean`: Limits the matching of join points to a particular, or a set of Spring beans.
