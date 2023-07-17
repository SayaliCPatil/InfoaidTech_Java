package pratice;
import java.util.*;
import java.util.stream.Collectors;

public class PassWordGenerator {

    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        boolean generateMore = true;

        while (generateMore) {
            System.out.println("Enter the length of the password:");
            int plen = input.nextInt();
            System.out.println("Enter the character set to be used to generate the password:");
            String chSet = input.next();

            List<Character> listOfChar = chSet.chars()
                    .mapToObj(data -> (char) data)
                    .collect(Collectors.toList());

            Collections.shuffle(listOfChar);

            StringBuilder passwordBuilder = new StringBuilder(plen);
            for (int i = 0; i < plen; i++) {
                int randomIndex = (int) (Math.random() * listOfChar.size());
                passwordBuilder.append(listOfChar.get(randomIndex));
            }

            String password = passwordBuilder.toString();
            System.out.println("The password is: " + password);

            System.out.println("Do you want to regenerate the password? (Y/N):");
            String regenerateChoice = input.next();
            generateMore = regenerateChoice.equalsIgnoreCase("Y");
        }
        System.out.println("Thank you for using the Password Generator!");
    }
}
