package Ei_Study.Ex1.structural_design;

// Coffee Interface
interface Coffee {
    double cost();
    String description();
}

// Concrete Component (Simple Coffee)
class SimpleCoffee implements Coffee {
    @Override
    public double cost() {
        return 2.0;
    }

    @Override
    public String description() {
        return "Simple Coffee";
    }
}

// Abstract Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }

    @Override
    public String description() {
        return decoratedCoffee.description();
    }
}

// Concrete Decorator (Milk)
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 0.5;
    }

    @Override
    public String description() {
        return super.description() + ", Milk";
    }
}

// Concrete Decorator (Sugar)
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 0.2;
    }

    @Override
    public String description() {
        return super.description() + ", Sugar";
    }
}


public class DecoratorPatternTest {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.description() + " Cost: " + coffee.cost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.description() + " Cost: " + coffee.cost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.description() + " Cost: " + coffee.cost());
    }
}
