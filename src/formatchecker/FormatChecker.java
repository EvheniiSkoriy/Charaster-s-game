package formatchecker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FormatChecker {

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            System.out.println(checkFormat(args[i]));
            System.out.println("");

        }
    }

    private static String checkFormat(String fileName) {
        List<Double> list = new ArrayList();
        BufferedReader reader = null;
        String line;
        String firstLine;
        String[] firstArray = {};
        int j = 0;
        int k = 0;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            firstLine = reader.readLine();
            firstArray = firstLine.split(" ");
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(" ");
                for (int i = 0; i < array.length; i++) {

                    try {
                        list.add(Double.parseDouble(array[i]));
                    } catch (NumberFormatException e) {
                        System.out.println("java.lang.NumberFormatException: For input String '" + array[i] + "'");
                        return "Invalid";
                    }

                }
                k = array.length;
                try {
                    if (k != Integer.parseInt(firstArray[1])) {
                        throw new DimensionMismatchException();
                    }
                } catch (NumberFormatException e) {

                    System.out.println("java.lang.NumberFormatException: For input String '" + firstArray[0] + "'" + " or '" + firstArray[1] + "'");
                    return "Invalid";
                }
                j++;

            }

            try {

                if (j != Integer.parseInt(firstArray[0]) || k != Integer.parseInt(firstArray[1]) || firstArray.length > 2) {
                    throw new DimensionMismatchException("Incorrect size");
                }
            } catch (NumberFormatException e) {
                System.out.println("java.lang.NumberFormatException: For input String '" + firstArray[0] + "'" + " or '" + firstArray[1] + "'");
                return "Invalid";
            }
        } catch (FileNotFoundException e) {
            System.out.println("java.io.FileNotFoundExeption: " + fileName + " (The system cannot find the file specified)");
            return "Invalid";
        } catch (IOException e) {
            return "Invalid";
        } catch (DimensionMismatchException e) {
            System.out.println("java.lang.DimensionMismatchException");
            return "Invalid";
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        return "Valid";
    }

    /**
     * Runtime exception thrown when the given dimensions do not match the data.
     *
     * @author CS 221
     */
    public static class DimensionMismatchException extends RuntimeException {

        private static final long serialVersionUID = 1173110179989747752L;

        /**
         * Default constructor.
         */
        public DimensionMismatchException() {
            super();
        }

        /**
         * Constructor with given message.
         *
         * @param message - String
         */
        public DimensionMismatchException(String message) {
            super(message);
        }
    }
}
