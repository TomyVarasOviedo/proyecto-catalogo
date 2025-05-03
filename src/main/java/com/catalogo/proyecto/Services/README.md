# Servicios
> ## CatalogoService
> - getCatalogoId(Long idCatalogo) : Catalogo
> - guardarCatalogo(Catalogo catalogo) : Catalogo
> - eliminarCatalogo(Long idCatalogo) : Catalogo
> - validarCatalogoEntry(Catalogo catalogo) : boolean
> ## CategoriaService
> - guardarCategoria(Categoria categoria) : Categoria
> - getCategoriaId(Long idCategoria) : Categoria
> - getCategoriaAll() : List<Categoria>
> - eliminarCategoria(Long idCategoria) : Categoria
> - validarCategoriaEntry(Categoria categoria) : boolean
> ## PedidoService
> - guardarPedido(Pedido pedido) : Pedido
> - getPedidoId(UUID idPedido) : Pedido
> - getPedidoAll() : List<Pedido>
> - eliminarPedido(UUID idPedido) : Pedido
> - getPedidoFecha(LocalDate fechaIinicio, LocalDate fechaFinal) : List<Pedido>
> -  validarPedidoEntry(Pedido pedido) : boolean
> ## RopaService
> - guardarRopa(Ropa ropa) : Ropa
> - getRopaId(Long id) : Ropa
> -  eliminarRopa(Long id) : Ropa
> - validarRopaEntry(Ropa ropa) : boolean
> ## SeccionService
> - guardarSeccion(Seccion seccion) : Seccion
> - getSeccionId(Long seccionId) : Seccion
> - eliminarSeccion(Long seccionId) : Seccion
> - getSeccionAll() : List<Seccion>
> - validarSeccionEntry(Seccion seccion) : boolean
> ## TalleService 
> - guardarTalle(Talle talle) : Talle
> - getTalleId(int idTalle) : Talle
> - getAllTalles() : List<Talle>
> - eliminarTalle(int idTalle) : Talle
> - validarTalleEntry(Talle talle) : boolean
> ## UsuarioService
> - obtenerComprasUsuario(Long id) : List<Pedido>
> - getUsuarioAll() : List<Usuario>
> - getUsuarioId(Long id) : Usuario
> - guardarUsuario(Usuario usuario) : Usuario
> - eliminarUsuario(Long id) : Usuario
> - modificarUsuario(Usuario usuario) : Usuario
> - validarUsuarioEntry(Usuario usuario) : boolean

