package GUI;

import java.util.ArrayList;

/**
 * This class represents a calculator that can solve for a
 * wide variety of functions given a string input
 * @author Michael Topolski
 * December 2015
 */
public class Calculator
{
    /**
     * function takes in string and puts numeric items in list
     * takes operators and puts them in a list
     * @param input written in string format
     * @return the solution of the equation in string form
     */
    public String Solve(String input)
    {
        String[] E_string;
        int num_1 = 0;
        int count = 0;
        int number_count = 0;
        int solution;
        ArrayList<Integer> numeric_lst = new ArrayList<>();
        ArrayList<String> operator_lst = new ArrayList<>();

        E_string = input.replaceAll("\\s", "").split("");

        for (String s : E_string)
        {
            if (E_string.length == 1)
            {
                if (Character.isDigit(s.charAt(0)))
                {
                    solution = Integer.parseInt(s);
                    return String.valueOf(solution);
                }
                else
                {
                    solution = 0;
                    return String.valueOf(solution);
                }
            }
            else if (s.equals("+"))
            {
                operator_lst.add("+");
                count = 0;
                number_count += 1;
            }
            else if (s.equals("-"))
            {
                operator_lst.add("-");
                count = 0;
                number_count += 1;
            }
            else if (s.equals("*"))
            {
                operator_lst.add("*");
                count = 0;
                number_count += 1;
            }
            else if (s.equals("/"))
            {
                operator_lst.add("/");
                count = 0;
                number_count += 1;
            }
            else if (count >= 1)
            {
                String num_str = numeric_lst.get(number_count).toString();
                String new_str = num_str + s;
                num_1 = Integer.parseInt(new_str);
                numeric_lst.remove(number_count);
                numeric_lst.add(number_count, num_1);
                count += 1;
            }
            else if (Character.isDigit(s.charAt(0)))
            {
                num_1 = Integer.parseInt(s);
                numeric_lst.add(num_1);
                count += 1;
            }
        }

        return Solve_util(operator_lst, numeric_lst);
    }

    /**
     * function takes two lists one filled with operators and the second numeric
     * integers and solves the equation
     * @param operator_lst - list of string operands. '+' '-' '*' '/'
     * @param numeric_lst - list of numeric integers
     * @return solution - the integer answer in a string
     */
    private String Solve_util( ArrayList<String> operator_lst, ArrayList<Integer> numeric_lst)
    {
        int solution = 0;
        int index = 0;

        if(operator_lst.size() == 0)
        {
            solution = numeric_lst.get(0);
            return String.valueOf(solution);
        }
        for (String c : operator_lst)
        {
            try {
                if (index == 0)
                {
                    if (c.equals("+")) solution = numeric_lst.get(index) + numeric_lst.get(index + 1);
                    else if (c.equals("-")) solution = numeric_lst.get(index) - numeric_lst.get(index + 1);
                    else if (c.equals("*")) solution = numeric_lst.get(index) * numeric_lst.get(index + 1);
                    else if (c.equals("/")) solution = numeric_lst.get(index) / numeric_lst.get(index + 1);
                    index += 2;
                }
                else
                {
                    if (c.equals("+"))
                        solution += numeric_lst.get(index);
                    else if (c.equals("-"))
                        solution -= numeric_lst.get(index);
                    else if (c.equals("*"))
                        solution *= numeric_lst.get(index);
                    else if (c.equals("/"))
                        solution /= numeric_lst.get(index);
                    index += 1;
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                return "Need two numbers for equation.";
            }
            catch (ArithmeticException e)
            {
                return "Cannot Divide by zero.";
            }
        }
        return String.valueOf(solution);
    }
}