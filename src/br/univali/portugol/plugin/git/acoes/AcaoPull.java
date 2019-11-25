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
public class AcaoPull extends AbstractAction {
    public AcaoPull() {
        super("Ação Pull", carregarIcone());
    }

    private static Icon carregarIcone() {
        try {
            String caminho = "br/univali/portugol/plugin/git/imagens/pull.png";
            Image imagem = ImageIO.read(AcaoPull.class.getClassLoader().getResourceAsStream(caminho));

            return new ImageIcon(imagem);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GitCommands.getInstance().gitPull();
            JOptionPane.showMessageDialog(null, "Pull realizado", "Plugin Git", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Plugin Git", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
