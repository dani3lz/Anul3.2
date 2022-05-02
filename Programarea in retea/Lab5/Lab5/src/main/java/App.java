import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public String user;
    public String password;
    public String host_imap;
    public String host_pop3;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public App() throws IOException {
        FileInputStream config = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(config);

        this.user = properties.getProperty("mail.user");
        this.password = properties.getProperty("mail.password");
        this.host_imap = properties.getProperty("mail.host.imap");
        this.host_pop3 = properties.getProperty("mail.host.pop3");
    }

    public Session initSMTP(){
        String hostSMTP = "smtp.gmail.com";
        int port = 465;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", hostSMTP);
        prop.put("mail.smtp.ssl.enable", true);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");

        return Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }

    public void SMTP_Send(Session session, String subject, String text, String reply_to, boolean attach) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(user));

        message.setSubject(subject);
        boolean valid = VALID_EMAIL_ADDRESS_REGEX.matcher(reply_to).find();
        if(valid){
            Address[] addresses = {new InternetAddress(reply_to)};
            message.setReplyTo(addresses);
        }


        if (attach) {
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(text);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = "src/main/resources/Attach/Send.jpg";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Send.jpg");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
        } else {
            message.setSentDate(new Date());
            message.setText(text);
        }

        Transport.send(message);
        System.out.println("Sent message successfully....");
    }

    public Store initIMAP() throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.store.protocol", "imaps");

        Store store = Session.getInstance(prop).getStore();
        store.connect(host_imap, user, password);
        return store;
    }

    public void IMAP() throws MessagingException, IOException {
        Store store = initIMAP();
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        System.out.println("NR: " + inbox.getMessageCount());
        System.out.println();

        // Display
        for(int i = 1; i <= inbox.getMessageCount(); i++) {
            String dir = String.valueOf(new Date().getTime());
            File file = new File("src/main/resources/Attach/imap/" + dir);
            file.mkdirs();

            if(inbox.getMessageCount() > 1) {
                System.out.println("<><><><><><><><><><><><><>\n");
            }
            Message msg = inbox.getMessage(i);
            String subject = msg.getSubject();
            System.out.println("Subject: " + subject);
            Object content = msg.getContent();
            String body = "";
            if (content instanceof String) {
                body = content.toString();
                System.out.println("Body:\n" + body);
            } else if (content instanceof Multipart) {
                body = getText(msg);
                System.out.println("Body:\n" + body);

                // Attach
                Multipart multiPart = (Multipart) msg.getContent();
                for (int j = 0; j < multiPart.getCount(); j++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(j);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        String out = "src/main/resources/Attach/imap/" + dir + "/" + part.getFileName();
                        FileOutputStream output = new FileOutputStream(out);
                        InputStream input = part.getInputStream();
                        byte[] buffer = new byte[4096];
                        int byteRead;
                        while ((byteRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, byteRead);
                        }
                        output.close();
                    }

                }
            }
            String filename = "src/main/resources/Attach/imap/" + dir + "/" + subject + ".txt";
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(body);
            myWriter.close();
        }
    }

    public Store initPOP3() throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.store.protocol", "pop3s");

        Store store = Session.getInstance(prop).getStore();
        store.connect(host_pop3, user, password);
        return store;
    }

    public void POP3() throws MessagingException, IOException {
        Store store = initPOP3();
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        System.out.println("NR: " + inbox.getMessageCount());
        System.out.println();
        for(int i = 1; i <= inbox.getMessageCount(); i++) {
            String dir = String.valueOf(new Date().getTime());
            File file = new File("src/main/resources/Attach/pop3/" + dir);
            file.mkdirs();

            if(inbox.getMessageCount() > 1) {
                System.out.println("<><><><><><><><><><><><><>\n");
            }

            Message msg = inbox.getMessage(i);
            String subject = msg.getSubject();
            System.out.println("Subject: " + subject);
            Object content = msg.getContent();
            String body = "";
            if (content instanceof String) {
                body = content.toString();
                System.out.println("Body:\n" + body);
            } else if (content instanceof Multipart) {
                body = getText(msg);
                System.out.println("Body:\n" + body);

                Multipart multiPart = (Multipart) msg.getContent();
                for (int j = 0; j < multiPart.getCount(); j++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(j);
                    // Attach
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        String out = "src/main/resources/Attach/pop3/" + dir + "/" + part.getFileName();
                        FileOutputStream output = new FileOutputStream(out);
                        InputStream input = part.getInputStream();
                        byte[] buffer = new byte[4096];
                        int byteRead;
                        while ((byteRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, byteRead);
                        }
                        output.close();
                    }

                }
            }

            String filename = "src/main/resources/Attach/pop3/" + dir + "/" + subject + ".txt";
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(body);
            myWriter.close();
            System.out.println("Successfully saved - " + subject + ".");
        }
        inbox.close();
        store.close();
    }

    private static String getText(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            return (String)p.getContent();
        }
        if (p.isMimeType("multipart/alternative")) {
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(0);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }
        return null;
    }

    public static void Menu() throws IOException, MessagingException {
        App app = new App();
        boolean run = true;
        while (run){
            System.out.println("\n\n\n1. Afiseaza toata mesajele (IMAP)");
            System.out.println("2. Afiseaza toata mesajele (POP3)");
            System.out.println("3. Trimite mesaj");
            System.out.println("4. Exit");
            System.out.print("> Optiunea: ");
            int nr = new Scanner(System.in).nextInt();
            switch (nr){
                case 1: {
                    app.IMAP();
                    break;
                }
                case 2:{
                    app.POP3();
                    break;
                }
                case 3:{
                    System.out.println("\n\n\n1. Mesaj simplu.");
                    System.out.println("2. Mesaj cu atasament");
                    System.out.print("> Optiunea: ");
                    int sendNr = new Scanner(System.in).nextInt();
                    switch (sendNr){
                        case 1:{
                            System.out.print("\n\n\nSubiectul: ");
                            String subject = new Scanner(System.in).nextLine();
                            System.out.print("Text: ");
                            String text = new Scanner(System.in).nextLine();
                            System.out.print("Reply_To: ");
                            String reply_to = new Scanner(System.in).nextLine();
                            Session session = app.initSMTP();
                            app.SMTP_Send(session, subject, text, reply_to,false);
                            break;
                        }
                        case 2:{
                            System.out.print("\n\n\nSubiectul: ");
                            String subject = new Scanner(System.in).nextLine();
                            System.out.print("Text: ");
                            String text = new Scanner(System.in).nextLine();
                            System.out.print("Reply_To: ");
                            String reply_to = new Scanner(System.in).nextLine();
                            Session session = app.initSMTP();
                            app.SMTP_Send(session, subject, text, reply_to, true);
                            break;
                        }
                        default:{
                            System.out.println("Nu exista asa optiune. 2");
                            break;
                        }
                    }
                    break;
                }
                case 4:{
                    run = false;
                    break;
                }
                default:{
                    System.out.println("Nu exista asa optiune. 1");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, MessagingException {
        Menu();
    }


}
