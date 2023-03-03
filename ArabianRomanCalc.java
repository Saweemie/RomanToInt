import java.util.Scanner;
//throws Exception,
//Условие:
//1: +, -, *, / .
//2: I,II,III,IV,V...
//3: scan(I < X), && (1<10)
//4: res = (I < C), int
//5: 7+X =Exception
//6: II*L = C , 9-10 = -1
//7: IV-IIX, 42/7=Exception
//8://1
//9int
//10
//11 1-3-4; Exception
public class ArabianRomanCalc {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner (System.in );
        System.out.println("Введите выражение: ");
        String exp = scan.nextLine();
        System.out.println(parse(exp));

    }

    public static String  parse(String exp) throws Exception {

        int a,b;
        String oper;
        String result;
        boolean isRoman;
        exp = exp.replaceAll(" ", "");

        //1//
        //data[a, b]- разделяем полученное выражение по действию на числа
        String[] data = exp.split("[+\\-*/]");

        oper = detectOperation (exp);

        if (oper == null) throw new Exception("В качестве математического действия укажите символы из ниже прведенного списка:\n'+', '-', '*', '/'." );
        //1//

        //11//3//
        if (data.length != 2) throw new Exception("Выражение должно содержать два числа от 1 до 10 включительно.\nПример:'4*6', 'IX/III'.");
        //11//3//

        //если оба числа римские
        if (Roman.isRom(data[0]) && Roman.isRom(data[1])) {
            //конвертируем оба числа в арабские для вычесления
            a = Roman.convertToInt(data[0]);
            b = Roman.convertToInt(data[1]);
            isRoman = true;
        }
        //если оба числа арабские
        else if (!Roman.isRom(data[0]) && !Roman.isRom(data[1])) {
            a = Integer.parseInt(data[0]);
            b = Integer.parseInt(data[1]);
            isRoman = false;
        }
        //5//
        //если одни число римское, а другое - арабское
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        //5//

        //3//
        if (a > 10 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10");

        }
        //3//

        int arabRes = calc(a, b, oper);

        if (isRoman) {
        //10
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabRes <= 0) {
                throw new Exception("Римские цифры не могут представлять отрицательные числа");
            }
        //10

            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRom(arabRes);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabRes);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String exp) {
        if (exp.contains("+")) return "+";
        else if (exp.contains("-")) return "-";
        else if (exp.contains("*")) return "*";
        else if (exp.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romArr = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRom(String val) {
        for (int i = 0; i < romArr.length; i++) {
            if (val.equals(romArr[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToInt(String rom) {
        for (int i = 0; i < romArr.length; i++) {
            if (rom.equals(romArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRom(int arab) {
        return romArr[arab];
    }

}
