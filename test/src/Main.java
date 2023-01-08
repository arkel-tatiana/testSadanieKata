import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.print("Введите два числа (арабских или римских) и требуемую операцию: число  операцию число: ");
        String str1 = in.nextLine();
        System.out.println("Результат вычисления = " + calc(str1));
    }
    public static String calc(String input) throws Exception {
        String rezult = "";
        int chislo1 = 0;
        int chislo2 = 0;
        boolean isRoman;
        String [] strs = input.split(" "); //деление строки
        if (strs.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else if (strs.length < 3){
            throw new Exception("Cтрока не является математической операцией");
        }
        if (Roman.isRoman(strs[0]) && Roman.isRoman(strs[2])) {
            chislo1 = Roman.convertorRoman(strs[0]);
            chislo2 = Roman.convertorRoman(strs[2]);
            isRoman = true;
        }
        else if (!Roman.isRoman(strs[0]) && !Roman.isRoman(strs[2])) {
            isRoman = false;
            try {
                chislo1 = Integer.parseInt(strs[0].trim());
                chislo2 = Integer.parseInt(strs[2].trim());
            }
            catch (NumberFormatException e) {
                throw new Exception ("Введено не целое число либо вообще не число");
            }

        }
        else {
            throw new Exception("в введенном выражении используются одновременно разные системы счисления");
        }
        if (chislo1 > 10 || chislo2 > 10 || chislo1 < 1 || chislo2 < 1) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        String operand = strs[1];
        switch(operand){
            case "*" :
                rezult = Integer.toString(chislo1 * chislo2);
                break;
            case "-" :
                rezult = Integer.toString(chislo1 - chislo2);
                break;
            case "+" :
                rezult = Integer.toString(chislo1 + chislo2);
                break;
            case "/" :
                rezult = Integer.toString(Math.round(chislo1 / chislo2));
                break;
            default:
                throw new Exception("Введена недопустимая математическая операция ");
        }
        if (isRoman) {
            int rezultRoman = Integer.parseInt(rezult.trim());
            if (rezultRoman > 0) {
                rezult = Roman.convertorArab(rezultRoman);
            }
            else {
                throw new Exception("Результат не может быть выведен, т.к. в римской системе нет отрицательных чисел");
            }
        }


        return rezult;
    };
    class Roman {
        static String[] romanArray = new String[]{"00", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI","XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        static boolean isRoman(String chislo) {
            for (String Element : romanArray) {
                if (chislo.equalsIgnoreCase(Element)) {
                    return true;
                }
            }
            return false;
        }
        static int convertorRoman(String romanChislo) {
            int i = 0;
            for (String Element : romanArray) {
                if (romanChislo.equalsIgnoreCase(Element)) {
                    return i;
                }
                i++;
            }
            return -1;
        };
        static String convertorArab(int arabChislo) {
            return romanArray[arabChislo];
        }
    }
}