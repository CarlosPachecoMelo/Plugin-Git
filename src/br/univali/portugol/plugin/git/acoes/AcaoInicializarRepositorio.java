package br.univali.portugol.plugin.git.acoes;

import br.univali.portugol.plugin.git.GitCommands;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Cadu Pacheco
 * @author Chrystian Batista
 */
public class AcaoInicializarRepositorio extends AbstractAction {
    public AcaoInicializarRepositorio() {
        super("Ação Inicializar Repositorio", carregarIcone());
    }

    private static Icon carregarIcone() {
        try {
            String caminho = "br/univali/portugol/plugin/git/imagens/init.png";
            Image imagem = ImageIO.read(AcaoInicializarRepositorio.class.getClassLoader().getResourceAsStream(caminho));

            return new ImageIcon(imagem);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser pasta = new JFileChooser();
        pasta.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Escolha uma pasta";
            }
        });
        pasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        pasta.setAcceptAllFileFilterUsed(false);
        
        try {
            if(pasta.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                GitCommands.getInstance(pasta.getSelectedFile().getAbsolutePath()).gitInit();
                JOptionPane.showMessageDialog(null, "Repositório inicializado em: " + pasta.getSelectedFile().getAbsolutePath(), "Plugin Git", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Seleção inválida", "Plugin Git", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Plugin Git", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
