package compets.engine.process.visitor;

import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Dog;

public interface AnimalVisitor<T> {
	
	T visit(Dog dog);
	
	T visit(Cat cat);
}
