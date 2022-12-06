package hello.advanced.proxy.concrete;

import hello.advanced.proxy.concrete.code.ConcreteLogic;

public class ConcreteClient {

    private ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public void execute() {
        concreteLogic.operation();
    }
}
