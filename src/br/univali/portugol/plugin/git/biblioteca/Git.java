package br.univali.portugol.plugin.git.biblioteca;

import br.univali.portugol.nucleo.bibliotecas.base.Biblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.TipoBiblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.DocumentacaoBiblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.PropriedadesBiblioteca;

/**
 *
 * @author Cadu Pacheco
 * @author Chrystian Batista
 */
@PropriedadesBiblioteca(tipo = TipoBiblioteca.COMPARTILHADA)
@DocumentacaoBiblioteca(
        descricao = "Esta é a biblioteca utilizada pelo plugin 'PluginGit'",
        versao = "1.0"
)
public final class Git extends Biblioteca { }
