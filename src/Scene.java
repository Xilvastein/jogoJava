import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Scene extends JPanel {

    private Image bg;

    private int cenaAtual = 0;

    private JPanel caixaDialogo;
    private JTextArea textoDialogo;

    private JButton btnProxima;
    private JButton btnContinuar;

    private JButton btnIniciar;
    private JButton btnSair;

    // OPÇÕES (VISUAIS APENAS)
    private JButton opcao1;
    private JButton opcao2;
    private JButton opcao3;

    public Scene() {

        setLayout(null);

        criarDialogo();
        criarBotaoProxima();
        criarBotaoContinuar();
        criarMenuInicial();
        criarBotoesOpcao();

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

    // troca cenário
    public void setCena(int cena) {

        cenaAtual = cena;

        String caminho = "/images/pixelarts/cenarios/cenario" + cena + ".png";

        URL url = getClass().getResource(caminho);

        if (url != null) {
            bg = new ImageIcon(url).getImage();
        } else {
            System.out.println("❌ Imagem não encontrada: " + caminho);
            bg = null;
        }

        // esconde tudo
        caixaDialogo.setVisible(false);
        btnProxima.setVisible(false);
        btnContinuar.setVisible(false);
        btnIniciar.setVisible(false);
        btnSair.setVisible(false);

        opcao1.setVisible(false);
        opcao2.setVisible(false);
        opcao3.setVisible(false);

        // MENU
        if (cena == 0) {

            btnIniciar.setVisible(true);
            btnSair.setVisible(true);
        }

        // INTRO
        else if (cena == 1) {

            btnContinuar.setVisible(true);
        }

        // DIÁLOGOS + OPÇÕES VISUAIS
        else {

            caixaDialogo.setVisible(true);

            btnProxima.setVisible(true);

            opcao1.setVisible(true);
            opcao2.setVisible(true);
            opcao3.setVisible(true);

            switch (cena) {

                case 2 -> textoDialogo.setText("Mariana: \"Ele controla minhas roupas...\"");
                case 3 -> textoDialogo.setText("Mariana: \"Tenho medo de falar sobre isso...\"");
                case 4 -> textoDialogo.setText("Mariana: \"Ele pega meu celular sem permissão.\"");
                case 5 -> textoDialogo.setText("Mariana: \"Não sei mais o que fazer...\"");
                case 6 -> textoDialogo.setText("Final: \"Sua voz importa, mesmo depois do silêncio.\"");
            }
        }

        repaint();
    }

    //  diálogo
    private void criarDialogo() {

        caixaDialogo = new JPanel();
        caixaDialogo.setLayout(null);

        caixaDialogo.setBackground(new Color(0, 0, 0, 220));
        caixaDialogo.setBorder(new LineBorder(Color.WHITE, 4));

        textoDialogo = new JTextArea();
        textoDialogo.setForeground(Color.WHITE);
        textoDialogo.setBackground(new Color(0, 0, 0, 0));
        textoDialogo.setFont(new Font("Monospaced", Font.BOLD, 18));
        textoDialogo.setLineWrap(true);
        textoDialogo.setWrapStyleWord(true);
        textoDialogo.setEditable(false);
        textoDialogo.setOpaque(false);

        caixaDialogo.add(textoDialogo);
        add(caixaDialogo);
    }

    // próxima cena (sempre à direita das opções)
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

    // continuar
    private void criarBotaoContinuar() {

        btnContinuar = new JButton("[ESPAÇO] Continuar");
        estiloBotao(btnContinuar);

        btnContinuar.addActionListener(e -> {
            cenaAtual = 2;
            setCena(cenaAtual);
        });

        add(btnContinuar);
    }

    //  menu
    private void criarMenuInicial() {

        btnIniciar = new JButton("Iniciar Jogo");
        estiloBotao(btnIniciar);

        btnIniciar.addActionListener(e -> {
            cenaAtual = 1;
            setCena(cenaAtual);
        });

        add(btnIniciar);

        btnSair = new JButton("Sair");
        estiloBotao(btnSair);

        btnSair.addActionListener(e -> System.exit(0));

        add(btnSair);
    }

    // ⭐ OPÇÕES (SEM AÇÃO)
    private void criarBotoesOpcao() {

        opcao1 = new JButton("Opção 1");
        opcao2 = new JButton("Opção 2");
        opcao3 = new JButton("Opção 3");

        estiloBotao(opcao1);
        estiloBotao(opcao2);
        estiloBotao(opcao3);

        // ❗ NÃO FAZEM NADA AINDA
        opcao1.addActionListener(e -> {});
        opcao2.addActionListener(e -> {});
        opcao3.addActionListener(e -> {});

        caixaDialogo.add(opcao1);
        caixaDialogo.add(opcao2);
        caixaDialogo.add(opcao3);
    }

    //  estilo padrão
    private void estiloBotao(JButton btn) {

        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new LineBorder(Color.WHITE, 3));
        btn.setFocusPainted(false);
        btn.setFont(new Font("Monospaced", Font.BOLD, 13));
    }

    //  layout (PRÓXIMA SEMPRE À DIREITA)
    private void atualizarLayout() {

        int largura = getWidth();
        int altura = getHeight();

        caixaDialogo.setBounds(20, altura - 180, largura - 40, 160);

        textoDialogo.setBounds(20, 15, largura - 260, 100);

        int y = 90;

        //  OPÇÕES primeiro (centro)
        opcao1.setBounds(largura / 2 - 220, y, 120, 45);
        opcao2.setBounds(largura / 2 - 90, y, 120, 45);
        opcao3.setBounds(largura / 2 + 40, y, 120, 45);

        //  PRÓXIMA CENA SEMPRE À DIREITA
        btnProxima.setBounds(largura / 2 + 170, y, 160, 45);

        btnIniciar.setBounds(largura / 2 - 120, altura - 220, 240, 50);
        btnSair.setBounds(largura / 2 - 120, altura - 150, 240, 50);

        btnContinuar.setBounds(20, altura - 70, 220, 38);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        atualizarLayout();

        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }
}