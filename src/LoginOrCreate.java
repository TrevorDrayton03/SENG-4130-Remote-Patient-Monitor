
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginOrCreate extends JFrame{
    private JPanel LoginOrCreatePanel;
    private JButton haveAnAccountLoginButton;
    private JButton createAccountButton;

    public LoginOrCreate() {
        setContentPane(LoginOrCreatePanel);
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        haveAnAccountLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //new LoginFrame();
                setVisible(false);
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args){
        new LoginOrCreate();
    }
}
