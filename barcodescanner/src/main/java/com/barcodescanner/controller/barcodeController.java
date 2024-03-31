package com.barcodescanner.controller;


import com.barcodescanner.service.BarcodeService;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/barcode")
public class barcodeController {

    @Autowired
    BarcodeService barcodeService;

    @PostMapping("/upload")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        String barcodeInformation = barcodeService.getBarcodeInformation(file);
        return barcodeInformation;
    }

}
