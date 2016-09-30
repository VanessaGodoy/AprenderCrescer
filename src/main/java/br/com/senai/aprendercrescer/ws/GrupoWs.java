package br.com.senai.aprendercrescer.ws;

import br.com.senai.aprendercrescer.controller.GrupoController;
import br.com.senai.aprendercrescer.controller.UsuarioController;
import br.com.senai.aprendercrescer.model.Grupo;
import br.com.senai.aprendercrescer.model.Usuario;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/grupo")
public class GrupoWs {

  @GET
    @Path("/getgrupo")
    @Produces("application/json")
    public Response getAllUsuarios() {
        // ArrayList<JSONObject> listaJson = new ArrayList<JSONObject>();

        try {
            GrupoController ususarioControler;
            ususarioControler = new GrupoController();
            ArrayList<Grupo> lista = ususarioControler.getGrupos();

            JSONObject jGrupo;
            StringBuilder retorno = new StringBuilder();
            retorno.append("[");
            boolean controle = false;
            for (Grupo grupo : lista) {
                if (controle) {
                    retorno.append(" , ");
                }

                jGrupo = new JSONObject();
                jGrupo.put("idGrupo", grupo.getIdgrupo());
                jGrupo.put("TipoUsuario", grupo.getTipousuario());
                jGrupo.put("Descricao", grupo.getDescricaogrupo());
                retorno.append(jGrupo.toString());
                controle = true;
            }

            retorno.append("]");
            return Response.status(200).entity(retorno.toString()).build();
        } catch (Exception ex) {
            System.out.println("Erro:" + ex);
            return Response.status(200).entity(
                    "{erro : \"" + ex + "\"}").build();

        }
    }

    @POST
    @Path("/setgrupos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setGrupo(InputStream dadosServ) {
        StringBuilder requisicaoFinal = new StringBuilder();

        try {
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(dadosServ));

            String requisicao = "";
            while ((requisicao = in.readLine()) != null) {
                requisicaoFinal.append(requisicao);

            }
            System.out.println(requisicaoFinal.toString());

            JSONObject resposta = new JSONObject(requisicaoFinal.toString());
            Grupo grupo = new Grupo();
            grupo.setIdgrupo(resposta.getInt("idgrupo"));
            grupo.setTipousuario(resposta.getString("tipousuario"));
            grupo.setDescricaogrupo(resposta.getString("descricaogrupo"));

            new GrupoController().insereGrupo(grupo);
 
            Response.status(200).entity(
                    requisicaoFinal.toString()).build();
        } catch (Exception ex) {
            return Response.status(501).entity(ex.toString()).build();
        }

        return null;
    }
}
