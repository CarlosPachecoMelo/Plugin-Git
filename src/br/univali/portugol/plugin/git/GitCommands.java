package br.univali.portugol.plugin.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * Baseado no código encontrado em https://gist.github.com/Crydust/fd1b94afc52cd0f7dd4c
 * 
 * @author Cadu Pacheco
 * @author Chrystian Batista
 */
public class GitCommands {
    private static GitCommands singleton;
    private final Path pasta;
    private String remoto;
    private String usuario;
    private String senha;

    private GitCommands(String pasta){
        this.pasta = Paths.get(pasta);
    }

    public static GitCommands getInstance() {
        if(singleton == null ){
            singleton = new GitCommands(System.getProperty("user.dir"));
        }
        return singleton;
    }

    public static GitCommands getInstance(String pasta) {
        if(singleton == null ){
            singleton = new GitCommands(pasta);
        }
        return singleton;
    }

    public Path getPasta() {
        return pasta;
    }

    public void gitInit() throws IOException, InterruptedException {
        executarComando("git", "init");
        executarComando("git", "add", ".");
        executarComando("git", "commit", "-a", "-m", "Commit Inicial");
    }

    public void gitRemoteAdd(String remoto, String usuario, String senha) throws IOException, InterruptedException {
        executarComando("git", "remote", "add", "origin", remoto);

        this.usuario = usuario.replaceAll("@", "%40");
        this.senha = senha.replaceAll("@", "%40");
        this.remoto = remoto.replaceAll("(https://)([^\\/]*)\\/([^\\/]*)\\/([^\\/]*)", "$1" + this.usuario + ":" + this.senha + "@$2/$3/$4");

        executarComando("git", "push", "--all", this.remoto);
    }

    public void gitCommit(String mensagem) throws IOException, InterruptedException {
        executarComando("git", "add", ".");
        executarComando("git", "commit", "-a", "-m", mensagem);
    }

    public void gitPull() throws IOException, InterruptedException {
        executarComando("git", "pull", this.remoto);
    }

    public void gitPush() throws IOException, InterruptedException {
        executarComando("git", "push", "--all", this.remoto);
    }

    private void executarComando(String... comando) throws IOException, InterruptedException {
        Objects.requireNonNull(pasta, "directory");
        if(!Files.exists(pasta)) {
            throw new RuntimeException("Não é possível executar um comando em uma pasta não existente");
        }

        ProcessBuilder construtor = new ProcessBuilder().command(comando).directory(pasta.toFile());
        Process processo = construtor.start();

        LeitorStream errorStream = new LeitorStream(processo.getErrorStream(), "ERROR");
        errorStream.start();

        LeitorStream outputStream = new LeitorStream(processo.getInputStream(), "OUTPUT");
        outputStream.start();

        int saida = processo.waitFor();
        errorStream.join();
        outputStream.join();    

        if(saida != 0) {
            throw new AssertionError(String.format("Comando %s retornou %d", comando, saida));
        }
    }

    private static class LeitorStream extends Thread {
        private final InputStream input;
        private final String tipo;

        private LeitorStream(InputStream input, String tipo) {
            this.input = input;
            this.tipo = tipo;
        }

        @Override
        public void run() {
            try (BufferedReader leitor = new BufferedReader(new InputStreamReader(input))){
                String linha;
               while((linha = leitor.readLine()) != null) {
                   System.out.println(tipo + " > " + linha);
               }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
