import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaDeJogo extends JFrame{
    private JPanel painelJogo; // painel do jogo. O nome é definido no modo Design, em "field name"
    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;

    public JanelaDeJogo(CampoMinado campoMinado) {
        this.campoMinado = campoMinado;
        var nrLinhas = campoMinado.getNrLinhas();
        var nrColunas = campoMinado.getNrColunas();
        this.botoes = new BotaoCampoMinado[nrLinhas][nrColunas];
        painelJogo.setLayout(new GridLayout(nrLinhas, nrColunas));
        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                botoes[linha][coluna] = new BotaoCampoMinado(linha,coluna);
                botoes[linha][coluna].addActionListener(this::btnCampoMinadoActionPerformed);
                painelJogo.add(botoes[linha][coluna]);
            }
        }
        setContentPane(painelJogo);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents
        pack();
        setVisible(true);
    }
    public void btnCampoMinadoActionPerformed(ActionEvent e) {
        var botao = (BotaoCampoMinado) e.getSource();
        var x = botao.getLinha();
        var y = botao.getColuna();
        campoMinado.revelarQuadricula(x, y);
        actualizarEstadoBotoes();
        if (campoMinado.isJogoTerminado()) {
            if (campoMinado.isJogadorDerrotado())
                JOptionPane.showMessageDialog(null, "Oh, rebentou uma mina",
                        "Perdeu!", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Parabéns. Conseguiu descobrir todas as minas em "+ (campoMinado.getDuracaoJogo()/1000)+" segundos", "Vitória", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }

    }

    public JanelaDeJogo() {}

    public class BotaoCampoMinado extends JButton {
        private int estado;
        private int linha;
        private int coluna;

        public BotaoCampoMinado(int linha, int coluna) {
            this.estado = CampoMinado.TAPADO;
            this.linha=linha;
            this.coluna=coluna;
        }

        public void setEstado(int estado) {
            this.estado = estado;
            switch (estado) {
                case CampoMinado.VAZIO:
                    setText("");
                    setBackground(Color.LIGHT_GRAY);
                    break;
                case CampoMinado.TAPADO:
                    setText("");
                    setBackground(null);
                    break;
                case CampoMinado.DUVIDA:
                    setText("?");
                    setBackground(Color.yellow);
                    break;
                case CampoMinado.MARCADO:
                    setText("!");
                    setBackground(Color.red);
                    break;
                case CampoMinado.REBENTADO:
                    setText("*");
                    setBackground(Color.yellow);
                    break;
                default:
                    setText(String.valueOf(estado));
                    setBackground(Color.LIGHT_GRAY);
            }
        }

        public int getLinha() {
            return linha;
        }

        public int getColuna() {
            return coluna;
        }
    }

    private void actualizarEstadoBotoes() {
        for (int x = 0; x < campoMinado.getNrLinhas(); x++) {
            for (int y = 0; y < campoMinado.getNrColunas(); y++) {
                botoes[x][y].setEstado(campoMinado.getEstadoQuadricula(x, y));
            }
        }
    }
}
