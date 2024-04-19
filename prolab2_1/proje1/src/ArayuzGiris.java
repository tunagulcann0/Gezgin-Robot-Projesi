import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class ArayuzGiris extends JFrame {
    public ArayuzGiris() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Giriş Ekranı");
        setSize(400, 200);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);
        JLabel baslikEtiketi = new JLabel("Maze Solver", SwingConstants.CENTER);
        baslikEtiketi.setBounds(100, 30, 200, 30);
        panel.add(baslikEtiketi);
        JButton problem1Buton = new JButton("Problem 1");
        problem1Buton.setBounds(50, 100, 120, 30);
        panel.add(problem1Buton);

        JButton problem2Buton = new JButton("Problem 2");
        problem2Buton.setBounds(230, 100, 120, 30);
        panel.add(problem2Buton);
        problem1Buton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Problem1 p1 = null;
                try {
                    p1 = new Problem1();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
                p1.setVisible(true);
                setVisible(false);
            }
        });
        problem2Buton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Problem2 p2 = new Problem2();
                p2.setVisible(true);
                setVisible(false);
            }
        });
    }
}

