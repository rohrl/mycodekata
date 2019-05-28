package patterns;

import com.google.common.collect.*;

import java.util.*;

public class VisitorExample {

    public interface Visitor {

        void visit(Car v);

        void visit(Wheel v);

        // add more when more classes in the family
    }

    public interface Visitee {

        void accept(Visitor visitor);
    }

    public static class Car implements Visitee {

        List<Wheel> wheels = ImmutableList.of(new Wheel(1), new Wheel(2), new Wheel(3), new Wheel(4));

        @Override
        public void accept(Visitor visitor) {
            for (Wheel wheel : wheels) {
                wheel.accept(visitor);
            }
            visitor.visit(this);
        }
    }

    public static class Wheel implements Visitee {

        int number;

        public Wheel(int i) {
            number = i;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    // you can create as many visitors as you want
    public static class ToStringBehavior implements Visitor {

        @Override
        public void visit(Car v) {
            System.out.println("Printing car: " + v);
        }

        @Override
        public void visit(Wheel v) {
            System.out.println("Printing wheel number " + v.number + ": " + v);
        }
    }

    public static void main(String[] args) {

        final Car car = new Car();

        final ToStringBehavior toStringBehavior = new ToStringBehavior();

        car.accept(toStringBehavior);
    }
}
