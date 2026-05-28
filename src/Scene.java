import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Scene extends JPanel {

    // Imagem de fundo do cenário atual
    private Image bg;

    // Controle da cena atual do jogo
    private int cenaAtual = 0;

    // Sistema de pontuação (começa em 0 e não muda automaticamente)
    private int pontos = 0;

    // Painel de diálogo inferior
    private JPanel caixaDialogo;

    // Texto exibido no diálogo
    private JTextArea textoDialogo;

    // Label da pontuação no canto superior direito
    private JLabel labelPontos;

    // Botão para avançar cena
    private JButton btnProxima;

    // Botão de continuar na introdução
    private JButton btnContinuar;

    // Botões do menu inicial
    private JButton btnIniciar;
    private JButton btnSair;

    // Botões de opção (visuais apenas por enquanto)
    private JButton opcao1;
    private JButton opcao2;
    private JButton opcao3;

    public Scene() {

        // Usa layout manual (posição absoluta dos elementos)
        setLayout(null);

        // Criação dos elementos da interface
        criarDialogo();
        criarPontuacao();
        criarBotaoProxima();
        criarBotaoContinuar();
        criarMenuInicial();
        criarBotoesOpcao();

        // Permite captura de teclado
        setFocusable(true);

        // Evento de teclado para avanço com espaço
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                // Se apertar espaço na cena 1, avança para cena 2
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    if (cenaAtual == 1) {
                        cenaAtual = 2;
                        setCena(cenaAtual);
                    }
                }
            }
        });

        // Inicializa primeira cena
        setCena(cenaAtual);
    }

    // Troca o cenário do jogo
    public void setCena(int cena) {

        cenaAtual = cena;

        // Caminho da imagem dentro do projeto
        String caminho = "/images/pixelarts/cenarios/cenario" + cena + ".png";

        // Carrega imagem como recurso interno do projeto
        URL url = getClass().getResource(caminho);

        if (url != null) {
            bg = new ImageIcon(url).getImage();
        } else {
            System.out.println("Imagem não encontrada: " + caminho);
            bg = null;
        }

        // Esconde todos os elementos antes de mostrar os corretos
        caixaDialogo.setVisible(false);
        btnProxima.setVisible(false);
        btnContinuar.setVisible(false);
        btnIniciar.setVisible(false);
        btnSair.setVisible(false);

        opcao1.setVisible(false);
        opcao2.setVisible(false);
        opcao3.setVisible(false);

        labelPontos.setVisible(false);

        // Menu inicial
        if (cena == 0) {

            btnIniciar.setVisible(true);
            btnSair.setVisible(true);
        }

        // Introdução do jogo
        else if (cena == 1) {

            btnContinuar.setVisible(true);
        }

        // Cenas principais com diálogo e opções
        else {

            caixaDialogo.setVisible(true);
            btnProxima.setVisible(true);

            opcao1.setVisible(true);
            opcao2.setVisible(true);
            opcao3.setVisible(true);

            labelPontos.setVisible(true);

            // Atualiza texto da pontuação sem alterar valor
            atualizarPontuacao();

            // Define o texto de cada cena
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

    // Cria o painel de diálogo inferior
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

    // Cria label de pontuação
    private void criarPontuacao() {

        labelPontos = new JLabel("Pontuação: 0");
        labelPontos.setForeground(Color.WHITE);
        labelPontos.setHorizontalAlignment(SwingConstants.RIGHT);

        atualizarFontePontuacao();

        add(labelPontos);
    }

    // Atualiza o texto da pontuação
    private void atualizarPontuacao() {

        labelPontos.setText("Pontuação: " + pontos);
    }

    // Ajusta fonte da pontuação dependendo do tamanho da tela
    private void atualizarFontePontuacao() {

        int largura = getWidth();

        if (largura >= 1200) {
            labelPontos.setFont(new Font("Monospaced", Font.BOLD, 26));
        } else {
            labelPontos.setFont(new Font("Monospaced", Font.BOLD, 16));
        }
    }

    // Cria botão de próxima cena
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

    // Cria botão de continuar na introdução
    private void criarBotaoContinuar() {

        btnContinuar = new JButton("[ESPAÇO] Continuar");
        estiloBotao(btnContinuar);

        btnContinuar.addActionListener(e -> {
            cenaAtual = 2;
            setCena(cenaAtual);
        });

        add(btnContinuar);
    }

    // Cria menu inicial
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

    // Cria botões de opção (sem funcionalidade ainda)
    private void criarBotoesOpcao() {

        opcao1 = new JButton("Opção 1");
        opcao2 = new JButton("Opção 2");
        opcao3 = new JButton("Opção 3");

        estiloBotao(opcao1);
        estiloBotao(opcao2);
        estiloBotao(opcao3);

        // Ainda não possuem lógica de escolha
        opcao1.addActionListener(e -> {});
        opcao2.addActionListener(e -> {});
        opcao3.addActionListener(e -> {});

        caixaDialogo.add(opcao1);
        caixaDialogo.add(opcao2);
        caixaDialogo.add(opcao3);
    }

    // Define estilo padrão dos botões
    private void estiloBotao(JButton btn) {

        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new LineBorder(Color.WHITE, 3));
        btn.setFocusPainted(false);
        btn.setFont(new Font("Monospaced", Font.BOLD, 13));
    }

    // Atualiza layout dos elementos na tela
    private void atualizarLayout() {

        int largura = getWidth();
        int altura = getHeight();

        caixaDialogo.setBounds(20, altura - 180, largura - 40, 160);

        textoDialogo.setBounds(20, 15, largura - 260, 100);

        int y = 90;

        opcao1.setBounds(largura / 2 - 220, y, 120, 45);
        opcao2.setBounds(largura / 2 - 90, y, 120, 45);
        opcao3.setBounds(largura / 2 + 40, y, 120, 45);

        btnProxima.setBounds(largura / 2 + 170, y, 160, 45);

        btnIniciar.setBounds(largura / 2 - 120, altura - 220, 240, 50);
        btnSair.setBounds(largura / 2 - 120, altura - 150, 240, 50);

        btnContinuar.setBounds(20, altura - 70, 220, 38);

        int larguraLabel = (largura >= 1200) ? 320 : 220;

        labelPontos.setBounds(largura - larguraLabel - 20, 20, larguraLabel, 50);

        atualizarFontePontuacao();
    }

    // Renderização do painel
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        atualizarLayout();

        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
