package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PomodoroApp extends JFrame {
    private Pomodoro pomodoro;

    private JLabel titulo;
    private JLabel cronometro;
    private JLabel labelStatusCiclo;
    private JButton botaoIniciar;
    private JSpinner selectorCiclos;
    private Timer timer;

    public PomodoroApp() {
        pomodoro = new Pomodoro(4);

        setTitle("LuiX-Pomodor");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("logo.png"));
            setIconImage(icone.getImage());
        } catch (Exception e) {
            System.out.println("Erro ao carregar ícone: " + e.getMessage());
            // Ícone padrão caso não encontre
        }

        // Timer instanciado primeiro para evitar NullPointerException
        timer = new Timer(1000, e -> {
            boolean faseEncerrada = pomodoro.tick();
            
            if (!faseEncerrada) {
                atualizarCronometro();
            } else {
                timer.stop();
                
                if (pomodoro.isConcluido()) {
                    JOptionPane.showMessageDialog(this, "Parabéns! Você concluiu todos os ciclos!");
                    resetarInterface();
                } else {
                    tratarTrocaDeFase();
                }
            }
        });

        JPanel painel = new JPanel(new GridLayout(5, 1, 5, 5));
        painel.setBackground(new Color(0, 0, 0));

        titulo = new JLabel("HORA DE FOCAR", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        // Inicializa o cronômetro com valor padrão
        cronometro = new JLabel("25:00", SwingConstants.CENTER);

        JPanel painelCiclos = new JPanel(new FlowLayout());
        painelCiclos.setBackground(new Color(0, 0, 0));

        labelStatusCiclo = new JLabel("Ciclo: 1 / 4  |  Total:");
        labelStatusCiclo.setForeground(Color.LIGHT_GRAY);

        selectorCiclos = new JSpinner(new SpinnerNumberModel(4, 1, 10, 1));
        selectorCiclos.addChangeListener(e -> {
            if (timer != null && !timer.isRunning()) {
                pomodoro.setTotalCiclos((int) selectorCiclos.getValue());
                atualizarTextoCiclo();
            }
        });

        painelCiclos.add(labelStatusCiclo);
        painelCiclos.add(selectorCiclos);

        botaoIniciar = new JButton("Iniciar");
        JButton botaoReiniciar = new JButton("Reiniciar");

        botaoIniciar.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                botaoIniciar.setText("Continuar");
                selectorCiclos.setEnabled(true);
            } else {
                timer.start();
                botaoIniciar.setText("Pausar");
                selectorCiclos.setEnabled(false);
            }
        });

        botaoReiniciar.addActionListener(e -> resetarInterface());

        painel.add(titulo);
        painel.add(cronometro);
        painel.add(painelCiclos);
        painel.add(botaoIniciar);
        painel.add(botaoReiniciar);

        add(painel);

        // OUVINTE DE REDIMENSIONAMENTO DA JANELA
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarEstiloCronometro();
            }
        });

        // Aplica o estilo inicial com base no tamanho inicial da janela
        ajustarEstiloCronometro();
    }

    private void ajustarEstiloCronometro() {
        if (cronometro == null) return;

        // Se a largura for até 480 OU a altura for até 420, usa a fonte menor
        if (getWidth() <= 580 || getHeight() <= 520) {
            cronometro.setForeground(new Color(255, 30, 30));
            cronometro.setFont(new Font("Monospaced", Font.BOLD, 54));
        } else {
            cronometro.setForeground(new Color(255, 255, 255));
            cronometro.setFont(new Font("Monospaced", Font.BOLD, 114));
        }
    }

    private void tratarTrocaDeFase() {
        if (!pomodoro.isEmPausa()) {
            pomodoro.alternarFase();
            titulo.setText("HORA DA PAUSA (5 MIN)");
            titulo.setForeground(new Color(25,255,150));
            titulo.setFont(new Font("SansSerif", Font.BOLD, 48));
            JOptionPane.showMessageDialog(this, "Excelente trabalho! Faça uma pausa de 5 minutos.");
        } else {
            pomodoro.alternarFase();
            titulo.setText("HORA DE FOCAR");
            titulo.setForeground(new Color(255,255,255));
            titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
            JOptionPane.showMessageDialog(this, "Pausa terminada! De volta ao foco.");
        }
        
        atualizarTextoCiclo();
        atualizarCronometro();
        timer.start();
    }

    private void resetarInterface() {
        timer.stop();
        pomodoro.setTotalCiclos((int) selectorCiclos.getValue());
        pomodoro.resetar();

        titulo.setText("HORA DE FOCAR");
        
        atualizarCronometro();
        atualizarTextoCiclo();
        
        botaoIniciar.setText("Iniciar");
        selectorCiclos.setEnabled(true);
    }

    private void atualizarTextoCiclo() {
        labelStatusCiclo.setText(String.format("Ciclo: %d / %d  |  Total:", 
            pomodoro.getCicloAtual(), pomodoro.getTotalCiclos()));
    }

    private void atualizarCronometro() {
        int minutos = pomodoro.getTempoRestante() / 60;
        int segundos = pomodoro.getTempoRestante() % 60;
        cronometro.setText(String.format("%02d:%02d", minutos, segundos));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PomodoroApp().setVisible(true);
        });
    }
}