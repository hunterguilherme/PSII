package aplicativo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/aplicativos")
@Produces(MediaType.APPLICATION_JSON)
public class AplicativoResource {

    private AplicativoDAO dao;

    public AplicativoResource(AplicativoDAO dao) {
        this.dao = dao;
    }

    @POST
    public Aplicativo Create(Aplicativo app) {
        Aplicativo a = dao.create(app);
        return a;
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") LongParam idParam, Aplicativo app) {
        app.setId(idParam.get());
        if (dao.atualizar(app)) {
            return Response.ok().build();
        }
        throw new WebApplicationException("Aplicativo com id= " + idParam.get() + " não enconrado!", 404);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam idParam) {
        long id = idParam.get();
        if (dao.apagar(id)) {
            return Response.ok().build();
        }
        throw new WebApplicationException("Aplicativo com id= " + id
                + " não encontrado!", 404);
    }

    @GET
    public List<Aplicativo> read() {
        return dao.readAll();
    }

    @GET
    @Path("{id}")
    public List<Aplicativo> read2(@PathParam("id") LongParam idParam) {
        long id = idParam.get();

        return dao.readId(id);
    }
        @GET
        @Path("nome/{nome}")
        public List<Aplicativo> read3(@PathParam("nome") String nome) {

            return dao.readNome(nome);

        }

    }
