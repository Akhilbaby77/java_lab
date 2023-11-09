public class StringBufferMethods_lab2_2 {

    public static void main(String[] args) {
        
        StringBuffer s = new StringBuffer("Hello");
        
        s.append(" Akhil");
        System.out.println("append(): " + s);
        
        s.insert(5, " Baby");
        System.out.println("insert(): " + s);
        
        s.replace(6, 11, "BABY ");
        System.out.println("replace(): " + s);

        s.delete(6, 11);
        System.out.println("delete(): " + s);
  
        System.out.println("charAt(3): " + s.charAt(3));
     
        s.setCharAt(1, 'i');
        System.out.println("setCharAt(): " + s);
        
        System.out.println("length(): " + s.length());

        System.out.println("capacity(): " + s.capacity());
      
        s.ensureCapacity(50);
        System.out.println("ensureCapacity(): " + s.capacity());

        String str = s.toString();
        System.out.println("toString(): " + str);

        String subStr1 = s.substring(6);
        System.out.println("substring(int start): " + subStr1);

        String subStr2 = s.substring(1, 6);
        System.out.println("substring(int start, int end): " + subStr2);
    }
}
