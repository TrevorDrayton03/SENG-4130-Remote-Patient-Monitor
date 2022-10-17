import javax.swing.*;

public abstract class FrameTemplate extends JFrame {
    final void initalizeFrame(JPanel PanelName){
        setContentPane(PanelName);
        setSize(500,500);
        setVisible(true);
    }
    

}
