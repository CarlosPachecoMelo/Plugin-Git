package br.univali.portugol.plugin.git.acoes;

import br.univali.portugol.plugin.git.GitCommands;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Cadu Pacheco
 * @author Chrystian Batista
 */
public class AcaoCommit extends AbstractAction {
    public AcaoCommit() {
        super("Ação Commit", carregarIcone());
    }

    private static Icon carregarIcone() {
        try {
            String caminho = "br/univali/portugol/plugin/git/imagens/commit.png";
            Image imagem = ImageIO.read(AcaoCommit.class.getClassLoader().getResourceAsStream(caminho));

            return new ImageIcon(imagem);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(null, "Informe a mensagem do commit");
            GitCommands.getInstance().gitCommit(input);
            JOptionPane.showMessageDialog(null, "Commit realizado", "Plugin Git", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Plugin Git", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
