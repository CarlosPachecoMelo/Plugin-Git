package br.univali.portugol.plugin.git;

import br.univali.portugol.plugin.git.acoes.AcaoAdicionarRemoto;
import br.univali.portugol.plugin.git.acoes.AcaoCommit;
import br.univali.portugol.plugin.git.acoes.AcaoInicializarRepositorio;
import br.univali.portugol.plugin.git.acoes.AcaoPull;
import br.univali.portugol.plugin.git.acoes.AcaoPush;

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
        GerenciadorPlugins.getInstance().instalarAcaoPlugin(this, new AcaoInicializarRepositorio());
        GerenciadorPlugins.getInstance().instalarAcaoPlugin(this, new AcaoAdicionarRemoto());
        GerenciadorPlugins.getInstance().instalarAcaoPlugin(this, new AcaoCommit());
        GerenciadorPlugins.getInstance().instalarAcaoPlugin(this, new AcaoPush());
        GerenciadorPlugins.getInstance().instalarAcaoPlugin(this, new AcaoPull());

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
