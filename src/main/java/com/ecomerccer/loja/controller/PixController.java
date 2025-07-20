package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import com.ecomerccer.loja.service.SelectProduto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pix")
@CrossOrigin(origins = "*")
public class PixController {

    private final SelectProduto selectProduto;
    private final IntemPedidoRepository intemPedidoRepository;

    public PixController(SelectProduto selectProduto, IntemPedidoRepository intemPedidoRepository) {
        this.selectProduto = selectProduto;
        this.intemPedidoRepository = intemPedidoRepository;
    }

    private String gerarPayloadPix(String chavePix, String descricao, String nome, String cidade, String valor) {
        String gui = "BR.GOV.BCB.PIX";

        String subfield00 = "00" + String.format("%02d", gui.length()) + gui;
        String subfield01 = "01" + String.format("%02d", chavePix.length()) + chavePix;

        String merchantAccountInfo = subfield00 + subfield01;
        String campo26 = "26" + String.format("%02d", merchantAccountInfo.length()) + merchantAccountInfo;

        String campo00 = "000201";
        String campo52 = "52040000";
        String campo53 = "5303986";
        String campo54 = "54" + String.format("%02d", valor.length()) + valor;
        String campo58 = "5802BR";
        String campo59 = "59" + String.format("%02d", nome.length()) + nome.toUpperCase();
        String campo60 = "60" + String.format("%02d", cidade.length()) + cidade.toUpperCase();

        String campo62_05 = "05" + String.format("%02d", descricao.length()) + descricao;
        String campo62 = "62" + String.format("%02d", campo62_05.length()) + campo62_05;

        String payloadSemCRC = campo00 + campo26 + campo52 + campo53 + campo54 +
                campo58 + campo59 + campo60 + campo62 + "6304";

        String crc = calcularCRC16(payloadSemCRC);

        return payloadSemCRC + crc;
    }

    private String calcularCRC16(String s) {
        int polinomio = 0x1021;
        int resultado = 0xFFFF;

        for (byte b : s.getBytes()) {
            resultado ^= (b << 8);
            for (int i = 0; i < 8; i++) {
                if ((resultado & 0x8000) != 0) {
                    resultado = (resultado << 1) ^ polinomio;
                } else {
                    resultado <<= 1;
                }
                resultado &= 0xFFFF;
            }
        }
        return String.format("%04X", resultado);
    }

    @GetMapping(value = "/pix/qrcode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> gerarPixQRCode(@RequestParam Long idIntemPedido) throws Exception {
        Optional<IntemPedido> optionalItem = intemPedidoRepository.findById(idIntemPedido);
        if (optionalItem.isEmpty()) {
            throw new RuntimeException("Item de pedido não encontrado");
        }

        IntemPedido item = optionalItem.get();
        BigDecimal total = item.getTotal();

        // Formata o valor para o padrão do Pix (2 casas decimais, ponto como separador)
        DecimalFormat df = new DecimalFormat("0.00");
        String valor = df.format(total).replace(",", ".");

        String chavePix = "gustalpcarvalho@gmail.com";
        String descricao = "Checkout";
        String nome = "LUIZ CARVALHO";
        String cidade = "LUZIANIA";

        String payload = gerarPayloadPix(chavePix, descricao, nome, cidade, valor);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, 300, 300, hints);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "PNG", baos);
        String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

        Map<String, String> response = new HashMap<>();
        response.put("payload", payload);
        response.put("qrcodeBase64", "data:image/png;base64," + base64Image);
        return response;
    }
}
