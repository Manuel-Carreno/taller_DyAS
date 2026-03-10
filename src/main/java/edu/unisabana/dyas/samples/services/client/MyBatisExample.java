/*
 * Copyright (C) 2015 cesarvefe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.unisabana.dyas.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.SQLException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.unisabana.dyas.samples.entities.Cliente;
import edu.unisabana.dyas.samples.entities.Item;
import edu.unisabana.dyas.samples.entities.TipoItem;

/**
 *
 * @author cesarvefe
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();

        //crear un nuevo item
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        Item newItem = new Item();
        newItem.setNombre("Silla de Madera");
        newItem.setDescrpcion("Producto nuevo para la venta");
        newItem.setFechaLanzamiento(new java.util.Date());
        newItem.setTarifaxDia(10000);
        newItem.setFormatoRenta("Diario");
        newItem.setGenero("Mueble");
        newItem.setId(4);

        //crear el tipo items
        TipoItem tipoItems = new TipoItem();
        tipoItems.setID(2);
        tipoItems.setDescripcion("Mueble");
        newItem.setTipo(tipoItems);

        im.insertarItem(newItem);
        System.out.println("Se inserto el item");

        sqlss.commit();
        
        Item itemInsert = im.consultarItem(newItem.getId());
        System.out.println("El item guardado es:");
        System.out.println(itemInsert);
        System.out.println("-----------------");


        //consultar clientes
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        System.out.println(cm.consultarClientes());
        
        sqlss.close();

        
        
    }


}
