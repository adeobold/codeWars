package codewars;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
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

        //System.out.println(Arrays.toString(tribonacci(new double []{1,1,1},10)));
        //System.out.println(sequence(new int[]{-2, 1, 1, 4, -1, 2, 1, -5, 4}));
//        System.out.println(toRoman(1666));
//        System.out.println(fromRoman("MCMXC"));

        //System.out.println(dblLinear(50));

        //System.out.println(determinant(new int[][]{{2, 5, 3}, {1, -2, -1}, {1, 3, 4}}));

        /*String LIPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sa"
                + "gittis dolor mauris, at elementum ligula tempor eget. In quis rhoncus nunc, at aliquet orci. Fusc"
                + "e at dolor sit amet felis suscipit tristique. Nam a imperdiet tellus. Nulla eu vestibulum urna. V"
                + "ivamus tincidunt suscipit enim, nec ultrices nisi volutpat ac. Maecenas sit amet lacinia arcu, no"
                + "n dictum justo. Donec sed quam vel risus faucibus euismod. Suspendisse rhoncus rhoncus felis at f"
                + "ermentum. Donec lorem magna, ultricies a nunc sit amet, blandit fringilla nunc. In vestibulum vel"
                + "it ac felis rhoncus pellentesque. Mauris at tellus enim. Aliquam eleifend tempus dapibus. Pellent"
                + "esque commodo, nisi sit amet hendrerit fringilla, ante odio porta lacus, ut elementum justo nulla"
                + " et dolor.";


        System.out.println(justify(ttt, 14));
        */

        //System.out.println(rangeExtraction(new int[] {-6,-3,-2,-1,0,1,3,4,5,7,8,9,10,11,14,15,17,18,19,20}));

        //System.out.println(formatDuration(120));

//        int[][] sudoku = {
//                {5, 3, 4, 6, 7, 8, 9, 1, 2},
//                {6, 7, 2, 1, 9, 5, 3, 4, 8},
//                {1, 9, 8, 3, 4, 2, 5, 6, 7},
//                {8, 5, 9, 7, 6, 1, 4, 2, 3},
//                {4, 2, 6, 8, 5, 3, 7, 9, 1},
//                {7, 1, 3, 9, 2, 4, 8, 5, 6},
//                {9, 6, 1, 5, 3, 7, 2, 8, 4},
//                {2, 8, 7, 4, 1, 9, 6, 3, 5},
//                {3, 4, 5, 2, 8, 6, 1, 7, 9}
//        };
//
//        System.out.println(check(sudoku));

        //System.out.println(countOnes(12,29));

        System.out.println(stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}));


    }

    public static String stripComments(String text, String[] commentSymbols) {

        String result = "";
        String[] arr = text.split("\n");

        for (int i = 0; i < arr.length; i++) {

            for (String c : commentSymbols) {

                if (arr[i].length() > 0 && arr[i].contains(c)) {
                    arr[i] = arr[i].substring(0,arr[i].indexOf(c));
                }

            }

            result += arr[i].replaceAll("\\s+$", "");

            if (i < arr.length - 1) result += "\n";

        }

        return result;

    }


    public static BigInteger countOnes(long left, long right) {

        BigInteger result = BigInteger.valueOf(0);

        int digitsCount = Long.toBinaryString(left).length();
        for (long i = left; i <= right; i++) {
            if (digitsCount < Long.toBinaryString(i).length()) {
                break;
            }
            ;
            result = result.add(BigInteger.valueOf(Long.bitCount(i)));
        }

        if (Long.toBinaryString(left).length() != Long.toBinaryString(right).length()) {
            digitsCount = Long.toBinaryString(right).length();
            for (long i = right; i >= left; i--) {
                if (digitsCount > Long.toBinaryString(i).length()) {
                    break;
                }
                ;
                result = result.add(BigInteger.valueOf(Long.bitCount(i)));
            }
        }

        for (int i = Long.toBinaryString(left).length() + 1; i <= Long.toBinaryString(right).length() - 1; i++) {
            result = result.add(BigInteger.valueOf((long) Math.pow(2, i)));
        }

        return result;


//        long result = 0l;
//        System.out.println(Long.toBinaryString(left));
//        System.out.println(Long.toBinaryString(right));
//        for (long i = left; i <= right; i++) {
//            System.out.println(Long.toBinaryString(i));
//            result += Long.bitCount(i);
//        }
//        return BigInteger.valueOf(result);
    }


    public static boolean check(int[][] sudoku) {

        for (int i = 0; i < sudoku.length; i++) {

            int[] horizontalLine = sudoku[i];
            if (Arrays.stream(horizontalLine).distinct().count() != 9) return false;
            if (Arrays.stream(horizontalLine).anyMatch(value -> value == 0)) return false;

            int[] verticalLine = new int[sudoku.length];
            for (int j = 0; j < sudoku.length; j++) {
                verticalLine[j] = sudoku[i][j];
            }
            if (Arrays.stream(verticalLine).distinct().count() != 9) return false;
            if (Arrays.stream(verticalLine).anyMatch(value -> value == 0)) return false;

        }

        int[] subMatrix = new int[9];

        for (int i = 0; i < sudoku.length - 3; i += 3) {
            for (int j = 0; j < sudoku.length - 3; j += 3) {

                subMatrix[0] = sudoku[i][j];
                subMatrix[1] = sudoku[i][j + 1];
                subMatrix[2] = sudoku[i][j + 2];
                subMatrix[3] = sudoku[i + 1][j];
                subMatrix[4] = sudoku[i + 1][j + 1];
                subMatrix[5] = sudoku[i + 1][j + 2];
                subMatrix[6] = sudoku[i + 2][j];
                subMatrix[7] = sudoku[i + 2][j + 1];
                subMatrix[8] = sudoku[i + 2][j + 2];

                if (Arrays.stream(subMatrix).distinct().count() != 9) return false;

            }
        }

        return true;


    }


    public static String formatDuration(int seconds) {

        String result = "";

        int years = seconds / 31536000;
        int days = (seconds - years * 31536000) / 86400;
        int hours = (seconds - years * 31536000 - days * 86400) / 3600;
        int minutes = (seconds - years * 31536000 - days * 86400 - hours * 3600) / 60;
        seconds = seconds - years * 31536000 - days * 86400 - hours * 3600 - minutes * 60;

        if (years > 0) result += years + " year";
        if (years > 1) result += "s";

        if (result != "" && days != 0) result += ", ";
        if (days > 0) result += days + " day";
        if (days > 1) result += "s";

        if (result != "" && hours != 0) result += ", ";
        if (hours > 0) result += hours + " hour";
        if (hours > 1) result += "s";

        if (result != "" && minutes != 0) result += ", ";
        if (minutes > 0) result += minutes + " minute";
        if (minutes > 1) result += "s";

        if (result != "" && seconds != 0) result += ", ";
        if (seconds > 0) result += seconds + " second";
        if (seconds > 1) result += "s";

        if (result.contains(",")) {
            result = result.substring(0, result.lastIndexOf(",")) + " and" + result.substring(result.lastIndexOf(",") + 1);
        }

        return result;

    }

    public static String rangeExtraction(int[] arr) {

        String result = "";

        int rangeCount = 0;
        Integer rangeStart = arr[0];
        Integer rangeEnd = arr[0];
        int prevValue = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] - prevValue == 1) {
                rangeCount++;
                rangeEnd = arr[i];
            } else {
                if (result != "") result += ",";
                if (rangeCount > 1) {
                    result += rangeStart + "-" + rangeEnd;
                } else if (rangeStart == rangeEnd) {
                    result += rangeStart;
                } else {
                    result += rangeStart + "," + rangeEnd;
                }
                rangeCount = 0;
                rangeStart = arr[i];
                rangeEnd = arr[i];
            }

            prevValue = arr[i];

        }

        if (result != "") result += ",";
        if (rangeCount > 1) {
            result += rangeStart + "-" + rangeEnd;
        } else if (rangeStart == rangeEnd) {
            result += rangeStart;
        } else {
            result += rangeStart + "," + rangeEnd;
        }

        return result;
    }


    public static String justify(String text, int width) {

        if (text.length() <= width || text == "") return text;

        StringBuilder justifiedText = new StringBuilder();
        StringBuilder word = new StringBuilder();
        StringBuilder line = new StringBuilder();

        int i = 0;

        text += " ";

        while (i < text.length()) {

            word.append(text.charAt(i));

            if (text.charAt(i) == ' ') {

                if (line.length() + word.toString().trim().length() > width) {
                    if (justifiedText.length() != 0) justifiedText.append("\n");
                    justifiedText.append(line.toString().trim());
                    line.delete(0, line.length());
                }

                line.append(word);
                word.delete(0, word.length());

            }

            i++;

        }

        if (justifiedText.length() != 0) justifiedText.append("\n");
        justifiedText.append(line.toString().trim());

        String[] arr = justifiedText.toString().split("\n");

        justifiedText = new StringBuilder();

        for (int k = 0; k < arr.length - 1; k++) {

            String tmp = arr[k];

            if (tmp.contains(" ")) {

                String[] tmpArr = tmp.split(" ");
                int[] spaces = new int[tmpArr.length - 1];

                while (tmp.replace(" ", "").length() + Arrays.stream(spaces).sum() < width) {

                    for (int l = 0; l < spaces.length; l++) {
                        spaces[l]++;
                        if (tmp.replace(" ", "").length() + Arrays.stream(spaces).sum() == width) {
                            break;
                        }
                    }

                }

                int spaceIndex = 0;
                for (String s : tmpArr) {

                    if (spaceIndex < spaces.length) {
                        justifiedText.append(s).append(" ".repeat(spaces[spaceIndex]));
                    } else {
                        justifiedText.append(s).append("\n");
                    }


                    spaceIndex++;
                }

            } else {
                justifiedText.append(tmp).append("\n");
            }

        }

        justifiedText.append(arr[arr.length - 1]);
        return justifiedText.toString();

    }


    public static int determinant(int[][] matrix) {

        if (matrix.length == 1) return matrix[0][0];

        if (matrix.length == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        if (matrix.length > 2) {

            int result = 0;

            for (int i = 0; i < matrix.length; i++) {

                int sign = 1;
                if (i % 2 != 0) sign = -1;

                int[][] newArray = new int[matrix.length - 1][matrix.length - 1];

                int newArrayI = 0;
                int newArrayJ;

                for (int j = 1; j < matrix.length; j++) {
                    newArrayJ = 0;
                    for (int k = 0; k < matrix.length; k++) {
                        if (k != i) {
                            newArray[newArrayI][newArrayJ] = matrix[j][k];
                            newArrayJ++;
                        }
                    }
                    newArrayI++;
                }

                result += sign * matrix[0][i] * determinant(newArray);

            }

            return result;

        }

        return 0;

    }


    public static int dblLinear(int n) {

        //https://www.codewars.com/kata/5672682212c8ecf83e000050/train/java

        SortedSet<Integer> arr = new TreeSet<>();

        arr.add(1);

        ArrayList<Integer> lstToSolve = new ArrayList<>();
        ArrayList<Integer> lstNewItems = new ArrayList<>();

        lstToSolve.add(1);

        int counter = 0;

        while (counter <= n / 3) {

            lstNewItems.clear();

            for (int i = 0; i < lstToSolve.size(); i++) {

                int x1 = 2 * lstToSolve.get(i) + 1;
                int x2 = 3 * lstToSolve.get(i) + 1;

                if (!arr.contains(x1)) arr.add(x1);
                if (!arr.contains(x2)) arr.add(x2);

                lstNewItems.add(x1);
                lstNewItems.add(x2);

            }

            lstToSolve = new ArrayList<>(lstNewItems);

            counter++;

        }

        return arr.stream().collect(Collectors.toList()).get(n);

    }

    public static String toRoman(int n) {

        String result = "";

        int thousands = (n / 1000) % 10;
        int hundreds = (n / 100) % 10;
        int dozens = (n / 10) % 10;
        int numbers = n % 10;

        for (int i = 0; i < thousands; i++) {
            result += "M";
        }

        if (hundreds < 4) {
            for (int i = 0; i < hundreds; i++) {
                result += "C";
            }
        } else if (hundreds == 4) {
            result += "CD";
        } else if (hundreds < 9) {
            result += "D";
            for (int i = 0; i < hundreds - 5; i++) {
                result += "C";
            }
        } else {
            result += "CM";
        }

        if (dozens < 4) {
            for (int i = 0; i < dozens; i++) {
                result += "X";
            }
        } else if (dozens == 4) {
            result += "XL";
        } else if (dozens < 9) {
            result += "L";
            for (int i = 0; i < dozens - 5; i++) {
                result += "X";
            }
        } else {
            result += "XC";
        }

        if (numbers < 4) {
            for (int i = 0; i < numbers; i++) {
                result += "I";
            }
        } else if (numbers == 4) {
            result += "IV";
        } else if (numbers < 9) {
            result += "V";
            for (int i = 0; i < numbers - 5; i++) {
                result += "I";
            }
        } else {
            result += "IX";
        }

        return result;
    }

    public static int fromRoman(String romanNumeral) {

        int result = 0;

        if (romanNumeral.contains("IV")) {
            result += 4;
            romanNumeral = romanNumeral.replace("IV", "");
        }

        if (romanNumeral.contains("IX")) {
            result += 9;
            romanNumeral = romanNumeral.replace("IX", "");
        }

        if (romanNumeral.contains("XL")) {
            result += 40;
            romanNumeral = romanNumeral.replace("XL", "");
        }

        if (romanNumeral.contains("XC")) {
            result += 90;
            romanNumeral = romanNumeral.replace("XC", "");
        }

        if (romanNumeral.contains("CD")) {
            result += 400;
            romanNumeral = romanNumeral.replace("CD", "");
        }

        if (romanNumeral.contains("CM")) {
            result += 900;
            romanNumeral = romanNumeral.replace("CM", "");
        }

        for (Character ch : romanNumeral.toCharArray()) {

            switch (ch) {
                case 'I':
                    result += 1;
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    result += 10;
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    result += 100;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
            }

        }

        return result;

    }


    public static String[] dirReduc(String[] arr) {

        int i = 0;
        int j = 0;

        while (i < arr.length - 1) {

            if (arr[i] == "") {
                i++;
            } else {

                j = i + 1;

                while (j < arr.length && arr[j] == "") {
                    j++;
                }

                if (j == arr.length) break;

                if (
                        (arr[i] == "NORTH" && arr[j] == "SOUTH") ||
                                (arr[i] == "SOUTH" && arr[j] == "NORTH") ||
                                (arr[i] == "EAST" && arr[j] == "WEST") ||
                                (arr[i] == "WEST" && arr[j] == "EAST")
                ) {
                    arr[i] = "";
                    arr[j] = "";
                    i = 0;
                } else {
                    i++;
                }

            }

        }

        List<String> list = new ArrayList<>();

        for (String s : arr) {
            if (s != "") list.add(s);
        }

        return list.toArray(new String[0]);

    }


    public static int findIt(int[] a) {

//        List asList = Arrays.asList(a);
//        Set<Integer> mySet = new HashSet<Integer>(asList);

        int result = 0;

        for (int i : a) {

            result = 0;

            for (int j : a) {

                if (i == j) result++;

            }

            if (result % 2 != 0) return i;

        }

        return -1;

        //int xor = 0;
        //    for (int i = 0; i < A.length; i++) {
        //      xor ^= A[i];
        //    }
        //    return xor;

    }


    public static double[] tribonacci(double[] s, int n) {

        double[] result = new double[n];

        if (n <= 0) return result;

        if (s.length == 0) return result;

        for (int i = 0; i < n; i++) {

            if (i == 0) {
                result[0] = s[0];
            } else if (i == 1) {
                result[1] = s[1];
            } else if (i == 2) {
                result[2] = s[2];
            } else {
                result[i] = result[i - 1] + result[i - 2] + result[i - 3];
            }

        }

        return result;

    }


    public static String camelCase(String input) {

        String result = "";

        char[] arr = input.toCharArray();

        for (char c : arr) {
            if (Character.isUpperCase(c)) {
                result += " ";
            }
            result += c;
        }

        return result;

        //return input.replaceAll("([A-Z])", " $1");

    }


    public static int findEvenIndex(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            int leftSum = 0;
            int rightSum = 0;

            for (int j = 0; j < i; j++) {
                leftSum += arr[j];
            }

            for (int j = arr.length - 1; j > i; j--) {
                rightSum += arr[j];
            }

            if (leftSum == rightSum) return i;

        }

        return -1;

    }


    public static String longest(String s1, String s2) {

        String result = "";

        char[] arr = (s1 + s2).toCharArray();

        Arrays.sort(arr);

        for (char c : arr) {
            if (result.indexOf(c) < 0) {
                result += c;
            }
        }

        return result;

        //Take 2 strings s1 and s2 including only letters from ato z. Return a new sorted string, the longest possible, containing distinct letters - each taken only once - coming from s1 or s2.

    }


    public static boolean comp(int[] a, int[] b) {

        //https://www.codewars.com/kata/550498447451fbbd7600041c/train/java

        int squareCount = 0;

        if (a == null || b == null) return false;

        if (b.length == 0) return true;

        //if (a.length == 0 || b.length == 0) return false;

        //if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {

            int aa = (int) Arrays.stream(a).sorted().toArray()[i];
            int bb = (int) Arrays.stream(b).sorted().toArray()[i];

            if (aa * aa == bb) {
                squareCount++;
            }

        }


        return squareCount == b.length;

//        for (int elemB : b) {
//
//            if (Arrays.stream(a).anyMatch(value -> value*value == elemB)) {
//                squareCount++;
//            }
//
//        }
//
//        for (int elemA : a) {
//
//            if (Arrays.stream(b).anyMatch(value -> value == elemA*elemA)) {
//                squareCount++;
//            }
//
//        }
//
//        return squareCount == a.length*3;

    }


    public static int sequence(int[] arr) {

        int result = 0;

        if (arr.length == 0 || Arrays.stream(arr).max().getAsInt() <= 0) {
            return result;
        }

        if (Arrays.stream(arr).allMatch(value -> value > 0)) {
            return Arrays.stream(arr).sum();
        }

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            if (sum < 0) {
                sum = 0;
            }

            sum += arr[i];

            if (sum > result) {
                result = sum;
            }

        }

        return result;

        // The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers:
        // Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        // should be 6: {4, -1, 2, 1}

    }


    public static String[] wave(String str) {

        String[] result = new String[str.replaceAll(" ", "").length()];

        int arrIndex = 0;

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) != ' ') {

                result[arrIndex] = str.substring(0, i) + str.substring(i, i + 1).toUpperCase() + str.substring(i + 1);

                arrIndex++;

            }

        }

        return result;

        //Task
        //In this simple Kata your task is to create a function that turns a string into a Mexican Wave. You will be passed a string and you must return that string in an array where an uppercase letter is a person standing up.
        //Rules
        // 1.  The input string will always be lower case but maybe empty.
        // 2.  If the character in the string is whitespace then pass over it as if it was an empty seat

        //      List<String> list = new ArrayList<>();
        //      for (int i = 0; i < str.length(); i++) {
        //        char ch = str.charAt(i);
        //        if (ch == ' ') continue;
        //        list.add(str.substring(0,i) + Character.toUpperCase(ch) + str.substring(i+1));
        //      }
        //      return list.toArray(new String[0]);

    }


    public static int GetSum(int a, int b) {
        //Given two integers a and b, which can be positive or negative, find the sum of all the integers between and including them and return it. If the two numbers are equal return a or b.
        //
        //Note: a and b are not ordered!

        int result = 0;

        if (a == b) {
            return a;
        }

        for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
            result += i;
        }

        return result;
    }


    public static long findNb(long m) {

        // find n that n^3 + (n-1)^3 + (n-2)^3 + ... + 1^3 == m

        long result = 1;

        Long i = 2l;

        while (result < m) {

            result += i * i * i;

            i++;

        }

        if (result == m) {
            return i - 1;
        } else {
            return -1l;
        }

    }


    public static String decode(String r) {

        //https://www.codewars.com/kata/5dad6e5264e25a001918a1fc/train/java

        String result = "";
        String stringNum = "";
        String code = "";
        Integer num;

        for (Character ch : r.toCharArray()) {
            if (Pattern.matches("[0123456789]", ch.toString())) {
                stringNum += ch;
            } else {
                code += ch;
            }
        }

        num = Integer.parseInt(stringNum);

        for (Character ch : code.toCharArray()) {

            int charCode = -1;

            for (int i = 0; i < 25; i++) {
                if (i * num % 26 == (int) ch - 97) {
                    charCode = i;
                    break;
                }
            }

            if (charCode == -1) {
                return "Impossible to decode";
            } else {
                result += (char) (charCode + 97);
            }

        }

        if (5057 % 26 % 13 == 0) {
            return "Impossible to decode";
        }

        return result;

    }


    public static long[] bonus(int[] arr, long s) {

        //System.out.println(bonus(new int[] {22, 3, 15}, 18228));
        //https://www.codewars.com/kata/5d68d05e7a60ba002b0053f6/train/java


        return null;
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
