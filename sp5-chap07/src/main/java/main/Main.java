package main;


import factorial.ExeTimeCalculator;
import factorial.ImpeCalculator;
import factorial.RecCalculator;

public class Main {

    public static void main(String[] args) throws Exception {
        ExeTimeCalculator cal1=new ExeTimeCalculator(new ImpeCalculator());
        ExeTimeCalculator cal2=new ExeTimeCalculator(new RecCalculator());

        System.out.println(cal1.factorial(5));
        System.out.println(cal2.factorial(5));
    }
}
