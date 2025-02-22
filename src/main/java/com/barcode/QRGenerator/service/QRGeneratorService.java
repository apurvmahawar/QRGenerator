package com.barcode.QRGenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QRGeneratorService {

    public boolean generateQR(String data, int height, int width) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            saveImage(image);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    private static void saveImage(BufferedImage image) throws IOException {
        String outputDirectory = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "QRDump")
                .toString();
        File fileDirectory = new File(outputDirectory);

        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }

        String fileName = "QRCode_" + UUID.randomUUID() + ".png";
        File outputFile = Paths.get(outputDirectory, fileName).toFile();

        ImageIO.write(image, "png", outputFile);
        log.info("QRCode Saved to: " + outputFile.getAbsolutePath());
    }

}
