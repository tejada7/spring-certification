## Some concepts
- Aspect: a modularization of concerns that cuts across multiple objects
- Join point: a point during execution of a program, e.g. a method execution
- Advice: an action taken by an aspect at a particular join point
- Pointcut: a set of join points specifying exactly where to apply an advice
- Target object: an object being advised by one or more aspects
- Proxy: an object the AOP framework creates to implement the aspect contracts
- Code tangling (~_código mezclado_): coupling of concerns, AOP aims to avoid code tangling
- Code scattering (~_código diseminado_): the same concern spread across modules. AOP aims to eliminate code scattering 

## Main annotations
- `@Before` advice: advice to be run before a join point
- `@AfterReturning` advice: advice to be run after a join point completes normally
- `@AfterThrowing` advice: advice to be run after a join point throws an exception
- `@After` advice: advice to be run after a join point regardless of the way it exits
- `@Around` advice: advice that surrounds a join point


If a class being advised implements an interface, Spring will create a `jdk dynamic proxy`, otherwise, a `cglib` one. 

## Other Common Pointcut Designators
- `within`: limits the matching of join points to a certain type.
- `target`: limits the matching of join points to a specific type.
- `bean`: limits the matching of join points to a particular, or a set of Spring beans.
