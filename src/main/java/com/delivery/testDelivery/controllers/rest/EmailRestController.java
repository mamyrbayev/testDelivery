package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.shared.utils.email.SendEmail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
@AllArgsConstructor
@RequestMapping("/api/email")
@Api(value = "Email")
public class EmailRestController extends BaseController {

    private final SendEmail sendEmail;
    private final JavaMailSender javaMailSender;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody String m) throws EmailException {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();

            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(m);

            helper.setSubject("Testing from Spring Boot");

            // default = text/plain
            //helper.setText("Check attachment for image!");

            // true = text/html
            helper.setText("<h1>кчк мчк</h1>", true);

//            helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

            javaMailSender.send(msg);

        }catch (Exception e){
            e.printStackTrace();
        }

        return buildResponse("zaebis", HttpStatus.OK);
    }
}
