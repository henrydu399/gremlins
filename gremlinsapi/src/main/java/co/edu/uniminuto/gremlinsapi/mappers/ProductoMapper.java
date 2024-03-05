package co.edu.uniminuto.gremlinsapi.mappers;

import co.edu.uniminuto.gremlinsapi.dtos.ProductoDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Producto;

public class ProductoMapper {

    private final DistribuidoreMapper distribuidoreMapper;

    public ProductoMapper(DistribuidoreMapper distribuidoreMapper) {
        this.distribuidoreMapper = distribuidoreMapper;
    }

    public static ProductoDTO mapProductoToDTO(Producto producto) {
        return ProductoDTO.builder()
                .pId(producto.getPId())
                .pCreado(producto.getPCreado())
                .pModificado(producto.getPModificado())
                .pNombre(producto.getPNombre())
                .pPrecioFinal(producto.getPPrecioFinal())
                .pEstado(producto.getPEstado())
                .distribuidore(DistribuidoreMapper.mapDistribuidoreToDTO(producto.getDistribuidore()))
                .build();
    }

    public static Producto mapDTOToProducto(ProductoDTO productoDTO) {
        return Producto.builder()
                .pId(productoDTO.getPId())
                .pCreado(productoDTO.getPCreado())
                .pModificado(productoDTO.getPModificado())
                .pNombre(productoDTO.getPNombre())
                .pPrecioFinal(productoDTO.getPPrecioFinal())
                .pEstado(productoDTO.getPEstado())
                // Si necesitas mapear la lista de pedidos detalles o el distribuidor, debes implementarlo aquí
                .build();
    }
}
