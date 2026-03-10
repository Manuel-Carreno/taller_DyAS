-- SQLite
    select
    c.nombre,
    c.documento,
    c.telefono,
    c.direccion,
    c.email,
    c.vetado,
    ir.ITEMS_id ,
    ir.fechainiciorenta ,
    ir.fechafinrenta ,
    i.id ,
    i.nombre ,
    i.descripcion ,
    i.fechalanzamiento ,
    i.tarifaxdia ,
    i.formatorenta ,
    i.genero ,        
    ti.id ,
    ti.descripcion 
    FROM VI_CLIENTES as c 
    left join VI_ITEMRENTADO as ir on c.documento=ir.CLIENTES_documento 
    left join VI_ITEMS as i on ir.ITEMS_id=i.id 
    left join VI_TIPOITEM as ti on i.TIPOITEM_id=ti.id 

    WHERE c.documento = 123456789

    
