package contaBancaria;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/contabancaria")
@Produces(MediaType.APPLICATION_JSON)
public class ContaBancariaResource {

    private ContaBancariaDAO dao;

    public ContaBancariaResource(ContaBancariaDAO dao) {
        this.dao = dao;
    }

    @POST
    public ContaBancaria Create(ContaBancaria app) {
        ContaBancaria a = dao.create(app);
        return a;
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") LongParam idParam, ContaBancaria app) {
        app.setId(idParam.get());
        if (dao.atualizar(app)) {
            return Response.ok().build();
        }
        throw new WebApplicationException("Conta bancaria com id= " + idParam.get() + " não enconrada!", 404);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam idParam) {
        long id = idParam.get();
        if (dao.apagar(id)) {
            return Response.ok().build();
        }
        throw new WebApplicationException("Conta bancaria com id= " + id
                + " não encontrada!", 404);
    }

    @GET
    public List<ContaBancaria> read() {
        return dao.readAll();
    }

    @GET
    @Path("{id}")
    public List<ContaBancaria> read2(@PathParam("id") LongParam idParam) {
        long id = idParam.get();
        
        return dao.readId(id);
        
    }
    
    @GET
    @Path("nome/{nomeTitular}")
    public List<ContaBancaria> read3(@PathParam("nomeTitular") String nomeTitular) {
        String nome = nomeTitular;
        return dao.readNome(nome);

    }

}
