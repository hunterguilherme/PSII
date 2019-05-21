package webService;

import dataBase.ConexaoJavaDB;
import aplicativo.AplicativoDAO;
import aplicativo.AplicativoResource;
import contaBancaria.ContaBancariaDAO;
import contaBancaria.ContaBancariaResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestApp extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new RestApp().run(new String[]{"server"});
    }

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        //Mapeia a pasta "src/html" para a url "http://localhost:8080/" e
        // por padrao abre o arquivo index.html quando um recurso especifico
        // nao for informado
        bootstrap.addBundle(new AssetsBundle("/html", "/", "index.html"));
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        ConexaoJavaDB conexao = new ConexaoJavaDB("Hunter", "hunter", "jdbc:derby://localhost", 1527, "psII");
        AplicativoDAO aplicativoDao = new AplicativoDAO(conexao);
        environment.jersey().register(new AplicativoResource(aplicativoDao));
        ContaBancariaDAO contaBancariaDao = new ContaBancariaDAO(conexao);
        environment.jersey().register(new ContaBancariaResource(contaBancariaDao));
        // Mapeia todos os WebServices para a rota base 
        // "http://localhost:8080/api/"
        environment.jersey().setUrlPattern("/api/*");
    }
}
