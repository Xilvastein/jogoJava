import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Muted");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new Scene()); // seu jogo entra aqui

        setVisible(true);
    }
}