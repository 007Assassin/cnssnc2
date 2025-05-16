//Ceaser Cipher
import java.util.Scanner;

public class twoa {

    public static String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isUpperCase(c)) {
                char encryptedChar = (char) (((c + shift - 'A') % 26) + 'A');
                encryptedText.append(encryptedChar);
            } else if (Character.isLowerCase(c)) {
                char encryptedChar = (char) (((c + shift - 'a') % 26) + 'a');
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isUpperCase(c)) {
                char decryptedChar = (char) (((c - shift - 'A' + 26) % 26) + 'A');
                decryptedText.append(decryptedChar);
            } else if (Character.isLowerCase(c)) {
                char decryptedChar = (char) (((c - shift - 'a' + 26) % 26) + 'a');
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(c); 
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string to encrypt: ");
        String inputText = scanner.nextLine();

        System.out.print("Enter the shift value: ");
        int shift = scanner.nextInt();
        scanner.nextLine(); 

        String encryptedText = encrypt(inputText, shift);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
