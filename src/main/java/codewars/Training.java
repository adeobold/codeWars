package codewars;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Training {

    public static void main(String[] args) {

        Boolean[] array1 = {true, true, true, false,
                true, true, true, null,
                true, false, true, true,
                true, false, false, true,
                true, true, true, true,
                false, false, true, true};

        //System.out.println(Arrays.toString(productFib(1)));
        System.out.println(fibonacci(3));

    }


    public static long[] productFib(long prod) {
        //https://www.codewars.com/kata/5541f58a944b85ce6d00006a/train/java
        long[] result = new long[3];
        long n = 0;

        while (fibonacci(n) * fibonacci(n + 1) <= prod) {
            if (fibonacci(n) * fibonacci(n + 1) == prod) {
                result[0] = fibonacci(n);
                result[1] = fibonacci(n + 1);
            } else {
                result[0] = fibonacci(n + 1);
                result[1] = fibonacci(n + 2);
            }

            n++;
        }

        if (result[0] * result[1] == prod) {
            result[2] = 1;
        } else {
            result[2] = 0;
        }

        return result;
    }
    public static long fibonacci(long n) {

        long result = 0;

        long elemMinus2 = 0;
        long elemMinus1 = 1;
        long elemCurrent = 0;

        if (n == 0) {
            elemCurrent = 0;
        } else if (n == 1) {
            elemCurrent = 1;
        }

        for (int i = 2; i <= n; i++) {

            elemCurrent = elemMinus2 + elemMinus1;

            elemMinus2 = elemMinus1;
            elemMinus1 = elemCurrent;

        }

        return elemCurrent;

//        if (n == 0) {
//            return 0;
//        } else if (n == 1) {
//            return 1;
//        } else {
//            return fibonacci(n - 1) + fibonacci(n - 2);
//        }
    }


    public static String reverseWords(final String original) {
        //Complete the function that accepts a string parameter, and reverses each word in the string. All spaces in the string should be retained.

        //String[] array = original.split(" ");
        //
        //    if(array.length == 0)
        //        return original;
        //
        //
        //    int i = 0;
        //    for(String string : array){
        //        array[i] = new StringBuilder(string).reverse().toString();
        //        i++;
        //    }
        //
        //    return String.join(" ",array);

        String result = "";
        String word = "";

        for (char ch : original.toCharArray()) {

            if (ch == ' ') {
                if (word != "") {
                    StringBuilder sb = new StringBuilder(word);
                    result += sb.reverse();
                    word = "";
                }
                result += ch;
            } else {
                word += ch;
            }

        }

        if (word != "") {
            StringBuilder sb = new StringBuilder(word);
            result += sb.reverse();
        }

        return result;

    }

    public static int[] sumParts(int[] ls) {

        int[] result = new int[ls.length + 1];

        int sum = 0;
        for (int i = ls.length - 1; i >= 0; i--) {
            sum += ls[i];
            result[i] = sum;
        }

        return result;

    }

    //John and his wife Ann have decided to go to Codewars. On the first day Ann will do one kata and John - he wants to know how it is working - 0 kata.
    //
    //Let us call a(n) - and j(n) - the number of katas done by Ann - and John - at day n. We have a(0) = 1 and in the same manner j(0) = 0.
    //
    //They have chosen the following rules:
    //
    //On day n the number of katas done by Ann should be n minus the number of katas done by John at day t, t being equal to the number of katas done by Ann herself at day n - 1
    //
    //On day n the number of katas done by John should be n minus the number of katas done by Ann at day t, t being equal to the number of katas done by John himself at day n - 1
    //
    //Whoops! I think they need to lay out a little clearer exactly what there're getting themselves into!
    //
    //Could you write:
    //functions ann(n) and john(n) that return the list of the number of katas Ann/John does on the first n days;
    //functions sum_ann(n) and sum_john(n) that return the total number of katas done by Ann/John on the first n days
//    public static Long j(long n) {
//        if (n == 0){
//            return 0l;
//        }
//        return n - a(j(n - 1));
//    }
//
//    public static Long a(long n) {
//        if (n == 0){
//            return 1l;
//        }
//        return n - j(a(n - 1));
//    }
    public static List<Long> aj(long n, String whoIs) {

        List<Long> a = new ArrayList<>();
        List<Long> j = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (i == 0) {
                a.add(1l);
                j.add(0l);
            } else {

                j.add(i - a.get(Math.toIntExact(j.get(i - 1))));
                a.add(i - j.get(Math.toIntExact(a.get(i - 1))));

            }

        }

        if (whoIs == "a") {
            return a;
        } else {
            return j;
        }

    }

    public static List<Long> john(long n) {

//        List<Long> result = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            result.add(j(i));
//        }
//
//        return result;

        return aj(n, "j");

    }

    public static List<Long> ann(long n) {

//        List<Long> result = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            result.add(a(i));
//        }
//
//        return result;

        return aj(n, "a");

    }

    public static long sumJohn(long n) {
        return john(n).stream().reduce(0l, (a, b) -> a + b);
    }

    public static long sumAnn(long n) {
        return ann(n).stream().reduce(0l, (a, b) -> a + b);
    }


    public static String createPhoneNumber(int[] numbers) {
        // Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.
        //
        //Example
        //Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}) // => returns "(123) 456-7890"
        //The returned format must be correct in order to complete this challenge.
        //Don't forget the space after the closing parentheses!

        return "(" + numbers[0] + numbers[1] + numbers[2] + ") " +
                numbers[3] + numbers[4] + numbers[5] + "-" +
                numbers[6] + numbers[7] + numbers[8] + numbers[9];

    }

    public static int bouncingBall(double h, double bounce, double window) {

        if (h <= 0) return -1;
        if (bounce <= 0 || bounce >= 1) return -1;
        if (window >= h) return -1;

        return ((int) (Math.log(window / h) / Math.log(bounce)) * 2 + 1);

    }

    public static String whoLikesIt(String... names) {

//        return switch (names.length) {
//            case 0 -> "no one likes this";
//            case 1 -> names[0] + " likes this";
//            case 2 -> names[0] + " and " + names[1] + " like this";
//            case 3 -> names[0] + ", " + names[1] + " and " + names[2] + " like this";
//            default -> names[0] + ", " + names[1] + " and " + (names.length - 2) + " others like this";
//        };

        switch (names.length) {
            case 0:
                return "no one likes this";
            case 1:
                return names[0] + " likes this";
            case 2:
                return names[0] + " and " + names[1] + " like this";
            case 3:
                return names[0] + ", " + names[1] + " and " + names[2] + " like this";
            default:
                return names[0] + ", " + names[1] + " and " + (names.length - 2) + " others like this";
        }

    }

    public static String high(String s) {

        String highestWord = "";
        Integer highestWordCodeSum = 0;

        for (String word : s.split(" ")) {
            Integer wordCodeSum = 0;
            for (Character ch : word.toCharArray())
                wordCodeSum += (int) ch - 96;
            if (wordCodeSum > highestWordCodeSum) {
                highestWordCodeSum = wordCodeSum;
                highestWord = word;
            }
        }

        return highestWord;
    }

    public static boolean getXO(String str) {

        int xCount = 0;
        int oCount = 0;

        for (Character ch : str.toCharArray()) {
            if (Pattern.matches("[oO]", ch.toString())) oCount++;
            if (Pattern.matches("[xX]", ch.toString())) xCount++;
        }

        return xCount == oCount;

    }

    public static int[] deleteNth(int[] elements, int maxOccurrences) {

        for (int elem : Arrays.stream(elements).distinct().toArray()) {
            int elemOccurrence = 0;
            for (int i = 0; i < elements.length; i++)
                if (elem == elements[i]) {
                    if (elemOccurrence >= maxOccurrences) elements[i] = Integer.MIN_VALUE;
                    elemOccurrence++;
                }
        }

        return Arrays.stream(elements).filter(value -> value != Integer.MIN_VALUE).toArray();

//        Task
//        Given a list lst and a number N, create a new list that contains each number of lst at most N times without reordering. For example if N = 2, and the input is [1,2,3,1,2,1,2,3], you take [1,2,3,1,2], drop the next [1,2] since this would lead to 1 and 2 being in the result 3 times, and then take 3, which leads to [1,2,3,1,2,3].
//
//        Example
//        EnoughIsEnough.deleteNth(new int[] {20,37,20,21}, 1) // return [20,37,21]
//        EnoughIsEnough.deleteNth(new int[] {1,1,3,3,7,2,2,2,2}, 3) // return [1, 1, 3, 3, 7, 2, 2, 2]

    }

    public static String expandedForm(int num) {

        String result = "";

        char[] arrayOfDigits = String.valueOf(num).toCharArray();

        for (int i = arrayOfDigits.length - 1; i >= 0; i--) {
            if (arrayOfDigits[i] != '0') {
                if (result != "") result = " + " + result;
                for (int j = 0; j < arrayOfDigits.length - 1 - i; j++)
                    result = new StringBuilder().append('0').toString() + result;
                result = arrayOfDigits[i] + result;

            }
        }

        return result;

//        Write Number in Expanded Form
//        You will be given a number and you will need to return it as a string in Expanded Form. For example:
//
//        Kata.expandedForm(12); # Should return "10 + 2"
//        Kata.expandedForm(42); # Should return "40 + 2"
//        Kata.expandedForm(70304); # Should return "70000 + 300 + 4"
//        NOTE: All numbers will be whole numbers greater than 0.
    }

    public static long findNextSquare(long sq) {

        return Math.sqrt(sq) % 1 != 0 ? -1 : (long) Math.pow(Math.sqrt(sq) + 1, 2);

    }

    public static double findUniq(double arr[]) {

//        Arrays.sort(arr);
//        return arr[0] == arr[1] ? arr[arr.length-1]:arr[0];

        double result = 0;

        double max = Arrays.stream(arr).max().getAsDouble();
        double min = Arrays.stream(arr).min().getAsDouble();

        double[] firstThreeElements = new double[]{arr[0], arr[1], arr[2]};

        if (Arrays.stream(firstThreeElements).filter(value -> value == max).toArray().length > 1)
            result = min;
        else
            result = max;

        return result;


//        There is an array with some numbers. All numbers are equal except for one. Try to find it!
//
//        Kata.findUniq(new double[]{ 1, 1, 1, 2, 1, 1 }); // => 2
//        Kata.findUniq(new double[]{ 0, 0, 0.55, 0, 0 }); // => 0.55
//        It’s guaranteed that array contains at least 3 numbers.
//
//        The tests contain some very huge arrays, so think about performance.


    }

    public static int opposite(int number) {
        return -number;
    }

    public static String longestConsec(String @NotNull [] strarr, int k) {

        String result = "";

        for (int i = 0; i <= strarr.length - k; i++) {
            String candidate = "";
            for (int j = i; j < i + k; j++) {
                candidate += strarr[j];
            }
            if (candidate.length() > result.length()) result = candidate;
        }

        return result;


//        You are given an array(list) strarr of strings and an integer k. Your task is to return the first longest string consisting of k consecutive strings taken in the array.
//
//        Examples:
//        strarr = ["tree", "foling", "trashy", "blue", "abcdef", "uvwxyz"], k = 2
//
//        Concatenate the consecutive strings of strarr by 2, we get:
//
//        treefoling   (length 10)  concatenation of strarr[0] and strarr[1]
//        folingtrashy ("      12)  concatenation of strarr[1] and strarr[2]
//                trashyblue   ("      10)  concatenation of strarr[2] and strarr[3]
//                        blueabcdef   ("      10)  concatenation of strarr[3] and strarr[4]
//                                abcdefuvwxyz ("      12)  concatenation of strarr[4] and strarr[5]
//
//                                        Two strings are the longest: "folingtrashy" and "abcdefuvwxyz".
//                The first that came is "folingtrashy" so
//        longest_consec(strarr, 2) should return "folingtrashy".

    }

    public static int getCount(@NotNull String str) {

//        Return the number (count) of vowels in the given string.
//
//        We will consider a, e, i, o, u as vowels for this Kata (but not y).
//
//        The input string will only consist of lower case letters and/or spaces.

        int vowelsCount = 0;

        for (Character ch : str.toCharArray())
            if (Pattern.matches("[aeiouAEIOU]", ch.toString()))
                vowelsCount++;

        return vowelsCount;

    }

    public static String solution(String str) {

//        Complete the solution so that it reverses the string passed into it.
//
//        'world'  =>  'dlrow'
//        'word'   =>  'drow'

        return new StringBuilder(str).reverse().toString();
    }

    public static String oddOrEven(int[] array) {

//        Task:
//        Given a list of integers, determine whether the sum of its elements is odd or even.
//
//        Give your answer as a string matching "odd" or "even".
//
//                If the input array is empty consider it as: [0] (array with a zero).
//
//        Examples:
//        Input: [0]
//        Output: "even"
//
//        Input: [0, 1, 4]
//        Output: "odd"
//
//        Input: [0, -1, -5]
//        Output: "even"

        return (Arrays.stream(array).sum() % 2 != 0) ? "odd" : "even";

    }

    public static String numberToString(int num) {
        // Return a string of the number here!
        return Integer.toString(num);
    }

    private static String sumOfSeries(int n) {

        double result = 0;

        for (double i = 1; i <= n; i++)
            result += 1 / (i * 2 + i - 2);

        return String.format("%.2f", result);

    }

    public static int Past(int h, int m, int s) {

        if (h < 0 || h > 23) return -1;
        if (m < 0 || m > 59) return -1;
        if (s < 0 || s > 59) return -1;

        LocalDateTime midNight = LocalDate.now().atTime(0, 0, 0);
        LocalDateTime now = LocalDate.now().atTime(h, m, s);

        return (int) Duration.between(midNight, now).getSeconds() * 1000;

    }

    public static int Liters(double time) {

        //Your code goes here! Hint: You should change that -1
        return (int) (time * 0.5);

    }

    public static int century(int number) {
        return (number - 1) / 100 + 1;
    }

    public static int squareSum(int[] n) {
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            result += n[i] * n[i];
        }
        return result;
    }

    public static List filterList(final List list) {

        ArrayList<Object> result = new ArrayList<Object>();

        for (int i = 0; i < list.size(); i++)

            if (list.toArray()[i] instanceof Integer)
                result.add(Integer.parseInt(list.toArray()[i].toString()));

        return Arrays.asList(result.toArray());
    }

    public static String accumulation(String s) {

        String result = "";

        for (int i = 0; i < s.toCharArray().length; i++) {
            if (result != "") result += "-";
            for (int j = 0; j <= i; j++) {
                if (j == 0) result += s.toUpperCase().toCharArray()[i];
                else result += s.toLowerCase().toCharArray()[i];
            }
        }

        return result;
    }

    public static double find_average(int[] array) {

        return array.length != 0 ? Arrays.stream(array).average().getAsDouble() : 0;

    }

    public static int[] map(int[] arr) {
        return Arrays.stream(arr).map(i -> i * 2).toArray();
    }

    public static int[] arrayDiff(int[] a, int[] b) {

//        List<Integer> listA = Arrays.stream(a).boxed().collect(Collectors.toList());
//        List<Integer> listB = Arrays.stream(b).boxed().collect(Collectors.toList());
//        listA.removeAll(listB);
//        return listA.stream().mapToInt(e -> e).toArray();

        ArrayList<Integer> result = new ArrayList<Integer>();
        //List<Integer> listB = Arrays.stream(b).boxed().toList();
        List<Integer> listB = Arrays.stream(b).boxed().collect(Collectors.toList());

        for (int num : a)
            if (!Arrays.stream(b).boxed().toList().contains(num))
                result.add(num);

        a = result.stream().mapToInt(Integer::intValue).toArray();

        return a;
    }

    public static String disemvowel(String str) {

        String result = "";

        for (Character ch : str.toCharArray()) {
            if (!Pattern.matches("[aeiouAEIOU]", ch.toString()))
                result += ch;
        }

        return result;

    }

    public static int countSheeps(Boolean[] arrayOfSheeps) {

        int result = 0;

        for (int i = 0; i < arrayOfSheeps.length; i++)
            if (arrayOfSheeps[i] != null && arrayOfSheeps[i]) result++;

        return result;

    }

    public static int makeNegative(final int x) {

        return x > 0 ? x * -1 : x;

    }

    public static int rowSumOddNumbers(int n) {

        //        Given the triangle of consecutive odd numbers:
        //
        //        1
        //        3     5
        //        7     9    11
        //        13    15    17    19
        //        21    23    25    27    29
        //...
        //        Calculate the sum of the numbers in the nth row of this triangle (starting at index 1) e.g.: (Input --> Output)
        //
        //        1 -->  1
        //        2 --> 3 + 5 = 8

//        int result = 0;
//        int start = n * (n - 1) + 1;
//
//        for (int i = 0; i < n; i++)
//            result += start + 2*i;
//
//        return result;

        return n * n * n;

    }

    public static int sum(int[] arr) {

//        You get an array of numbers, return the sum of all of the positives ones.
//
//                Example [1,-4,7,12] => 1 + 7 + 12 = 20
//
//        Note: if there is nothing to sum, the sum is default to 0.


        return Arrays.stream(arr).filter(value -> value > 0).sum();


    }

    public static String toJadenCase(String phrase) {

        String result = null;

        if (phrase != null && (phrase != "")) {

            result = "";

            String[] splitedPhrase = phrase.split(" ");

            for (String str :
                    splitedPhrase) {
                str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
                result += str + " ";
            }

            result = result.trim();

        }

        return result;
    }

    public static String encode(String word) {

//        The goal of this exercise is to convert a string to a new string where each character in the new string is "(" if that character appears only once in the original string, or ")" if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.
//
//                Examples
//        "din"      =>  "((("
//        "recede"   =>  "()()()"
//        "Success"  =>  ")())())"
//        "(( @"     =>  "))(("
//        Notes
//        Assertion messages may be unclear about what they display in some languages. If you read "...It Should encode XXX", the "XXX" is the expected result, not the input!

        String result = "";

        word = word.toLowerCase();

        for (int i = 0; i < word.toCharArray().length; i++) {

            String cuttedString = word.substring(0, i) + word.substring(i + 1);

            if (cuttedString.indexOf(word.toCharArray()[i]) >= 0)
                result += ")";
            else
                result += "(";

        }

        return result;
    }

    public static int duplicateCount(String text) {

//        Count the number of Duplicates
//        Write a function that will return the count of distinct case-insensitive alphabetic characters and numeric digits that occur more than once in the input string. The input string can be assumed to contain only alphabets (both uppercase and lowercase) and numeric digits.
//
//                Example
//        "abcde" -> 0 # no characters repeats more than once
//        "aabbcde" -> 2 # 'a' and 'b'
//        "aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
//        "indivisibility" -> 1 # 'i' occurs six times
//        "Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
//        "aA11" -> 2 # 'a' and '1'
//        "ABBA" -> 2 # 'A' and 'B' each occur twice


        Integer result = 0;

        text = text.toLowerCase();

        for (int i = 0; i < text.toCharArray().length; i++) {

            if (text.toCharArray()[i] != '-' && text.indexOf(text.toCharArray()[i]) != text.lastIndexOf(text.toCharArray()[i]))
                result += 1;

            text = text.replace(text.toCharArray()[i], '-');

        }

        return result;

    }

    public static Integer basicMath(String op, int v1, int v2) {

//        Description:
//        Your task is to create a function that does four basic mathematical operations.
//
//            The function should take three arguments - operation(string/char), value1(number), value2(number).
//            The function should return result of numbers after applying the chosen operation.
//
//            Examples(Operator, value1, value2) --> output
//            ('+', 4, 7) --> 11
//        ('-', 15, 18) --> -3
//        ('*', 5, 5) --> 25
//        ('/', 49, 7) --> 7

//        switch (op) {
//            case "-":
//                return v1 - v2;
//            case "+":
//                return v1 + v2;
//            case "*":
//                return v1 * v2;
//            case "/": {
//                if (v2 == 0)
//                    throw new IllegalArgumentException("Division by zero");
//                return v1 / v2;
//            }
//            default:
//                throw new IllegalArgumentException("Unknown operation: " + op);
//        }

        Integer result = 0;

        switch (op) {
            case ("+"):
                result = v1 + v2;
                break;
            case ("-"):
                result = v1 - v2;
                break;
            case ("*"):
                result = v1 * v2;
                break;
            case ("/"):
                result = v2 != 0 ? v1 / v2 : 0;
                break;
        }

        return result;
    }

}
