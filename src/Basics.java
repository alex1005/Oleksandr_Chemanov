import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Basics {

    public static List<Integer> getIntegersFromList(List<Object> list) {
        var res = new ArrayList<Integer>(); // creating new list, so the old one will not be changed
        if(list == null) return null;
        for(Object item: list) {
            if(item instanceof Integer)
                res.add((Integer) item);
        }
        return res;
    }

    public static String firstNonRepeatingLetter(String s) {
        var lower = s.toLowerCase();
        for(int i = 0; i < lower.length(); i++) {
            if(lower.indexOf(lower.charAt(i), lower.indexOf(lower.charAt(i))+1) == -1)
                return String.valueOf(s.charAt(i));
        }
        return "";
    }

    public static int digitalRoot(int n) {
        var res = 10;
        while (res >= 10){
            res = 0;
            while(n != 0) {
                res += n % 10;
                n /= 10;
            }
            n = res;
        }
        return res;
    }

    public static int countNumberOfPairs(int[] a, int target, boolean isStream) {
        var count = 0;
        if(isStream){
            count = ((int)Arrays.stream(a)
                    .flatMap(i -> Arrays.stream(a).map(j -> i + j)).filter(i -> i == target).count()
                    - (int)Arrays.stream(a).map(i -> 2*i).filter(i -> i == target).count())/2;
        } else {
            for(int i = 0; i < a.length-1; i++)
                for(int j = i+1; j < a.length; j++)
                    if(a[i]+a[j] == target) count++;
        }

        return count;
    }

    public static String meeting(String s) {
        s = s.toUpperCase();
        var names = s.split(";");
        return Arrays.stream(names).map(str -> str.substring(str.indexOf(":")+1)+", "+ str.substring(0, str.indexOf(":")))
                .sorted().reduce("", (accum, next) -> accum +"("+next+")");
    }

    public static int nextBigger(int n) {
        var num = Integer.toString(n).toCharArray();
        var i = 0;
        for(i = num.length-1; i > 0; i--) {
            if(num[i-1] < num[i]) break;
        }

        if(i == 0) return -1; // Digits are descending

        var minGreaterInd = i;
        for(int j = i+1; j < num.length; j++){
            if(num[j] < num[minGreaterInd] && num[j] > num[i-1]) num[minGreaterInd] = num[j];
        }
        var t = num[i-1];
        num[i-1] = num[minGreaterInd];
        num[minGreaterInd] = t;
        Arrays.sort(num, i, num.length); // Tail need to be ascending
        return Integer.valueOf(new String(num));
    }

    public static String numToIPv4(long num) {
        var res = "";
        for(int i = 0; i < 4; i++) {
            res = "." + (num % 256) + res;
            num /= 256;
        }
        return res.substring(1);
    }

    public static void main(String[] args) {
        System.out.print("Run default tests(enter y) or input mannualy(enter something else)?: ");
        Scanner sc = new Scanner(System.in);
        if(!sc.nextLine().equals("y")) {
            //FIRST TASK
            System.out.print("getIntegersFromList test\nEnter array with integers and strings separeted by space: ");
            var inputArr = sc.nextLine().split(" ");
            var list1Task = new ArrayList<Object>();
            
            for(int i = 0; i < inputArr.length; i++) {
                try{
                    list1Task.add(Integer.parseInt(inputArr[i]));
                } catch(NumberFormatException e) {
                    list1Task.add(inputArr[i].trim());
                }
            }
            System.out.println("Result: " + getIntegersFromList(list1Task)+"\n---------------------------");

            //SECOND TASK
            System.out.print("firstNonRepeatingLetter test\nEnter string: ");
            //sc.nextLine(); //Consumes newline character
            System.out.println("Result: " + firstNonRepeatingLetter(sc.nextLine())+"\n---------------------------");

            //THIRD TASK
            System.out.print("digitalRoot test\nEnter positive integer: ");
            System.out.println("Result: " + digitalRoot(Integer.parseInt(sc.nextLine()))+"\n---------------------------");

            //FOURTH TASK
            System.out.print("countNumberOfPairs test\nEnter integers separeted by space: ");
            inputArr = sc.nextLine().split(" ");
            var list4Task = Arrays.stream(inputArr).mapToInt(i -> Integer.parseInt(i)).toArray();
            System.out.print("Enter target sum: ");
            var target = Integer.parseInt(sc.nextLine());
            System.out.println("Result(for loop): " + countNumberOfPairs(list4Task, target, false));
            System.out.println("Result(stream): " + countNumberOfPairs(list4Task, target, true)+"\n---------------------------");

            //FIFTH TASK
            System.out.print("meeting test\nEnter names in format \'Name1:Surname1;Name2:Surname2;...\': " );
            //sc.nextLine(); //Consumes newline character
            System.out.println("Result: " + meeting(sc.nextLine())+"\n---------------------------");

            //FIRST EXTRA TASK
            System.out.print("nextBigger test\nEnter positive integer: ");
            System.out.println("Result: " + nextBigger(Integer.parseInt(sc.nextLine()))+"\n---------------------------");

            //SECOND EXTRA TASK
            System.out.print("numToIPv4 test\nEnter positive integer: ");
            System.out.println("Result: " + numToIPv4(Integer.parseInt(sc.nextLine()))+"\n---------------------------");
        } else {
            //FIRST TASK
            System.out.println("getIntegersFromList default test: 1, 2, \"a\", \"b\", 0, 15");
            System.out.println("Result: " + getIntegersFromList(Arrays.asList(1,2,"a","b",0,15))+"\n---------------------------");

            //SECOND TASK
            System.out.println("firstNonRepeatingLetter default test: stress");
            System.out.println("Result: " + firstNonRepeatingLetter("stress")+"\n---------------------------");

            //THIRD TASK
            System.out.println("digitalRoot default test: 493193");
            System.out.println("Result: " + digitalRoot(493193)+"\n---------------------------");

            //FOURTH TASK
            System.out.println("countNumberOfPairs default test: 1, 3, 6, 2, 2, 0, 4, 5; 5");
            System.out.println("Result(for loop): " + countNumberOfPairs(new int[]{1, 3, 6, 2, 2, 0, 4, 5}, 5, false));
            System.out.println("Result(stream): " + countNumberOfPairs(new int[]{1, 3, 6, 2, 2, 0, 4, 5}, 5, true)+"\n---------------------------");

            //FIFTH TASK
            System.out.println("meeting default test: Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill");
            System.out.println("Result: " + meeting("Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill")+"\n---------------------------");

            //FIRST EXTRA TASK
            System.out.println("nextBigger default test: 2017");
            System.out.println("Result: " + nextBigger(2017)+"\n---------------------------");

            //SECOND EXTRA TASK
            System.out.println("numToIPv4 default test: 2149583361l");
            System.out.println("Result: " + numToIPv4(2149583361l)+"\n---------------------------");
        }
        sc.close();
    }
}

