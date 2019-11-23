package br.univali.portugol.plugin.git;

import br.univali.ps.plugins.base.VisaoPlugin;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Cadu Pacheco
 * @author Chrystian Batista
 */
public final class VisaoPluginGit extends VisaoPlugin {
    private final JPanel painelInicial;
    private final JPanel painelImagem;

    public VisaoPluginGit(PluginGit plugin) {
        super(plugin);
        initComponents();

        painelInicial = new PainelInicial(plugin);
        painelImagem = new PainelImagem(plugin);

        add(painelInicial, painelInicial.getName());
        add(painelImagem, painelImagem.getName());
    }

    private void exibirPainel(String nome) {
        CardLayout layout = (CardLayout) getLayout();
        layout.show(this, nome);
    }

    public void exibirPainelInicial() {
        exibirPainel(painelInicial.getName());
    }

    public void exibirPainelImagem() {
        exibirPainel(painelImagem.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
