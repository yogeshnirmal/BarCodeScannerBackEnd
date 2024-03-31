package com.barcodescanner.service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.cglib.core.Converter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BarcodeService {
    public String getBarcodeInformation(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("Content type: " + file.getContentType());

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

            // Convert the image to a format compatible with ZXing
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Initialize the MultiFormatReader and decode the barcode
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);

            // Print the barcode content
            System.out.println("Barcode content: " + result.getText());
            Pattern pattern = Pattern.compile("Salary:\\s*(\\d+)");
            Matcher matcher = pattern.matcher(result.getText());
            if (matcher.find()) {
                String salary = matcher.group(1);
                System.out.println(" In Salary we giving 12$ bonus now Existing Salary" + salary+"$ ANd Now Salary is "+(Integer.parseInt(salary)+12) +"S");
            }
            return result.getText();
        } catch (Exception e) {
            return "Failed to upload file";
        }
    }
}
