import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Avaliador de Consciência");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setAlwaysOnTop(true);

        add(new Scene()); // seu jogo entra aqui
        setType(JFrame.Type.UTILITY);
        setVisible(true);
    }
}