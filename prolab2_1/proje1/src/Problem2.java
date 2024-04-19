import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem2 extends JFrame {

    private JPanel panel; // labirenti göstermek için kullanılacak JPanel
    private JTextField rowField, colField; // kullanıcının matrisin boyutlarını gireceği JTextField nesneleri
    private int[][] maze; // labirentin matrisi
    private int rows, cols; // labirentin satır ve sütun sayısı
    private int startX, startY, endX, endY; // labirentin giriş ve çıkış koordinatları
    private final List<Integer> path = new ArrayList<Integer>();

    public Problem2() {
        super("Problem 2"); // JFrame başlığı

        // JFrame ayarları
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Kullanıcının matris boyutlarını girebileceği JTextField nesneleri
        rowField = new JTextField("20", 5);
        colField = new JTextField("20", 5);
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("Satır:"));
        controlPanel.add(rowField);
        controlPanel.add(new JLabel("Sütun:"));
        controlPanel.add(colField);

        // "Oluştur" düğmesi
        JButton createButton = new JButton("Oluştur");
        createButton.addActionListener(e -> {
            // Kullanıcının girdiği boyutlarda bir matris oluştur
            rows = Integer.parseInt(rowField.getText() ) ;
            cols = Integer.parseInt(colField.getText() ) ;
            maze = new int[rows][cols];
            startX = 0;
            startY = 0;
            endX = rows -1;
            endY = cols -1;
            // Rastgele labirent oluştur
            createMaze();
            // Labirenti göster
            showMaze();
        });
        controlPanel.add(createButton);

        // JFrame'e arayüz bileşenlerini ekle
        add(controlPanel, BorderLayout.NORTH);
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);
    }

// Rastgele labirent oluşturmak için recursive backtracking algoritmasını
private void createMaze() {
    // labirentin tüm hücreleri 1 ile işaretlenir (duvar)
    for (int i = 0; i < rows-1; i++) {
        for (int j = 0; j < cols-1; j++) {
            maze[i][j] = 1;
        }
    }
    // labirentin giriş ve çıkış noktaları belirlenir
    maze[startX][startY] = 0;
    maze[endX][endY] = 0;
    // recursive backtracking algoritması kullanarak rastgele bir labirent oluşturulur
    createMazeRecursively(startX, startY);
    maze[cols-3][rows-3]=0;
    maze[cols-3][rows-2]=0;
    maze[cols-2][rows-3]=0;
    if (DepthFirst2.traverse(maze, startX, startY,rows)) {
        System.out.println("Yol bulundu!");

    } else {
        System.out.println("Yol bulunamadı!");
    }
}
    // recursive backtracking algoritması
    private void createMazeRecursively(int x, int y) {
        int[][] directions = {{0, -2}, {0, 2}, {-2, 0}, {2, 0}}; // ilerleme yönleri
        Random random = new Random();

        // yönleri rastgele karıştır
        for (int i = directions.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = directions[i];
            directions[i] = directions[j];
            directions[j] = temp;
        }

        // her yönde ilerle ve duvarları kırarak labirent oluştur
        for (int[] dir : directions) {
            int dx = x + dir[0];
            int dy = y + dir[1];

            // ilerleme alanı labirentin içinde mi kontrol et
            if (dx >= 0 && dx < rows && dy >= 0 && dy < cols && maze[dx][dy] == 1) {
                // duvarı kır
                maze[(dx + x) / 2][(dy + y) / 2] = 0;
                maze[dx][dy] = 0;

                // recursive olarak ilerle
                createMazeRecursively(dx, dy);
            }
        }
    }

    // labirenti JPanel'de göstermek için özel bir Component sınıfı
    private class MazeComponent extends JComponent {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellSize = 20; // her hücrenin boyutu
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(30)); // kalınlık ayarlanır
            g2d.setColor(Color.BLACK);
            g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);

            // labirenti çiz
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Color color = null;
                    switch (maze[i][j]){
                        case 0:
                            color = Color.WHITE;
                            break;
                        case 1:
                            color = Color.BLACK;
                            break;
                        case 2 :
                            color = Color.MAGENTA;
                            break;
                        case 5:
                            color = Color.GREEN;
                            break;
                        case 6:
                            color= Color.ORANGE;
                            break;
                    }
                    g.setColor(color);
                    g.fillRect(cellSize*j,cellSize*i , cellSize, cellSize);

                }
            }

            // giriş ve çıkış noktalarını çiz
            g.setColor(Color.BLUE);
            g.fillRect(startY,startX, cellSize, cellSize);
            g.setColor(Color.RED);
            g.fillRect(endY*cellSize, endX*cellSize, cellSize, cellSize);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cols * 20, rows * 20); // JPanel boyutu hesapla
        }
    }

    // labirenti göster
    private void showMaze() {
        panel.removeAll(); // önceki labirenti sil
        panel.add(new MazeComponent()); // yeni labirent
    }

    // kullanıcının matris boyutlarını değiştirme işlemini gerçekleştirir
    private void resizeMaze() {
        try {
            // matris boyutları için kullanıcıdan girdi al
            int newRows = Integer.parseInt(rowField.getText());
            int newCols = Integer.parseInt(colField.getText());

            // yeni matrisi oluştur
            rows = newRows;
            cols = newCols;
            maze = new int[rows][cols];

            // yeni labirenti oluştur ve göster
            createMaze();
            showMaze();
        } catch (NumberFormatException ex) {
        }
    }

    }

