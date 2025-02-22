package com.barcode.QRGenerator.controller.dto;

public record QRRequestDto(
        String data,
        int height,
        int width
) {
}
