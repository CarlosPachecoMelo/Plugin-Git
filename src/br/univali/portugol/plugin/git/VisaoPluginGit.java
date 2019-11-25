package br.univali.portugol.plugin.git;

import br.univali.ps.plugins.base.VisaoPlugin;
import java.awt.CardLayout;

/**
 *
 * @author Cadu Pacheco
 * @author Chrystian Batista
 */
public final class VisaoPluginGit extends VisaoPlugin {
    public VisaoPluginGit(PluginGit plugin) {
        super(plugin);
        initComponents();
    }

    private void exibirPainel(String nome) {
        CardLayout layout = (CardLayout) getLayout();
        layout.show(this, nome);
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
