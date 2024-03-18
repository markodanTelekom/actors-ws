package com.onboarding.actors.actorsws.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageHelper {
    public static void saveBase64Image(String base64Data, String fileName) {
        try {
            // Decode Base64 data
            byte[] decodedData = Base64.getDecoder().decode(base64Data);

            // Write the decoded data to a file
            Files.write(Paths.get(fileName), decodedData);

            System.out.println("Image '" + fileName + "' saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }

    public static String encodeImageToBase64(String filePath) {
        String base64Image = "";
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            fis.close();

            // Encode bytes to Base64
            base64Image = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64Image;
    }

    public static String getImageExtension(String imageMetadata){
        //data:image/jpeg;base64
        String[] firstSplit = imageMetadata.split("/");
        String[] extension = firstSplit[1].split(";");
        return extension[0];
    }

    public static String[] splittedImageData(String base64Data){
        String[] decoded = base64Data.split(",");
        System.out.println(decoded[0]);
        System.out.println(decoded[1]);
        return decoded;
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully: " + filePath);
            } else {
                System.err.println("Failed to delete the file: " + filePath);
            }
        } else {
            System.err.println("File does not exist: " + filePath);
        }
    }
}
