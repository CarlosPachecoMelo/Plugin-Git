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
public class AcaoAdicionarRemoto extends AbstractAction {
    public AcaoAdicionarRemoto() {
        super("Ação Adicionar Remoto", carregarIcone());
    }

    private static Icon carregarIcone() {
        try {
            String caminho = "br/univali/portugol/plugin/git/imagens/remote.png";
            Image imagem = ImageIO.read(AcaoAdicionarRemoto.class.getClassLoader().getResourceAsStream(caminho));

            return new ImageIcon(imagem);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(null, "Informe o endereço remoto (HTTPS)");
            String usuario = JOptionPane.showInputDialog(null, "Informe o usuário");
            String senha = JOptionPane.showInputDialog(null, "Informe a senha");
            if(input != null && usuario != null && senha != null) {
                GitCommands.getInstance().gitRemoteAdd(input, usuario, senha);
                JOptionPane.showMessageDialog(null, "Endereço remoto adicionado", "Plugin Git", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Dados inválidos", "Plugin Git", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Plugin Git", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
