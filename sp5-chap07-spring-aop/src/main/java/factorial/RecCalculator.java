package factorial;

// 핵심기능(팩토리얼 계산)을 실행하는 객체
public class RecCalculator implements Calculator {

    @Override
    public long factorial(long num) {

        if (num == 0)
            return 1;
        else
            return num * factorial(num - 1);
    }
}
