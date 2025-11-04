package co.edu.unbosque.bogotacup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoRecuperacion(String toEmail, String nuevaClave) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("no-reply@bogotacup.com");
            message.setTo(toEmail);
            message.setSubject("Recuperación de Contraseña - BogotáCup");
            message.setText("Hola, has solicitado recuperar tu contraseña.\n\n" +
                    "Tu nueva contraseña temporal es: " + nuevaClave + "\n"+
                    "Saludos,\nEl equipo de BogotáCup");

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }
}