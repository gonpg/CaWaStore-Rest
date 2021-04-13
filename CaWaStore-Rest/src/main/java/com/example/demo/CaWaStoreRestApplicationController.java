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
import org.springframework.mail.MailException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


@RestController
public class CaWaStoreRestApplicationController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public JavaMailSender emailSender;
  
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
    
    @RequestMapping(value = "/mail/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void email(@PathVariable long id) {
        Optional <Usuario> user = usuarioRepository.findById(id);

        sendMail(user.get().getEmail(), "Bienvenido a CaWaStore", "Tu usuario es: " + user.get().getNombreUsuario());
    }
    
    
    @RequestMapping(value = "/realizado/{id_pedido}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void orderEmail(@PathVariable long id_pedido) {
        Optional<Pedido> pedido = pedidoRepository.findById(id_pedido);
        String email = pedido.get().getUsuario().getEmail();
        StringBuilder pedido_concat = new StringBuilder();
        for(Producto p : pedido.get().getProductos()) {
            pedido_concat.append(p.getNombre()+","+p.getPrecio()+","+p.getDescripcion()+"\n");
        }
        
        System.out.println("Enviado correo a: " + email);

        sendMail(email, "Pedido completado",
                "Su pedido ha sido realizado\n"
                +pedido_concat);
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

        contentStream.showText("Usuario: " + pedido.getUsuario().getNombreUsuario());
        contentStream.newLine();
        contentStream.showText("Fecha: " + formatter.format(pedido.getFecha()));
        contentStream.newLine();
        contentStream.newLine();

        for (Producto producto : pedido.getProductos()) {
            contentStream.showText(producto.getNombre() + " - " + producto.getPrecio() + "€");
            contentStream.newLine();
        }
        contentStream.newLine();
        contentStream.showText("Gracias por confiar en CaWaStore");
        contentStream.endText();
        contentStream.close();

        document.save(output);

        return output;        

    }
    
    public void sendMail(String address, String subject, String text) {
    	 try {
             SimpleMailMessage message = new SimpleMailMessage();
             message.setTo(address);
             message.setSubject(subject);
             message.setText(text);

             emailSender.send(message);
         } catch (MailException me) {
             System.out.println("No se ha podido enviar el correo");
         }
    }


}