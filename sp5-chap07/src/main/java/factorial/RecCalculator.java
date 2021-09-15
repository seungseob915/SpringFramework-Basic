package factorial;

// 핵심기능(팩토리얼 계산)을 실행하는 객체
public class RecCalculator implements Calculator{

    @Override
    public long factorial(long num){

        long start=System.currentTimeMillis();

        try {
            if (num == 0)
                return 1;
            else
                return num * factorial(num - 1);
        } finally {
            long end=System.currentTimeMillis();
//            System.out.printf("RecCalculator.factorial(%d) 실행시간 = %d\n", num, (end-start));   // ExeTimeCalculator에서 시간 계산
        }
    }
}
