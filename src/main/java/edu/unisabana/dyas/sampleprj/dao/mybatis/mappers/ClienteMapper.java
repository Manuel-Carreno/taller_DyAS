package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Cliente;

/**
 *
 * @author cesarvefe
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") int id);

    public void agregarItemRentadoACliente( 
            @Param("idcli") int id,
            @Param("iditem") int idit,
            @Param("fechainicio") Date fechainicio,
            @Param("fechafin") Date fechafin);

    public List<Cliente> consultarClientes();
    
}
