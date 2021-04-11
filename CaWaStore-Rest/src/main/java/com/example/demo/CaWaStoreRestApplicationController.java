package com.example.demo;

import com.example.demo.entidades.*;
import com.example.demo.repositorios.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CaWaStoreRestApplicationController {

    @Autowired
    private PedidoRepository pedidoRepository;
  
    
    @RequestMapping(value = "/generarFactura/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> generatePDF(@PathVariable long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        String filename = "factura_" + id + ".pdf";
        ByteArrayOutputStream output;

        try {
        output = createInvoice(pedido.get());
        } catch (IOException e) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        return new ResponseEntity<>(output.toByteArray(), headers, HttpStatus.OK);
    }
    
    
    private ByteArrayOutputStream createInvoice(Pedido pedido) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 26);
        contentStream.setLeading(40f);
        contentStream.newLineAtOffset(60, 760);
        contentStream.showText("Pedido nº " + pedido.getId());
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
        contentStream.setLeading(16f);

        contentStream.showText(pedido.getUsuario().getNombreUsuario());
        contentStream.newLine();
        contentStream.showText("Fecha: " + formatter.format(pedido.getFecha()));
        contentStream.newLine();
        contentStream.newLine();

        for (Producto producto : pedido.getProductos()) {
            contentStream.showText(producto.getNombre() + " - " + producto.getPrecio() + "€");
            contentStream.newLine();
        }
        contentStream.endText();
        contentStream.close();

        document.save(output);

        return output;        

    }

}