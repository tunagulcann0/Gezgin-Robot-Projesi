import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.net.URL;

public class Problem1 extends JFrame {
    static int[][] matris;
    static int sleeptime;
    static int col = 0;
    private JPanel panel;
    private JButton bitirButton;
    private JButton baslaButton;
    private JButton degistirButton;
    private final List<Integer> path = new ArrayList<Integer>();
    private String URLPath1 = new String("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");
    private String URLPath2 = new String("http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt");
    URL url= new URL(URLPath1);

    public Problem1() throws MalformedURLException {
        setTitle("Problem 1");
        setSize(900, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        degistirButton = new JButton("Değiştir");
        degistirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(URLPath1.equals("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt")) {
                    URLPath1 = ("http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt");
                    try {
                        url= new URL(URLPath2);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    URLPath1 = ("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");
                    try {
                        url= new URL(URLPath1);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                updateMatris();
                repaint();
            }
        });

        baslaButton = new JButton("Başla");
        baslaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        sleeptime=300;
                        if (DepthFirst.traverse(matris, 0, 0, col)) {
                            System.out.println("Yol bulundu!");
                            int x = 0, y = 0;
                            path.add(x);
                            path.add(y);
                            while (x != col - 2 || y != col - 1) {
                                if (y + 1 < col && matris[x][y + 1] == 5) {
                                    y++;
                                } else if (x + 1 < col && matris[x + 1][y] == 5) {
                                    x++;
                                }
                                path.add(x);
                                path.add(y);
                                repaint();
                                try {
                                    Thread.sleep(sleeptime); // yavaşlama için
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println("Yol bulunamadı!");
                        }
                    }
                });
                t.start();
            }

        });
        bitirButton = new JButton("Bitir");
        bitirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sleeptime=0;
                if (!path.isEmpty()) {
                    int p = 0;
                    while (p < path.size() - 1) {
                        int x = path.get(p);
                        int y = path.get(p + 1);
                        matris[x][y] = 9;
                        p += 2;
                    }
                    path.clear();
                    try {
                        Thread.sleep(sleeptime); // yavaşlama için
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    repaint();

                }

            }
        });
        panel.add(baslaButton);
        panel.add(bitirButton);
        panel.add(degistirButton);
        add(panel, BorderLayout.SOUTH);
        updateMatris();
    }

    private void updateMatris() {
        Engel2 engel2 = new Engel2();
        Engel3 engel3 = new Engel3();
        col = 0;
        ArrayList<ArrayList<Integer>> numbersList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                col++;
                ArrayList<Integer> satır = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    satır.add(Character.getNumericValue(line.charAt(i)));
                }
                numbersList.add(satır);
            }
            matris = new int[col][col];
            for (int i = 0; i < numbersList.size(); i++) {
                for (int j = 0; j < numbersList.get(i).size(); j++) {
                    matris[i][j] = numbersList.get(i).get(j);
                }
            }
            for (int i=0;i<col;i++){
                for (int j = 0; j < col; j++) {
                    if (matris[i][j] == 8) {
                        continue;
                    }
                    if (matris[i][j] == 9) {
                        continue;
                    }
                    if (matris[i][j]==2){
                        switch (engel2.engelsec()){
                            case 0:
                                break;
                                case 1:
                                engel2.engelturu5(matris,i,j);
                                break;
                            case 2:
                                engel2.engelturu6(matris,i,j);
                                break;
                            case 3:
                                engel2.engelturu7(matris,i,j);
                                break;
                            case 4:
                                engel2.engelturu8(matris,i,j);
                                break;
                        }

                    }
                    if (matris[i][j]==3){
                        switch (engel3.engelsec()) {
                            case 0:
                                break;
                            case 1:
                                engel3.engelturu4(matris, i, j);
                                break;
                            case 2:
                                engel3.engelturu5(matris, i, j);
                                break;
                            case 3:
                                engel3.engelturu6(matris, i, j);
                                break;
                            case 4:
                                engel3.engelturu7(matris, i, j);
                                break;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        numbersList.clear();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.translate(50, 50);
        for (int i = 0; i < Problem1.col; i++) {
            for (int j = 0; j < Problem1.col; j++) {
                Color color = null;
                switch (Problem1.matris[i][j]) {
                    case 0:
                        color = Color.WHITE;
                        break;
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 7:
                        color = Color.BLUE;
                        break;
                    case 8:
                        color = Color.RED;
                        break;
                    case 4:
                        color = Color.ORANGE;
                        break;
                    case 5:
                        color = Color.WHITE;
                        break;
                    case 9:
                        color = Color.GREEN;
                }
                g.setColor(color);
                g.fillRect(30 * j, 30 * i, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * j, 30 * i, 30, 30);
            }
        }

       for (int p = 0; p < path.size(); p += 2) {
            int pathX = path.get(p);
            int pathY = path.get(p + 1);
            g.setColor(Color.GREEN);
            g.fillRect(30 * pathY, 30 * pathX, 30, 30);
        }
    }
    }


