package br.com.senai.aprendercrescer.ws;

import br.com.senai.aprendercrescer.controller.UsuarioController;
import br.com.senai.aprendercrescer.model.Usuario;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/usuario")
public class UsuarioWs {
    

    @GET
    @Path("/getusuario")
    @Produces("application/json")
    public Response getUsuario() {

        JSONObject retorno = new JSONObject();
        try {
            retorno.put("nome", "Eduardo");
            retorno.put("idade", 26);
            return Response.status(200).entity(retorno.toString()).build();
        } catch (JSONException ex) {
            Logger.getLogger(UsuarioWs.class.getName()).log(Level.SEVERE, null, ex);
        }
        String Resposta = "{'nome':'Eduardo'}";

        return Response.status(500).build();
    }

    @GET
    @Path("/getusuarios")
    @Produces("application/json")
    public Response getAllUsuario() {
        try {
            UsuarioController usuarioControler;
            usuarioControler = new UsuarioController();
            ArrayList<Usuario> lista
                    = usuarioControler.getUsuarios();
            JSONObject retorno = new JSONObject();
            JSONObject jUsuario;
            for (Usuario usuario : lista) {
                jUsuario = new JSONObject();
                jUsuario.put("idUsuario", usuario.getIdusuario());
                jUsuario.put("nome", usuario.getNome());
                retorno.put("usuario" + usuario.getIdusuario(), jUsuario.toString());
            }
            return Response.status(200).entity(retorno.toString()).build();
        } catch (Exception ex) {

            System.out.println("Erro:" + ex);

            return Response.status(200).entity(
                    "{erro:\"" + ex + "\"}").build();
        }
    }

@POST
@Path("/setusuario")
@Consumes(MediaType.APPLICATION_JSON)
public Response setUsuario(InputStream dadosServ){
    
    StringBuilder requisicaoFinal = new StringBuilder();
            
    try{
        BufferedReader in =
                new BufferedReader(new InputStreamReader(dadosServ));
        
        
        String requisicao = "";
        while((requisicao = in.readLine()) != null){
            requisicaoFinal.append(requisicao);
            
        }
        System.out.println(requisicaoFinal.toString());
        
        JSONObject resposta = new JSONObject(requisicaoFinal.toString());
        Usuario usuario = new Usuario();
        usuario.setLogin(resposta.getString("nome"));
        usuario.setNome (resposta.getString("nome"));
        usuario.setSenha(resposta.getInt("senha")+"");
        usuario.setIdusuario(resposta.getInt("IDUsuario"));
        usuario.setIdgrupo(resposta.getInt("IDGrupo"));        
        usuario.setFlagInativo(resposta.getString("FlagInativo").toCharArray()[0]);        
        usuario.setDtAlteracao(new Date());
        
        
        new UsuarioController().insereUsuario(usuario);
        
        
        Response.status(200).entity(
        requisicaoFinal.toString()).build();
    }catch(Exception ex){
        return Response.status(501).entity(ex.toString()).build();
    }
    
    return null;
}










}