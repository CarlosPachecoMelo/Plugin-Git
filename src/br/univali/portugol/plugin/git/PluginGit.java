package br.univali.portugol.plugin.git;

import br.univali.portugol.plugin.git.acoes.AcaoPersonalizadaDinamica;

import br.univali.portugol.plugin.git.acoes.AcaoPersonalizadaEstatica;
import br.univali.portugol.plugin.git.biblioteca.Git;
import br.univali.ps.plugins.base.GerenciadorPlugins;
import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import br.univali.ps.plugins.base.VisaoPlugin;

/**
 *
 * @author Cadu Pacheco
 * @author Chrystian Batista
 */
public final class PluginGit extends Plugin {
    private final VisaoPlugin visao = new VisaoPluginGit(this);
    private UtilizadorPlugins utilizador;

    /**
     * Construtor padrão vázio do plugin.
     */
    public PluginGit() {}

    @Override
    protected void inicializar(UtilizadorPlugins utilizador) {
        this.utilizador = utilizador;
        //Aqui você deve instalar todas as ações que seu plugin fará, ou seja, seus botões
        GerenciadorPlugins.getInstance().instalarAcaoPlugin(this, new AcaoPersonalizadaEstatica());
        GerenciadorPlugins.getInstance().instalarAcaoPlugin(this, new AcaoPersonalizadaDinamica());
        this.utilizador.registrarBiblioteca(Git.class);
    }

    @Override
    public VisaoPlugin getVisao() {
        //retorna uma tela simples para o usuário
        return visao;
    }

    public UtilizadorPlugins getUtilizador() {
        return utilizador;
    }
}
