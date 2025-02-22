package com.barcode.QRGenerator.controller;

import com.barcode.QRGenerator.service.QRGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/qr")
@Tag(name = "QR Generator", description = "2D Barcode/QR Code Generator")
public class QRController {
    @Autowired
    private QRGeneratorService qrGeneratorService;

    @GetMapping("/")
    @Operation(summary = "QR Generator", description = "Create QR Code for provided data string")
    public boolean generateQRCode(@RequestParam String data) {
        return qrGeneratorService.generateQR(data);
    }

}
