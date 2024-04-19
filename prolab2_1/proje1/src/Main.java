import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        ArayuzGiris girisEkrani = new ArayuzGiris();
        girisEkrani.setVisible(true);
        Problem1 p1 = new Problem1();
        Problem2 p2= new Problem2();
    }
}
