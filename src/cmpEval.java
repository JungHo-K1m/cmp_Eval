import java.util.Scanner;
import java.util.Stack;

public class cmpEval {
    public static String eval(final String str){
        char[] token = str.toCharArray();   //문자열을 쪼개자
        String oper = "<>=!";

        Stack<Integer> values = new Stack<Integer>();   //숫자 담는 스택
        Stack<String> operations = new Stack<String>(); //연산자 담는 스택
        Stack<Boolean> bool = new Stack<Boolean>(); //비교연산 결과 담는 스택

        //문자열 길이만큼 반복
        for(int i=0; i < str.length(); i++){
            if(token[i] == ' ') continue;


            //숫자를 스택에 저장
            if(Character.isDigit(token[i])){
                StringBuffer valueBuffer = new StringBuffer();

                while(i<token.length && Character.isDigit(token[i]))
                    valueBuffer.append(token[i++]);

                values.push(Integer.parseInt((valueBuffer.toString())));

                i--;
            }


            //비교연산자 스택에 저장
            else if(oper.indexOf(token[i]) != -1){
                StringBuffer operBuffer = new StringBuffer();

                while(i<token.length && !Character.isDigit(token[i]))
                    operBuffer.append(token[i++]);

                operations.push(String.valueOf(operBuffer));

                i--;
            }
        }

        //최종 연산
        while(!operations.empty())
            bool.push(cmpOp(operations.pop(),values.pop(),values.pop()));


        return String.valueOf(bool.pop());
    }


    public static boolean cmpOp(String op, int b, int a){
        switch (op){
            case "==":
                return a == b;
            case "!=":
                return a != b;
            case "<":
                return a < b;
            case "<=":
                return a <= b;
            case ">":
                return a > b;
            case ">=":
                return a >= b;
        }
        return true;
    }


    public static void main(String[] args){
        System.out.print("입력 : ");
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();
        System.out.println("결과 : " + eval(str));
    }
}
