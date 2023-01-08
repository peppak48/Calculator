package com.danil;

import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Converter converter = new Converter();
        String[] actions = {"+","-","/","*"};
        String[] verificationActions = {"\\+","-","/","\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String exp = scn.nextLine();

        //Определение действия (+,-,*,/)
        int actionIndex=-1;
        for (int i=0; i<actions.length; i++){
            if (exp.contains(actions[i])){
                actionIndex=i;
                break;
            }
        }


        //Проверка на ошибку
        if (actionIndex==-1){
            System.out.println("throws Exception //т.к. строка не является математической операцией");
            return;
        }

        //Проверка на удовлетворение заданию - два операнда и один оператор (+, -, /, *)
        char [] actions2 = new char[]{'+','-','/','*'};
        char [] charArray = exp.toCharArray();
        int counter = 0;
        for (char c : charArray) {
            for (char b : actions2) {
                if (c == b) {
                    counter++;
                }
            }
        }

        if(counter>1){
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return;
        }

        //Делаем строчку по найденному арифметическому знаку
        String[] data = exp.split(verificationActions[actionIndex]);

        //Проверка на идентичность формата чисел (все арабские или все римские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;


            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //Если римские, то конвертируем в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }else {
                //Если арбские, то конвертируем из строки в текст
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if (a==0 || b==0){
                System.out.println("throws Exception //т.к. деление на 0 не возможо");
                return;
            }
            //Выбор арифметического действия
            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            //Если изначально числа были в римском форма, то возвращаем их в римском формате
            if (isRoman){
                System.out.println(converter.intToRoman(result));


                //Если изначально были в арабском формате, то возвращаем в арабском формате
            } else{
                System.out.println(result);
            }


        }else {
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
        }

    }
}
