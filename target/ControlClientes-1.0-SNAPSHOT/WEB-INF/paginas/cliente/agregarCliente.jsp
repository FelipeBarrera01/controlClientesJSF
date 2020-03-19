
<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="motal-title">Agregar Cliente</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                
            </div>
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="POST">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre">
                    </div>
                     <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" name="apellido">
                    </div>
                     <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" name="email">
                    </div>
                      <div class="form-group">
                        <label for="telefono">Telefono</label>
                        <input type="text" class="form-control" name="telefono">
                    </div>
                     <div class="form-group">
                        <label for="saldo">Saldo</label>
                        <input type="text" class="form-control" name="saldo">
                    </div>
                     <div class="modal-footer">
                         <button class="btn btn-primary" type="submit">
                             Guardar
                         </button>
                    </div>
                </div>
            </form>
            
        </div>
    </div>
</div>