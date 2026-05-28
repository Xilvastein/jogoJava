import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Scene extends JPanel {

    private Image bg;

    private int cenaAtual = 0;

    private JPanel caixaDialogo;
    private JTextArea textoDialogo;

    private JButton btnProxima;
    private JButton btnContinuar;

    private JButton btnIniciar;
    private JButton btnSair;

    public Scene() {

        setLayout(null);

        criarDialogo();
        criarBotaoProxima();
        criarBotaoContinuar();
        criarMenuInicial();

        setFocusable(true);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    if (cenaAtual == 1) {

                        cenaAtual = 2;
                        setCena(cenaAtual);
                    }
                }
            }
        });

        setCena(cenaAtual);
    }

    // 🎮 troca cenário
    public void setCena(int cena) {

        cenaAtual = cena;

        String base =
            "C:\\Users\\danye\\Desktop\\pixelarts\\cenarios\\";

        bg = new ImageIcon(
            base + "cenario" + cena + ".png"
        ).getImage();

        // esconde tudo
        caixaDialogo.setVisible(false);
        btnProxima.setVisible(false);
        btnContinuar.setVisible(false);
        btnIniciar.setVisible(false);
        btnSair.setVisible(false);

        // MENU
        if (cena == 0) {

            btnIniciar.setVisible(true);
            btnSair.setVisible(true);
        }

        // INTRODUÇÃO
        else if (cena == 1) {

            btnContinuar.setVisible(true);
        }

        // DIÁLOGOS
        else {

            caixaDialogo.setVisible(true);
            btnProxima.setVisible(true);

            switch (cena) {

                case 2:
                    textoDialogo.setText(
                        "Mariana: \"Ele controla minhas roupas...\""
                    );
                    break;

                case 3:
                    textoDialogo.setText(
                        "Mariana: \"Tenho medo de falar sobre isso...\""
                    );
                    break;

                case 4:
                    textoDialogo.setText(
                        "Mariana: \"Ele pega meu celular sem permissão.\""
                    );
                    break;

                case 5:
                    textoDialogo.setText(
                        "Mariana: \"Não sei mais o que fazer...\""
                    );
                    break;

                case 6:
                    textoDialogo.setText(
                        "Final: \"Sua voz importa, mesmo depois do silêncio.\""
                    );
                    break;
            }
        }

        repaint();
    }

    // 💬 diálogo
    private void criarDialogo() {

        caixaDialogo = new JPanel();

        caixaDialogo.setLayout(null);

        caixaDialogo.setBackground(
            new Color(0, 0, 0, 220)
        );

        caixaDialogo.setBorder(
            new LineBorder(Color.WHITE, 4)
        );

        textoDialogo = new JTextArea();

        textoDialogo.setForeground(Color.WHITE);

        textoDialogo.setBackground(
            new Color(0, 0, 0, 0)
        );

        textoDialogo.setFont(
            new Font("Monospaced", Font.BOLD, 18)
        );

        textoDialogo.setLineWrap(true);

        textoDialogo.setWrapStyleWord(true);

        textoDialogo.setEditable(false);

        textoDialogo.setOpaque(false);

        caixaDialogo.add(textoDialogo);

        add(caixaDialogo);
    }

    // 🔘 próxima cena
    private void criarBotaoProxima() {

        btnProxima = new JButton("Próxima Cena");

        estiloBotao(btnProxima);

        btnProxima.addActionListener(e -> {

            cenaAtual++;

            if (cenaAtual > 6) {

                cenaAtual = 0;
            }

            setCena(cenaAtual);
        });

        caixaDialogo.add(btnProxima);
    }

    // 🔘 botão continuar
    private void criarBotaoContinuar() {

        btnContinuar = new JButton("[ESPAÇO] Continuar");

        estiloBotao(btnContinuar);

        btnContinuar.addActionListener(e -> {

            cenaAtual = 2;

            setCena(cenaAtual);
        });

        add(btnContinuar);
    }

    // 🎮 menu inicial
    private void criarMenuInicial() {

        btnIniciar = new JButton("Iniciar Jogo");

        estiloBotao(btnIniciar);

        btnIniciar.addActionListener(e -> {

            cenaAtual = 1;

            setCena(cenaAtual);
        });

        add(btnIniciar);

        // botão sair
        btnSair = new JButton("Sair");

        estiloBotao(btnSair);

        btnSair.addActionListener(e -> System.exit(0));

        add(btnSair);
    }

    // 🎨 estilo padrão
    private void estiloBotao(JButton btn) {

        btn.setBackground(Color.BLACK);

        btn.setForeground(Color.WHITE);

        btn.setBorder(
            new LineBorder(Color.WHITE, 3)
        );

        btn.setFocusPainted(false);

        // 🔥 fonte menor
        btn.setFont(
            new Font("Monospaced", Font.BOLD, 13)
        );
    }

    // 📐 layout responsivo
    private void atualizarLayout() {

        int largura = getWidth();

        int altura = getHeight();

        // diálogo
        caixaDialogo.setBounds(
            20,
            altura - 180,
            largura - 40,
            160
        );

        textoDialogo.setBounds(
            20,
            15,
            largura - 260,
            100
        );

        btnProxima.setBounds(
            largura - 260,
            90,
            200,
            45
        );

        // menu
        btnIniciar.setBounds(
            largura / 2 - 120,
            altura - 220,
            240,
            50
        );

        btnSair.setBounds(
            largura / 2 - 120,
            altura - 150,
            240,
            50
        );

        // 🔥 botão continuar MENOR
        btnContinuar.setBounds(
            20,
            altura - 70,
            220,
            38
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        atualizarLayout();

        g.drawImage(
            bg,
            0,
            0,
            getWidth(),
            getHeight(),
            this
        );
    }
}