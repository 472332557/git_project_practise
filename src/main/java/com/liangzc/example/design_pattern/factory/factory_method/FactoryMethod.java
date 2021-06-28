package com.liangzc.example.design_pattern.factory.factory_method;

/**
 * 工厂方法实际是对简单工厂的升级，简单工厂由于工厂类职责过重（所有的对象创建都在一个工厂类中），
 * 当需要变更时，就要重新修改工厂方法，违反开闭原则。
 * 而工厂方法，指定一个工厂接口，而具体要创建的对象，交由实现该接口的工厂对象去负责创建，契合了单一原则，一个工厂负责创建一个对象实例。
 */
public interface FactoryMethod {

    ICourse createCourse();
}
