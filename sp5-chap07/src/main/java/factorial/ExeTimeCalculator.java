package factorial;

/**
 *
 * "Runtime을 측정하는 Proxy 객체"
 *
 *   1) 중복 코드 제거 (ImpeCalculator 및 RecCalculator 객체 내 시간 측정 기능 코드)
 *   2) 핵심 기능(팩토리얼 계산) 코드를 변경하지 않고, 부가 기능(시간 측정) 코드를 변경 가능함.
 *
 */

public class ExeTimeCalculator implements Calculator{

    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate){
        this.delegate=delegate;
    }

    @Override
    public long factorial(long num){
        long start=System.nanoTime();
        long result=delegate.factorial(num);
        long end=System.nanoTime();

        System.out.printf("%s.factorial(%d) 실행시간 = %d\n", delegate.getClass().getSimpleName(), num, (end-start));

        return result;
    }
}
