package com.wsjzzcbq.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * QRCodeUtils
 *
 * @author wsjz
 * @date 2022/04/06
 */
public class QRCodeUtils {

    public static BufferedImage generateQRcode(int width, int height, String content) throws WriterException {
        Map<EncodeHintType, Object> param = new HashMap<>();
        //字符设置
        param.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
        //纠错等级
        param.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //空白边距
        param.put(EncodeHintType.MARGIN, 3);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, param);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
