import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class SendingMessage {

    // GUI elements
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField recipientField;
    private JTextField subjectField;
    private JTextArea messageArea;
    private JButton sendButton;
    private JPanel panel;

    // function to form a message from GUI input fields
    public MyMessage form() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String recipient = recipientField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();

        MyMessage toSend = new MyMessage(new User(username, password), username, recipient, subject, message);
        return toSend;
    }

    // function to send message
    public static void send(MyMessage toSend)
    {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {                              //because we use a concrete mailing service - Gmail, we need Authntication
                        String username = toSend.getUser().getUsername();
                        String password = toSend.getUser().getPassword();
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);

        try {
            String username = toSend.getFrom();
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toSend.getTo()));
            message.setSubject(toSend.getSubject());
            message.setText(toSend.getMessageText());

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        recipientField.setText("");
        subjectField.setText("");
        messageArea.setText("");
    }

    public SendingMessage() {

        // listener for Send button in GUI
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyMessage toSend = form();
                SendingMessage.send(toSend);
                clearFields();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Send new Message");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new SendingMessage().panel);
        frame.pack();
        frame.setVisible(true);
    }
}
