import java.util.*;
import java.util.stream.Collectors;  
class PassWordGenerator{
    public static void main(String[] args) {
        Scanner Input =  new Scanner(System.in);
        System.out.println("Enter the length of the password :");
        int plen = Input.nextInt();
        System.out.println("Enter the charachter set to be used to generat the password :");
        String chSet = Input.next();

        List<Character> listOfChar = chSet.chars()  
        .mapToObj(data -> (char) data)  
        .collect(Collectors.toList());  

        Collections.shuffle(listOfChar); 

        String password = listOfChar.stream()  
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)  
                .toString();

        System.out.println("The password is : " +password);

    }
}