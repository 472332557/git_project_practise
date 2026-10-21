package com.liangzc.example;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;

/**
 * DES Encryption Example
 * Implementation of complete DES encryption process:
 * 1) Convert plaintext to byte array using UTF-8 encoding for encryption
 * 2) Convert encrypted byte array to Base64 string
 * 3) Replace special characters: '+' to '*', '/' to '-', '=' to '_'
 * 4) Remove line breaks
 * 5) Use DES/ECB/PKCS5Padding algorithm
 */
public class DesEncryptionExampleEn {

    public static void main(String[] args) {
        try {
            // Original plaintext
            String plainText = "Hello World! This is a test string.";
            // DES key (8 bytes)
            String secretKey = "12345678";
            
            System.out.println("Original Text: " + plainText);
            System.out.println("Secret Key: " + secretKey);
            
            // Call encryption method
            String encryptedText = desEncrypt(plainText, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);
            
            // Verify decryption
            String decryptedText = desDecrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);
            
            // Verify encryption/decryption is correct
            System.out.println("Verification: " + plainText.equals(decryptedText));
            
            // Demonstrate different plaintexts
            System.out.println("\n=== Testing Different Texts ===");
            String[] testTexts = {
                "123456",
                "Hello",
                "Test String",
                "Mixed123!@#"
            };
            
            for (String text : testTexts) {
                String encrypted = desEncrypt(text, secretKey);
                String decrypted = desDecrypt(encrypted, secretKey);
                System.out.println("Original: " + text);
                System.out.println("Encrypted: " + encrypted);
                System.out.println("Decrypted: " + decrypted);
                System.out.println("Match: " + text.equals(decrypted));
                System.out.println("---");
            }
            
            // Demonstrate step by step process
            demonstrateSteps("Hello DES!", secretKey);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * DES Encryption method
     * @param plainText plaintext
     * @param secretKey secret key (8 bytes)
     * @return encrypted string
     */
    public static String desEncrypt(String plainText, String secretKey) throws Exception {
        // Step 1: Convert plaintext to byte array using UTF-8
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);
        
        // Create DES key
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = keyFactory.generateSecret(desKeySpec);
        
        // Step 5: Use DES/ECB/PKCS5Padding algorithm
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        
        // Perform encryption
        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
        
        // Step 2: Convert encrypted bytes to Base64 string
        String base64Encoded = Base64.getEncoder().encodeToString(encryptedBytes);
        
        // Step 3: Replace special characters ('+' to '*', '/' to '-', '=' to '_')
        String replacedString = base64Encoded
                .replace('+', '*')
                .replace('/', '-')
                .replace('=', '_');
        
        // Step 4: Remove line breaks
        String finalResult = replacedString.replaceAll("\\r\\n|\\r|\\n", "");
        
        return finalResult;
    }
    
    /**
     * DES Decryption method (for verification)
     * @param encryptedText encrypted text
     * @param secretKey secret key (8 bytes)
     * @return decrypted plaintext
     */
    public static String desDecrypt(String encryptedText, String secretKey) throws Exception {
        // Restore special character replacement
        String base64String = encryptedText
                .replace('*', '+')
                .replace('-', '/')
                .replace('_', '=');
        
        // Base64 decode
        byte[] encryptedBytes = Base64.getDecoder().decode(base64String);
        
        // Create DES key
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = keyFactory.generateSecret(desKeySpec);
        
        // Create decryptor
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, desKey);
        
        // Perform decryption
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        
        // Convert to string
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
    
    /**
     * Demonstrate each step of the encryption process
     */
    public static void demonstrateSteps(String plainText, String secretKey) throws Exception {
        System.out.println("\n=== Detailed Encryption Steps ===");
        System.out.println("Plaintext: " + plainText);
        System.out.println("Secret Key: " + secretKey);
        
        // Step 1: UTF-8 encoding
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);
        System.out.println("Step 1 - UTF-8 bytes: " + java.util.Arrays.toString(plainTextBytes));
        
        // Steps 2-5: DES encryption
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = keyFactory.generateSecret(desKeySpec);
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
        System.out.println("Step 2 - DES encrypted bytes: " + java.util.Arrays.toString(encryptedBytes));
        
        // Step 3: Base64 encoding
        String base64Encoded = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Step 3 - Base64 encoded: " + base64Encoded);
        
        // Step 4: Special character replacement
        String replacedString = base64Encoded
                .replace('+', '*')
                .replace('/', '-')
                .replace('=', '_');
        System.out.println("Step 4 - Special chars replaced: " + replacedString);
        
        // Step 5: Remove line breaks
        String finalResult = replacedString.replaceAll("\\r\\n|\\r|\\n", "");
        System.out.println("Step 5 - Final result: " + finalResult);
    }
}