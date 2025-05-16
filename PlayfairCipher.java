#2b
import java.util.*;

public class PlayfairCipher {
    private char[][] matrix = new char[5][5];

    public PlayfairCipher(String key) {
        key = formatKey(key);
        buildMatrix(key);
        printMatrix(); 
    }

    private String formatKey(String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        Set<Character> used = new HashSet<>();
        for (char c : key.toCharArray()) {
            if (!used.contains(c)) {
                used.add(c);
                result.append(c);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!used.contains(c)) {
                used.add(c);
                result.append(c);
            }
        }
        return result.toString();
    }

    private void buildMatrix(String key) {
        int index = 0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = key.charAt(index++);
    }

    private void printMatrix() {
        System.out.println("\nPlayfair Cipher Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int[] getPosition(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

    private List<String> prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        List<String> pairs = new ArrayList<>();
        int i = 0;
        while (i < text.length()) {
            char a = text.charAt(i++);
            char b = (i < text.length()) ? text.charAt(i) : 'X';
            if (a == b) {
                b = 'X';
            } else {
                i++;
            }
            pairs.add("" + a + b);
        }
        return pairs;
    }

    public String encrypt(String plainText) {
        List<String> pairs = prepareText(plainText);
        StringBuilder encrypted = new StringBuilder();
        for (String pair : pairs) {
            int[] pos1 = getPosition(pair.charAt(0));
            int[] pos2 = getPosition(pair.charAt(1));
            if (pos1[0] == pos2[0]) {
                encrypted.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                encrypted.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {
                encrypted.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                encrypted.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else {
                encrypted.append(matrix[pos1[0]][pos2[1]]);
                encrypted.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String cipherText) {
        List<String> pairs = prepareText(cipherText);
        StringBuilder decrypted = new StringBuilder();
        for (String pair : pairs) {
            int[] pos1 = getPosition(pair.charAt(0));
            int[] pos2 = getPosition(pair.charAt(1));
            if (pos1[0] == pos2[0]) {
                decrypted.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                decrypted.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) {
                decrypted.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                decrypted.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else {
                decrypted.append(matrix[pos1[0]][pos2[1]]);
                decrypted.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter key: ");
        String key = sc.nextLine();
        PlayfairCipher cipher = new PlayfairCipher(key);
        System.out.print("Enter message to encrypt: ");
        String message = sc.nextLine();
        String encrypted = cipher.encrypt(message);
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        sc.close();
    }
}
