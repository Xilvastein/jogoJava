import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

public class Scene extends JPanel {

    // Imagem de fundo do cenário atual
    private Image bg;

    // Controle da cena atual do jogo
    private int cenaAtual = 0;

    // Painel de diálogo inferior
    private JPanel caixaDialogo;
    JPanel caixaDialogo2;
    JPanel caixaDialogo3;
    JPanel caixaDialogo4;
    JPanel caixaDialogoFinal;
    JPanel caixaAlertaPositivo;
    JPanel caixaAlertaRegular;
    JPanel caixaAlertaNegativo;

    // Texto exibido no diálogo
    private JTextArea textoDialogo;
    JTextArea textoDialogo2;
    JTextArea textoDialogo3;
    JTextArea textoDialogo4;
    JTextArea textoDialogoFinal;
    JTextArea textoAlertaPositivo;
    JTextArea textoAlertaRegular;
    JTextArea textoAlertaNegativo;
    private String textoOpcao1 = "";
    private String textoOpcao2 = "";
    private String textoOpcao3 = "";

    // Label da pontuação no canto superior direito
    private JLabel labelPontos;

    // Botão de continuar na introdução
    private JButton btnContinuar;
    private JButton btnContinuar2;

    // Botões do menu inicial
    private JButton btnIniciar;
    private JButton btnSair;

    // Botões de opção (visuais apenas por enquanto)
    private JButton opcao1;
    private JButton opcao2;
    private JButton opcao3;
    private JButton sim1;
    private JButton nao1;
    private JButton sim2;
    private JButton nao2;
    private JButton sim3;
    private JButton nao3;
    private JButton ok1;
    private JButton ok2;
    private JButton ok3;
    
    public int pontuacao = 0;
    public int pontos;
    
    private Timer timerDigitacao;

    public Scene() {

        // Usa layout manual (posição absoluta dos elementos)
        setLayout(null);

        // Criação dos elementos da interface
        criarDialogo();
        criarPontuacao();
        criarBotaoContinuar();
        criarBotaoContinuar2();
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
        caixaDialogo2.setVisible(false);
        caixaDialogo3.setVisible(false);
        caixaDialogo4.setVisible(false);
        caixaDialogoFinal.setVisible(false);
        caixaAlertaPositivo.setVisible(false);
        caixaAlertaRegular.setVisible(false);
        caixaAlertaNegativo.setVisible(false);
        
        btnContinuar.setVisible(false);
        btnContinuar2.setVisible(false);
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
        else if (cena == 7) {
        	caixaDialogoFinal.setVisible(true);
        	textoDialogoFinal.setVisible(true);
        	btnContinuar2.setVisible(true);
        	if(pontuacao <= 20) {
        		textoDialogoFinal.setForeground(Color.RED);
        		textoDialogoFinal.setText("----- SUA PONTUAÇÃO FINAL FOI " + pontuacao + " PONTOS -----" + "\nSeu conhecimento sobre violência doméstica ainda é limitado. Algumas atitudes podem colocar as vítimas em situações ainda mais perigosas.\n" +
        	"\nA violência doméstica não é apenas física. Ela também pode ser psicológica, emocional, financeira e social. Muitas vítimas permanecem em silêncio por medo, dependência emocional ou falta de apoio adequado. Reconhecer os sinais, acolher sem julgamentos e incentivar a busca por ajuda pode salvar vidas. Se você ou alguém próximo estiver passando por isso, denuncie. Ligue 180.\n");
        } else if (pontuacao >= 20 && pontuacao <=35) {
        		textoDialogoFinal.setForeground(Color.YELLOW);
        		textoDialogoFinal.setText("----- SUA PONTUAÇÃO FINAL FOI " + pontuacao + " PONTOS -----" + "\nVocê reconhece parte dos sinais da violência doméstica, mas ainda possui dúvidas sobre formas seguras de acolhimento e apoio.\n" +
        				"\nA violência doméstica não é apenas física. Ela também pode ser psicológica, emocional, financeira e social. Muitas vítimas permanecem em silêncio por medo, dependência emocional ou falta de apoio adequado. Reconhecer os sinais, acolher sem julgamentos e incentivar a busca por ajuda pode salvar vidas. Se você ou alguém próximo estiver passando por isso, denuncie. Ligue 180.\n");
        } else {
        		textoDialogoFinal.setForeground(Color.GREEN);
        		textoDialogoFinal.setText("----- SUA PONTUAÇÃO FINAL FOI " + pontuacao + " PONTOS -----" + "\nParabéns. Você demonstrou consciência sobre sinais de abuso e sobre formas adequadas de acolher, proteger e orientar vítimas de violência doméstica.\n" +
        				"\nA violência doméstica não é apenas física. Ela também pode ser psicológica, emocional, financeira e social. Muitas vítimas permanecem em silêncio por medo, dependência emocional ou falta de apoio adequado. Reconhecer os sinais, acolher sem julgamentos e incentivar a busca por ajuda pode salvar vidas. Se você ou alguém próximo estiver passando por isso, denuncie. Ligue 180.\n");
        }
        } else if (cena == 8) {
        	btnSair.setVisible(true);
        }
        else {

            caixaDialogo.setVisible(true);

            opcao1.setVisible(true);
            opcao2.setVisible(true);
            opcao3.setVisible(true);

            labelPontos.setVisible(true);

            // Atualiza texto da pontuação sem alterar valor
            atualizarPontuacao();

            // Define o texto de cada cena
            switch (cena) {

                case 2 -> animarTexto(textoDialogo, "Júlia percebe que Mariana aparece frequentemente com mangas longas mesmo em dias muito quentes, evita conversar sobre sua vida pessoal e demonstra medo ao receber mensagens no celular. Após uma conversa reservada, Mariana revela que o marido controla sua rotina, suas finanças e teve um comportamento agressivo recentemente.");
                case 3 -> animarTexto(textoDialogo, "Após sair temporariamente de casa, Mariana recebe diversas mensagens do agressor pedindo desculpas, afirmando estar arrependido e prometendo mudar. Ela fica emocionalmente abalada e não sabe como agir.");
                case 4 -> animarTexto(textoDialogo, "Mesmo após conseguir medida protetiva, Mariana percebe que o agressor continua observando sua rotina e enviando mensagens ameaçadoras.");
                case 5 -> animarTexto(textoDialogo, "Durante uma reunião familiar, alguns parentes afirmam que Mariana deveria preservar a família e reconsiderar a denúncia.");
                case 6 -> animarTexto(textoDialogo, "Meses depois, Mariana começa a reconstruir sua vida. Certo dia, uma colega revela que também vive um relacionamento abusivo e pede conselhos.");		
                }
            }
            
         // Define o texto de cada PopUp
	        switch (cena) {
	
		        case 2 -> textoOpcao1 = "Acolher Mariana sem julgamentos, ajudá-la a construir um plano de segurança e orientá-la a buscar ajuda especializada, como a DEAM e o Ligue 180.";
		        case 3 -> textoOpcao1 = "Explicar sobre o ciclo da violência, reforçar a importância da segurança emocional e incentivá-la a formalizar medidas protetivas.";
		        case 4 -> textoOpcao1 = "Reunir provas das ameaças, registrar imediatamente o descumprimento da medida protetiva e buscar apoio jurídico e psicológico especializado.";
		        case 5 -> textoOpcao1 = "Reforçar que nenhuma forma de violência é justificável e incentivar a continuidade do acompanhamento psicológico e jurídico.";
		        case 6 -> textoOpcao1 = "Ouvir sem julgamentos, orientar sobre redes de apoio e incentivar a busca por ajuda especializada, como a DEAM e o Ligue 180.";
		    }
	            
            switch (cena) {

            case 2 -> textoOpcao2 = "Recomendar que Mariana converse com pessoas próximas e procure apoio emocional antes de decidir se deseja denunciar o agressor.";
            case 3 -> textoOpcao2 = "Sugerir que Mariana converse com profissionais especializados antes de decidir se deseja retomar ou encerrar definitivamente a relação.";
            case 4 -> textoOpcao2 = "Orientar Mariana a informar familiares e colegas próximos sobre a situação enquanto avalia novas medidas de proteção.";
            case 5 -> textoOpcao2 = "Incentivar Mariana a refletir sobre sua segurança emocional e sobre os impactos do ambiente familiar abusivo.";
            case 6 -> textoOpcao2 = "Recomendar que a colega converse com pessoas de confiança antes de tomar qualquer decisão importante.";
        }
            switch (cena) {

            case 2 -> textoOpcao3 = "Sugerir que Mariana tente evitar conflitos dentro de casa por enquanto, observando melhor a situação antes de tomar qualquer atitude mais séria.";
            case 3 -> textoOpcao3 = "Orientar Mariana a manter contato apenas por mensagens enquanto observa se as mudanças do agressor serão realmente duradouras.";
            case 4 -> textoOpcao3 = "Aconselhar Mariana a ignorar as provocações temporariamente para evitar aumentar os conflitos.";
            case 5 -> textoOpcao3 = "Sugerir que Mariana tente ouvir a opinião da família antes de tomar decisões definitivas.";
            case 6 -> textoOpcao3 = "Dizer que cada pessoa possui seu próprio tempo para lidar com relacionamentos difíceis e que ela deve pensar cuidadosamente antes de agir.";
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
        textoDialogo.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoDialogo.setLineWrap(true);
        textoDialogo.setWrapStyleWord(true);
        textoDialogo.setEditable(false);
        textoDialogo.setOpaque(false);
        
       

        caixaDialogo.add(textoDialogo);
        add(caixaDialogo);
        
        caixaDialogo2 = new JPanel();
    	caixaDialogo2.setLayout(null);
        caixaDialogo2.setBackground(new Color(0, 0, 0, 220));
        caixaDialogo2.setBorder(new LineBorder(Color.WHITE, 4));
       
        textoDialogo2 = new JTextArea();
        textoDialogo2.setForeground(Color.WHITE);
        textoDialogo2.setBackground(new Color(0, 0, 0, 0));
        textoDialogo2.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoDialogo2.setLineWrap(true);
        textoDialogo2.setWrapStyleWord(true);
        textoDialogo2.setEditable(false);
        textoDialogo2.setOpaque(false);
        caixaDialogo2.add(textoDialogo2);
        add(caixaDialogo2);
        
        criarBotoesOpcao1();
        
        caixaDialogo3 = new JPanel();
    	caixaDialogo3.setLayout(null);
        caixaDialogo3.setBackground(new Color(0, 0, 0, 220));
        caixaDialogo3.setBorder(new LineBorder(Color.WHITE, 4));
       
        textoDialogo3 = new JTextArea();
        textoDialogo3.setForeground(Color.WHITE);
        textoDialogo3.setBackground(new Color(0, 0, 0, 0));
        textoDialogo3.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoDialogo3.setLineWrap(true);
        textoDialogo3.setWrapStyleWord(true);
        textoDialogo3.setEditable(false);
        textoDialogo3.setOpaque(false);
        caixaDialogo3.add(textoDialogo3);
        add(caixaDialogo3);
        
        criarBotoesOpcao2();
        
        caixaDialogo4 = new JPanel();
        caixaDialogo4.setLayout(null);
        caixaDialogo4.setBackground(new Color(0, 0, 0, 220));
        caixaDialogo4.setBorder(new LineBorder(Color.WHITE, 4));
       
        textoDialogo4 = new JTextArea();
        textoDialogo4.setForeground(Color.WHITE);
        textoDialogo4.setBackground(new Color(0, 0, 0, 0));
        textoDialogo4.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoDialogo4.setLineWrap(true);
        textoDialogo4.setWrapStyleWord(true);
        textoDialogo4.setEditable(false);
        textoDialogo4.setOpaque(false);
        caixaDialogo4.add(textoDialogo4);
        add(caixaDialogo4);
        
        criarBotoesOpcao3();
        
        caixaAlertaPositivo = new JPanel();
        caixaAlertaPositivo.setLayout(null);
        caixaAlertaPositivo.setBackground(new Color(0, 0, 0, 220));
        caixaAlertaPositivo.setBorder(new LineBorder(Color.WHITE, 4));
        
        caixaAlertaRegular = new JPanel();
        caixaAlertaRegular.setLayout(null);
        caixaAlertaRegular.setBackground(new Color(0, 0, 0, 220));
        caixaAlertaRegular.setBorder(new LineBorder(Color.WHITE, 4));
        
        caixaAlertaNegativo = new JPanel();
        caixaAlertaNegativo.setLayout(null);
        caixaAlertaNegativo.setBackground(new Color(0, 0, 0, 220));
        caixaAlertaNegativo.setBorder(new LineBorder(Color.WHITE, 4));
        
        textoAlertaPositivo = new JTextArea();
        textoAlertaPositivo.setForeground(Color.GREEN);
        textoAlertaPositivo.setBackground(new Color(0, 0, 0, 0));
        textoAlertaPositivo.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoAlertaPositivo.setLineWrap(true);
        textoAlertaPositivo.setWrapStyleWord(true);
        textoAlertaPositivo.setEditable(false);
        textoAlertaPositivo.setOpaque(false);
        caixaAlertaPositivo.add( textoAlertaPositivo);
        add(caixaAlertaPositivo);
        
        criarBotoesOk1();
        
        textoAlertaRegular = new JTextArea();
        textoAlertaRegular.setForeground(Color.YELLOW);
        textoAlertaRegular.setBackground(new Color(0, 0, 0, 0));
        textoAlertaRegular.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoAlertaRegular.setLineWrap(true);
        textoAlertaRegular.setWrapStyleWord(true);
        textoAlertaRegular.setEditable(false);
        textoAlertaRegular.setOpaque(false);
        caixaAlertaRegular.add( textoAlertaRegular);
        add(caixaAlertaRegular);
        
        criarBotoesOk2();
        
        textoAlertaNegativo = new JTextArea();
        textoAlertaNegativo.setForeground(Color.RED);
        textoAlertaNegativo.setBackground(new Color(0, 0, 0, 0));
        textoAlertaNegativo.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoAlertaNegativo.setLineWrap(true);
        textoAlertaNegativo.setWrapStyleWord(true);
        textoAlertaNegativo.setEditable(false);
        textoAlertaNegativo.setOpaque(false);
        caixaAlertaNegativo.add( textoAlertaNegativo);
        add(caixaAlertaNegativo);
        
        criarBotoesOk3();
      
        caixaDialogoFinal = new JPanel();
        caixaDialogoFinal.setLayout(null);
        caixaDialogoFinal.setBackground(new Color(0, 0, 0, 220));
        caixaDialogoFinal.setBorder(new LineBorder(Color.WHITE, 4));
        
        textoDialogoFinal = new JTextArea();
        textoDialogoFinal.setBackground(new Color(0, 0, 0, 0));
        textoDialogoFinal.setFont(new Font("Monospaced", Font.BOLD, 15));
        textoDialogoFinal.setLineWrap(true);
        textoDialogoFinal.setWrapStyleWord(true);
        textoDialogoFinal.setEditable(false);
        textoDialogoFinal.setOpaque(false);
        
       

        caixaDialogoFinal.add(textoDialogoFinal);
        add(caixaDialogoFinal);
    }
    
    public void animarTexto(JTextArea textArea, String textoCompleto) {
    	if(timerDigitacao != null && timerDigitacao.isRunning()) {
    		timerDigitacao.stop();
    	}
    	textArea.setText("");
    	final int[] index = {1};
    	
    	timerDigitacao = new Timer(30, e -> {
    		if(index[0] <= textoCompleto.length()) {
    			textArea.setText(textoCompleto.substring(0, index[0]));
    			index[0]++;
    			repaint();
    		} else {
    			((Timer) e.getSource()).stop();
    		}
    	});
    	timerDigitacao.start();
    }
    
    // Cria label de pontuação
    private void criarPontuacao() {

        labelPontos = new JLabel("Pontuação: " + pontuacao);
        labelPontos.setForeground(Color.WHITE);
        labelPontos.setHorizontalAlignment(SwingConstants.RIGHT);

        atualizarFontePontuacao();

        add(labelPontos);
    }

    // Atualiza o texto da pontuação
    private void atualizarPontuacao() {

        labelPontos.setText("Pontuação: " + pontuacao);
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
    
    private void criarBotaoContinuar2() {

        btnContinuar2 = new JButton("Continuar");
        estiloBotao(btnContinuar2);

        btnContinuar2.addActionListener(e -> {
            cenaAtual = 8;
            setCena(cenaAtual);
        });

        add(btnContinuar2);
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

    // Cria botões de opção
    private void criarBotoesOpcao() {

        opcao1 = new JButton("Opção 1");
        opcao2 = new JButton("Opção 2");
        opcao3 = new JButton("Opção 3");

        estiloBotao(opcao1);
        estiloBotao(opcao2);
        estiloBotao(opcao3);

        // Ainda não possuem lógica de escolha
        opcao1.addActionListener(e -> {
        	caixaDialogo.setVisible(false);
        	caixaDialogo2.setVisible(true);
        	animarTexto(textoDialogo2, textoOpcao1);
        });
        opcao2.addActionListener(e -> {
        	caixaDialogo.setVisible(false);
        	caixaDialogo3.setVisible(true);
        	animarTexto(textoDialogo3, textoOpcao2);
        });
        opcao3.addActionListener(e -> {
        	caixaDialogo.setVisible(false);
        	caixaDialogo4.setVisible(true);
        	animarTexto(textoDialogo4, textoOpcao3);
        });

        caixaDialogo.add(opcao1);
        caixaDialogo.add(opcao2);
        caixaDialogo.add(opcao3);
        
    
    }
    
    private void criarBotoesOpcao1() {

        sim1 = new JButton("Sim");
        sim1.addActionListener(e -> {
        	caixaDialogo2.setVisible(false);
        	caixaAlertaPositivo.setVisible(true);
        	pontos = ThreadLocalRandom.current().nextInt(6,11+1);
    		pontuacao+=pontos;
        	animarTexto(textoAlertaPositivo, "----- GANHOU " + pontos + " PONTOS -----\n" + "Sua escolha foi excelente. Você decidiu acolher a pessoa em situação de vulnerabilidade com empatia e sem julgamentos, auxiliando ativamente na construção de estratégias de proteção imediatas e direcionando-a de forma segura para as redes de apoio institucionais e canais de denúncia especializados.");
        	ok1.setVisible(true);
        });
        nao1 = new JButton("Não");
        nao1.addActionListener(e -> {
        	caixaDialogo2.setVisible(false);
        	caixaDialogo.setVisible(true);
        });
        estiloBotao(sim1);
        estiloBotao(nao1);
       
        caixaDialogo2.add(sim1);
        caixaDialogo2.add(nao1);
        
        sim1.setVisible(true);
        nao1.setVisible(true);
     }
    
    private void criarBotoesOpcao2() {

        sim2 = new JButton("Sim");
        sim2.addActionListener(e -> {
        	caixaDialogo3.setVisible(false);
        	caixaAlertaRegular.setVisible(true);
        	pontos = ThreadLocalRandom.current().nextInt(1,5+1);
    		pontuacao+=pontos;
        	animarTexto(textoAlertaRegular, "----- GANHOU " + pontos + " PONTOS -----\n" + "Sua escolha foi mediana. Você buscou agir com cautela ao incentivar a reflexão sobre a gravidade da situação, sugerindo que ela converse com pessoas de confiança ou busque o suporte de profissionais especializados antes de tomar qualquer atitude definitiva.");
        	ok2.setVisible(true);
        });
        nao2 = new JButton("Não");
        nao2.addActionListener(e -> {
        	caixaDialogo3.setVisible(false);
        	caixaDialogo.setVisible(true);
        });
        estiloBotao(sim2);
        estiloBotao(nao2);
       
        caixaDialogo3.add(sim2);
        caixaDialogo3.add(nao2);
        
        sim2.setVisible(true);
        nao2.setVisible(true);
     }
    
    private void criarBotoesOpcao3() {

        sim3 = new JButton("Sim");
        sim3.addActionListener(e -> {
        	caixaDialogo4.setVisible(false);
        	caixaAlertaNegativo.setVisible(true);
        	pontos = ThreadLocalRandom.current().nextInt(-10,0+1);
    		pontuacao+=pontos;
        	animarTexto(textoAlertaNegativo, "----- PERDEU " + Math.abs(pontos) + " PONTOS -----\n" + "Sua escolha foi ruim e perigosa. Você acabou aconselhando a passividade e minimizando a gravidade do problema, sugerindo que ela ignore os sinais de abuso ou evite confrontos na falsa expectativa de que a situação se resolva sozinha com o tempo.");
        	ok3.setVisible(true);
        });
        nao3 = new JButton("Não");
        nao3.addActionListener(e -> {
        	caixaDialogo4.setVisible(false);
        	caixaDialogo.setVisible(true);;
        });
        estiloBotao(sim3);
        estiloBotao(nao3);
       
        caixaDialogo4.add(sim3);
        caixaDialogo4.add(nao3);
        
        sim3.setVisible(true);
        nao3.setVisible(true);
     }
    
    private void criarBotoesOk1() {
    	ok1 = new JButton("OK");
    	ok1.addActionListener(e -> {
    		cenaAtual++;

            if (cenaAtual > 7) {
                cenaAtual = 0;
            }

       
            setCena(cenaAtual);
    	});
    	estiloBotao(ok1);
    	caixaAlertaPositivo.add(ok1);
    	ok1.setVisible(false);
    }
    
    private void criarBotoesOk2() {
    	ok2 = new JButton("OK");
    	ok2.addActionListener(e -> {
    		cenaAtual++;

            if (cenaAtual > 7) {
                cenaAtual = 0;
            }

     
            setCena(cenaAtual);
    	});
    	estiloBotao(ok2);
    	caixaAlertaRegular.add(ok2);
    	ok2.setVisible(false);
    }
    
    private void criarBotoesOk3() {
    	ok3 = new JButton("OK");
    	ok3.addActionListener(e -> {
    		cenaAtual++;

            if (cenaAtual > 7) {
                cenaAtual = 0;
            }

 
            setCena(cenaAtual);
    	});
    	estiloBotao(ok3);
    	caixaAlertaNegativo.add(ok3);
    	ok3.setVisible(false);
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

        caixaDialogo.setBounds(20, altura - 200, largura - 80, 175);
        caixaDialogo2.setBounds(20, altura - 200, largura - 80, 175);
        caixaDialogo3.setBounds(20, altura - 200, largura - 80, 175);
        caixaDialogo4.setBounds(20, altura - 200, largura - 80, 175);
        caixaAlertaPositivo.setBounds(20, altura - 200, largura - 80, 175);
        caixaAlertaRegular.setBounds(20, altura - 200, largura - 80, 175);
        caixaAlertaNegativo.setBounds(20, altura - 200, largura - 80, 175);


        textoDialogo.setBounds(20, 15, largura - 260, 70);
        textoDialogo2.setBounds(20, 15, largura - 260, 70);
        textoDialogo3.setBounds(20, 15, largura - 260, 70);
        textoDialogo4.setBounds(20, 15, largura - 260, 70);
        textoAlertaPositivo.setBounds(20, 15, largura - 260, 100);
        textoAlertaRegular.setBounds(20, 15, largura - 260, 100);
        textoAlertaNegativo.setBounds(20, 15, largura - 260, 100);
        
        

        int y = 100;

        sim1.setBounds(largura / 2 - 220, y, 120, 45);
        nao1.setBounds(largura / 2 + 40, y, 120, 45);
        sim2.setBounds(largura / 2 - 220, y, 120, 45);
        nao2.setBounds(largura / 2 + 40, y, 120, 45);
        sim3.setBounds(largura / 2 - 220, y, 120, 45);
        nao3.setBounds(largura / 2 + 40, y, 120, 45);
        ok1.setBounds(largura / 2 - 90, y, 120, 45);
        ok2.setBounds(largura / 2 - 90, y, 120, 45);
        ok3.setBounds(largura / 2 - 90, y, 120, 45);
        opcao1.setBounds(largura / 2 - 220, y, 120, 45);
        opcao2.setBounds(largura / 2 - 90, y, 120, 45);
        opcao3.setBounds(largura / 2 + 40, y, 120, 45);
        btnIniciar.setBounds(largura / 2 - 120, altura - 220, 240, 50);

        int larguraCaixaFinal = largura - 160; 
        int alturaCaixaFinal = 400;
        int xCaixaFinal = (largura - larguraCaixaFinal) / 2;
        int yCaixaFinal = (altura - alturaCaixaFinal) / 2;
        caixaDialogoFinal.setBounds(xCaixaFinal, yCaixaFinal, larguraCaixaFinal, alturaCaixaFinal);
        textoDialogoFinal.setBounds(30, 30, larguraCaixaFinal - 60, alturaCaixaFinal - 110);


        if (cenaAtual == 0) {
            btnSair.setBounds(largura / 2 - 120, altura - 150, 240, 50);
        } else if (cenaAtual == 8) {
            int yBotaoSair = yCaixaFinal + alturaCaixaFinal - 70;
            btnSair.setBounds(largura - 240, altura - 70, 220, 38);
        }

        btnContinuar.setBounds(20, altura - 70, 220, 38);
        btnContinuar2.setBounds(20, altura - 70, 240, 50);

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



