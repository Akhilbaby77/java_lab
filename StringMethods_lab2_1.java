public class StringMethods_lab2_1 {

    public static void main(String[] args) {
        String text = "“We realizes that while our workers were thriving, the surrounding villages were still suffering. " +
                "It became our goal to uplift their quality of life as well. I remember seeing a family of 4 on a motorbike " +
                "in the heavy Bombay rain — I knew I wanted to do more for these families who were risking their lives for " +
                "lack of an alternative” The alternative mentioned here is the Tata Nano, which soon after came as the " +
                "world’s cheapest car on retail at a starting price of only Rs 1 lakh. These were the words of Ratan Tata " +
                "in a recent post by Humans of Bombay which formed the basis of his decision to come up with a car like Tata Nano.";

        
        System.out.println("charAt() example: " + text.charAt(5));

        System.out.println("compareTo() example: " + "Sample".compareTo("Sample"));

        System.out.println("concat() example: " + "Tata, ".concat("Nano"));

        System.out.println("contains() example: " + text.contains("Tata Nano"));

        System.out.println("endsWith() example: " + text.endsWith("Nano."));

        System.out.println("equals() example: " + "Tata".equals("Tata"));

        System.out.println("equalsIgnoreCase() example: " + "Bombay".equalsIgnoreCase("hello"));

        System.out.println("format() example: " + String.format("Value: %d", 123));

        System.out.println("getBytes() example: " + text.getBytes().length);

        char[] chars = new char[10];

        text.getChars(10, 20, chars, 0);

        System.out.print("getChars() example: ");

        System.out.println(chars);

        System.out.println("indexOf() example: " + text.indexOf("Tata Nano"));

        System.out.println("intern() example: " + text.intern());

        System.out.println("isEmpty() example: " + text.isEmpty());

        System.out.println("join() example: " + String.join(", ", "Apple", "Banana", "Orange"));

        System.out.println("lastIndexOf() example: " + text.lastIndexOf("Tata Nano"));

        System.out.println("length() example: " + text.length());

        System.out.println("replace() example: " + text.replace("Tata Nano", "Nano"));

        System.out.println("replaceAll() example: " + text.replaceAll("[A-Z]", "*"));

        System.out.println("split() example: " + String.join(", ", text.split(" ")));

        System.out.println("startsWith() example: " + text.startsWith("“We"));

        System.out.println("substring() example: " + text.substring(100, 150));

        System.out.println("toLowerCase() example: " + text.toLowerCase());

        System.out.println("toUpperCase() example: " + text.toUpperCase());

        System.out.println("trim() example: " + "   Trim   ".trim());

        System.out.println("valueOf() example: " + String.valueOf(12345));
    }
}
