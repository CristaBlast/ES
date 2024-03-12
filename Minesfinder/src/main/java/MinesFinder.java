import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame{
    private JPanel painelPrincipal;
    private JButton hardBtn;
    private JButton easyBtn;
    private JButton normalBtn;
    private JButton exitBtn;
    private JPanel JPanelWest;
    private JPanel JPanelButtons;

    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        exitBtn.addActionListener(this::btnSairActionPerformed);
        easyBtn.addActionListener(this::btnJogoFacilActionPerformed);
        normalBtn.addActionListener(this::btnJogoMedioActionPerformed);
        hardBtn.addActionListener(this::btnJogoDificilActionPerformed);
    }

    public void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnJogoFacilActionPerformed(java.awt.event.ActionEvent evt) {
        var janela = new JanelaDeJogo(new CampoMinado(9,9, 10));
    }
    private void btnJogoMedioActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,16, 40));
    }
    private void btnJogoDificilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,30, 90));
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
        var janela = new JanelaDeJogo(new CampoMinado());
        janela.setVisible(true);
    }

}
