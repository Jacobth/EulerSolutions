import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by jacobth on 2016-12-23.
 */
public class Euler {

    public static void main(String[] args) {

        Euler e = new Euler();

        System.out.println(e.combinatoric());
    }

    //Problem 23
    public long sumAbundant() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();

        long total = 0;

        for(int n = 12; n < 28124; n++) {

            int sum = abSum(n);

            if(sum > n) {

                map.put(n, sum);
            }

        }

        Iterator<Integer> iterator = map.keySet().iterator();

        while(iterator.hasNext()) {
            int n = iterator.next();

            Iterator<Integer> it = map.keySet().iterator();

            while(it.hasNext()) {
                sumMap.put(n + it.next(), 0);
            }
        }

        for(int n = 1; n < 28124; n++) {

            if(!sumMap.containsKey(n)) {

                total += n;
            }
        }

        return total;
    }

    private int abSum(int n) {
        int sum = 0;

        for(int i = 1; i <= n / 2; i++) {

            if(n % i == 0) {
                sum += i;
            }
        }

        return sum;
    }

    public int permutations() {

        long x = 1234567890;

        long[] a = new long[1000000];

        for(int i = 0; i < 1000000; i++){
            x = x << 1;

            a[i] = x;
        }

        System.out.println(x);

        return 0;
    }

    public long firstIndex() {

        BigInteger n;
        long index = 2;
        BigInteger current = new BigInteger("1");
        BigInteger prev = new BigInteger("1");

        while(true) {

            index++;

            n = BigInteger.ZERO;

            n = n.add(prev);
            n = n.add(current);

            prev = current;
            current = n;

            if(n.toString().length() == 1000) {
                return index;
            }

        }
    }

    public double getDValue() {

        BigDecimal x = BigDecimal.ONE;
        double y = 0;
        int longest = 0;

        for(double i = 2; i < 1000; i++) {

            int cycle = 0;
            int cycleTmp = 0;

            HashMap<Character, Integer> map = new HashMap<>();

            String s = x.divide(new BigDecimal(i), 10000, RoundingMode.HALF_UP).toString();
            s = s.substring(2, s.length());

            String t = "";

            System.out.println(s);


            for(Character c : s.toCharArray()) {

                String tmp = "";

                if(!map.containsKey(c)) {

                    map.put(c, 0);
                    cycle++;

                    t += c;
                }

                else {
                    tmp += c;
                    cycleTmp++;

                    String t2 = "";

                    for(int j = t.length(); j < t.length()*2 && j < s.length(); j++) {

                        t2 += s.charAt(j);
                    }

                    if(!t2.equals(t)) {
                        cycle++;
                    }

                    else {
                        tmp = "";
                    }


                }
            }

            if(cycle > longest) {
                longest = cycle;

                y = i;
            }
        }

        return y;
    }

    public long maxProduct() {

        long longest = 0;
        long prod = 0;

        for(int a = -999; a < 1000; a++) {

            for(int b = -1000; b <= 1000; b++) {

                long count = 0;

                int n = 0;

                while(true) {

                    long x = (n*n) + (a*n) + b;
                    n++;

                    if(isPrime(x)) {
                        count++;
                    }

                    else {
                        break;
                    }
                }

                if(count >= longest) {

                    longest = count;
                    prod = a * b;
                }
            }
        }

        return prod;

    }

    private boolean isPrime(long x) {

        for(int i = 2; i <= Math.sqrt(Math.abs(x)); i++) {

            if(x % i == 0) {
                return false;
            }
        }

        return true;
    }

    public long matrixSum(int d) {

        long sum = 0;

        int[][] a = new int[d][d];

        int start = d / 2;

        a[start][start] = 1;

        int x = start;
        int y = start;

        int max = 8;

        int length = 3;

        int n = 2;

        while(n <= (1002001- length)) {

            a[y][++x] = n;

            for(int j = 0; j < length - 2; j++) {
                a[++y][x] = ++n;
            }

            for(int j = 0; j < length - 1; j++) {
                a[y][--x] = ++n;
            }

            for(int j = 0; j < length - 1; j++) {
                a[--y][x] = ++n;
            }

            for(int j = 0; j < length - 1; j++) {
                a[y][++x] = ++n;
            }

            length += 2;

            n++;
        }

        int j = 0;

        for(int i = 0; i < a.length; i++) {

            sum += a[j][i] + a[a.length - 1 - j][i];
            j++;
        }

        sum--;

        return sum;
    }

    public long permutation(String str) {

        LinkedList<Long> list = new LinkedList<>();

        permutation("", str, list);

        long num = list.get(1000000 - 1);

        return num;
    }

    private void permutation(String prefix, String str, LinkedList<Long> list) {

        int n = str.length();

        if (n == 0) {
            list.add(Long.parseLong(prefix));
        }

        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), list);
        }
    }

    private static int findPivot(long[] f, int low, int high) {

        for(int i = low; i < high; i++) {
            if(f[i] != f[i + 1]) {
                return (low + high) / 2;
            }
        }

        return -1;
    }

    private static int partition(long[] f, int low, int high, int pivot) {
        while(low < high) {
            while(low < high && f[low] < f[pivot]) {
                low++;
            }

            while(low < high && f[high] >= f[pivot]) {
                high--;
            }

            if(low < high) {
                swap(f, low, high);
            }
        }

        return low;
    }

    private static void swap(long[] f, int low, int high) {
        long tmp = f[low];

        f[low] = f[high];
        f[high] = tmp;
    }

    private static void qs(long[] f, int low, int high) {
        int pivot, middle;

        pivot = findPivot(f, low, high);

        if(pivot != -1) {

            swap(f, pivot, high);
            pivot = high;
            middle = partition(f, low, high, pivot);

            swap(f, middle, pivot);

            qs(f, low, middle);
            qs(f, middle + 1, high);
        }
    }

    public static void qs(long[] f) {
        if(f != null && f.length != 0) {
            qs(f, 0, f.length - 1);
        }
    }

    public int combinations() {

        HashMap<Double, Integer> map = new HashMap<>();

        for(int a = 2; a <= 100; a++) {

            for(int b = 2; b <= 100; b++) {

                map.put(Math.pow(a, b), 0);
            }
        }

        return map.size();
    }

    public long powerSum(int p) {

        long sum = 0;

        for(int n = 2; n < 1000000; n++) {

            String s = n + "";

            long x = 0;

            for(Character c: s.toCharArray()) {

                int y = Integer.parseInt(c + "");

                x += (long)Math.pow(y, p);
            }

            if(x == n) {
                sum += x;
            }
        }

        return sum;
    }

    public long pound(int amount) {
        long sum = 0;

        for(int i = 1; i <= 200; i *= 2) {

            if(i == 4) {
                i++;
            }

            for(int j = 1; j <= 200; j*= 2) {

                if(j == 4) {
                    j++;
                }

                for(int k = 1; k <= 200; k++) {

                }
            }
        }

        return sum;
    }

    public long panDigital() {

        long sum = 0;

        HashMap<Integer,Integer> sumMap = new HashMap<>();

        for(int i = 1; i <= 100000; i++) {

            for(int j = 1; j <= 100000; j++) {

                int prod = j * i;

                String s = prod + "" + i + "" + j;

                if(s.length() > 9) {
                    break;
                }

                boolean isPan = true;

                boolean[] visited = new boolean[10];

                for(Character c : s.toCharArray()) {

                    if(c == '0' || s.length() != 9) {
                        isPan = false;
                        break;
                    }

                    int x = Integer.parseInt(c + "");

                    if(!visited[x]) {
                        visited[x] = true;
                    }
                    else {
                        isPan = false;
                    }

                }

                if(isPan) {
                    sumMap.put(prod, 0);
                }
            }
        }

        Iterator<Integer> iterator = sumMap.keySet().iterator();

        while(iterator.hasNext()) {
            sum += iterator.next();
        }

        return sum;
    }

    public int product() {

        for(int i = 10; i < 100; i++) {

            for(int j = 10; j < 100; j++) {

                String x1 = (i + "").charAt(0) + "";
                String x2 = (i + "").charAt(1) + "";
                String y1 = (j + "").charAt(0) + "";
                String y2 = (j + "").charAt(1) + "";

                int gcd = gcd(j,i);

                int x = i / gcd;
                int y = j / gcd;
                int z = x / y;

                if((x1.equals(x+"") || x2.equals(x+"")) && (y1.equals(y+"") || y2.equals(y+"")) && z < 1) {
                    System.out.println(i + "/" + j);
                }
            }
        }

        System.out.println(gcd(49, 98));

        return 0;
    }

    private int gcd(int a, int b)
    {
        while (b > 0)
        {
            int temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    public long sumFac() {

        long sum = 0;

        for(int i = 3; i < 1000000; i++) {

            String s = i + "";

            long tmp = 0;

            for(Character c : s.toCharArray()) {

                if(tmp > i) {
                    break;
                }

                tmp += factorial(Integer.parseInt(c + ""));
            }

            if(tmp == i) {
                sum += tmp;
            }
        }

        return sum;
    }

    private long factorial(int n) {

        long sum = 1;

        while(n > 1) {

            sum *= n--;
        }

        return sum;
    }

    public int circularPrimes(int max) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;

        for(int n = 2; n < max; n++) {

            if(isPrime(n)) {
                map.put(n, 0);
            }
        }

        Iterator<Integer> iterator = map.keySet().iterator();

        while(iterator.hasNext()) {

            String str = iterator.next() + "";

            char[] cList = str.toCharArray();

            boolean isCycle = true;

            for(int j = 0; j < str.length(); j++) {

                rotate(cList, 1);

                String tmp = new String(cList);

                int i = Integer.parseInt(tmp);

                if (!map.containsKey(i)) {
                    isCycle = false;
                    break;
                }
            }

            if (isCycle) {

                sum++;
            }

        }

        return sum;
    }

    public void rotate(char[] nums, int k) {
        if(k > nums.length)
            k=k%nums.length;

        char[] result = new char[nums.length];

        for(int i=0; i < k; i++){
            result[i] = nums[nums.length-k+i];
        }

        int j=0;
        for(int i=k; i<nums.length; i++){
            result[i] = nums[j];
            j++;
        }

        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    public long palindromeBase(int max) {

        long sum = 0;

        for(int i = 1; i < max; i++) {

            String ten = i + "";
            String two = Integer.toBinaryString(i);

            if(isPalindrome(ten) && isPalindrome(two)) {

                sum += i;
            }
        }

        return sum;
    }

    private boolean isPalindrome(String s) {
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()) {

            stack.push(c);
        }

        int index = 0;

        while(!stack.isEmpty()) {

            if(stack.pop() != s.charAt(index++)) {

                return false;
            }
        }

        return true;
    }

    private static final String[] tensNames = {
            "",
            "ten",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    private static final String[] numNames = {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + "hundred" + soFar;
    }


    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "zero"; }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(snumber.substring(3,6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9,12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1 :
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions)
                        + "billion";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(millions)
                        + "million";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions)
                        + "million";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "onethousand";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + "thousand";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    public long letters() {

        String s = "";

        for(int i = 1; i <= 1000; i++) {

            s += convert(i);
        }

        return s.length();
    }

    public long truncatable() {

        long sum = 0;

        int count = 0;

        for(int i = 11; i < 1000000; i++) {

            boolean isPrime = true;

            int x = i;

            String s = i + "";

            for(int j = 0; j < s.length(); j++) {

                int y = Integer.parseInt(s.substring(j));

                if(y == 1 || x == 1) {
                    isPrime = false;
                    break;
                }

                if(!isPrime(x)) {

                    isPrime = false;

                }

                if(!isPrime(y)) {

                    isPrime = false;

                }

                x = x/10;
            }

            if(isPrime) {
                count++;

                sum += i;
            }

        }

        System.out.println(count);

        return sum;
    }

    public int triangle() {

        int p = 0;
        int longest = 0;

        for(double n = 2; n <= 1000; n++) {

            int cycle = 0;

            for(double x = 2; x < n; x++) {

                for(double y = x + 1; y <= n; y++) {

                    double z = Math.sqrt(Math.pow(x , 2) + Math.pow(y, 2));

                    if((x + y + z) == n) {
                        cycle++;
                    }
                }
            }

            if(cycle > longest) {

                longest = cycle;
                p = (int)n;
            }
        }

        return p;
    }

    public long fractional() {

        long sum = 1;

        String s = "";

        for(int i = 1; i <= 100000; i++) {

            s += i;
        }

        for(int i = 1; i <= 100000; i *= 10) {

            sum *= Integer.parseInt(s.charAt(i) + "");
        }

        return sum;
    }

    public long panPrime() {

        long largest = 0;

        HashMap<Integer,Integer> sumMap = new HashMap<>();

        for(int i = 2; i <= 100000000; i++) {

                String s = i + "";

                if (s.length() > 9) {
                    break;
                }

                boolean isPan = true;

                boolean[] visited = new boolean[10];

                for (Character c : s.toCharArray()) {

                    if (c == '0' || s.length() > 9) {
                        isPan = false;
                        break;
                    }

                    int x = Integer.parseInt(c + "");

                    if (!visited[x]) {
                        visited[x] = true;
                    } else {
                        isPan = false;
                    }

                }

                for(int j = 1; j <= s.length(); j++) {

                    if(!visited[j]) {
                        isPan = false;
                        break;
                    }
                }

                if (isPan && i > largest && isPrime(i)) {

                    largest = i;
                }
        }



        return largest;
    }

    public int triangleWords() {

        int amount = 0;

        String a = "abcdefghijklmnopqrstuvwxyz";

        Map<Character, Integer> map = new HashMap<>();

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < a.length(); i++) {

            map.put(a.charAt(i), i + 1);
        }

        for(int n = 1; n < 500; n++) {

            int x = (n * (n + 1)) / 2;

            set.add(x);
        }

        String[] copy = new String[0];

        try {

            copy = readLines("words.txt");
        }

        catch (IOException e) {

            e.printStackTrace();
        }

        String[] lines = copy[0].split(",");
        String[] newLines = new String[lines.length];

        for(int i = 0; i < lines.length; i++) {

            String s = lines[i];

            String add = s.substring(1, s.length() - 1);

            newLines[i] = add.toLowerCase();
        }

        for(String s : newLines) {

            int value = 0;

            for(char c : s.toCharArray()) {

                value += map.get(c);
            }

            if(set.contains(value)) {

                amount++;
            }
        }

        return amount;
    }

    private String[] readLines(String filename) throws IOException {

        FileReader fileReader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {

            lines.add(line);
        }
        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }

    public int smallestSame() {

        for(int i = 1; 1 < Integer.MAX_VALUE; i++) {

            boolean[] visited = new boolean[10];

            String s = i + "";

            for(char c : s.toCharArray()) {

                int x = Integer.parseInt(c + "");
                visited[x] = true;
            }

            boolean isRight = true;

            for(int j = 2; j <= 6; j++) {

                String s1 = (j*i) + "";

                for(char c : s1.toCharArray()) {
                    int x = Integer.parseInt(c + "");

                    if(!visited[x]) {
                        isRight = false;
                        break;
                    }
                }

                if(!isRight)
                    break;
            }

            if(isRight) {
                return i;
            }
        }
    }

    public long combinatoric() {

        BigDecimal pr = BigDecimal.ONE;
        BigDecimal pn = BigDecimal.ONE;
        long sum = 0;

        for(long n = 1; n <= 100; n++) {

            pn = pn.multiply(new BigDecimal(n + ""));

            for(long r = 1; r <= n; r++) {

                pr = pr.multiply(new BigDecimal(r + ""));

                BigDecimal x = fac(n - r);

                x = x.multiply(pr);

                System.out.println(pn.toString());
               System.out.println(pn.divide(x,20, BigDecimal.ROUND_HALF_EVEN));

                BigDecimal c = pn.divide(x, 20, BigDecimal.ROUND_HALF_UP);

                System.out.println(x.toString());



                if(c.toString().length() > 5) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private BigDecimal fac(long n) {

        BigDecimal x = BigDecimal.ONE;

        if(n == 0) {
            return x;
        }

        for(long i = 1; i <= n; i++) {

            x = x.multiply(new BigDecimal(i + ""));
        }

        return x;
    }
}
