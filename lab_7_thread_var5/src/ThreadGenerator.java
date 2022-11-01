import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadGenerator {
    private static int thrNum;
    private static char action;
    private static int number;
    static List<BigInteger> listInt;
    public static void main(String[] args) {
        System.out.println("Enter count of threads");
        try(Scanner sc = new Scanner(System.in)){
            thrNum = Integer.parseInt(sc.nextLine());
            System.out.println("Action: ");
            action = sc.nextLine().charAt(0);
            System.out.println("Enter number ");
            number = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        execute();
        System.out.println(getResult());
    }
    private static void execute(){
        List<CalculatorThread> calcList = new ArrayList<>();
        ThreadFactory trF = Executors.defaultThreadFactory();
        ThreadPoolExecutor poolThr = new ThreadPoolExecutor(10,500, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(8), trF);
        for(int i = 0; i<thrNum; i++){
            CalculatorThread cl = new CalculatorThread(number);
            calcList.add(cl);
            poolThr.execute(cl);
        }
        poolThr.shutdown();
        for(var s : calcList){
            System.out.println(s.getBigNumber());
            listInt.add(s.getBigNumber());
        }
    }
    private static BigInteger getResult(){
        BigInteger tmp = BigInteger.valueOf(0);
        switch (action){
            case '+':{
                for(BigInteger s : listInt){
                    tmp = tmp.add(s);
                }
                return tmp;
            }
            case '-':{
                for(BigInteger s : listInt){
                    tmp = tmp.subtract(s);
                }
                return tmp;
            }
            case '*':{
                tmp = BigInteger.valueOf(1);
                for(BigInteger s : listInt){
                    tmp = tmp.multiply(s);
                }
                return tmp;
            }
        }
        return BigInteger.valueOf(0);
    }
}
