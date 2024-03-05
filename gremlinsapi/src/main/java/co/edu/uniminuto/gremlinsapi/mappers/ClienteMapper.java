package co.edu.uniminuto.gremlinsapi.mappers;

import java.util.stream.Collectors;


import co.edu.uniminuto.gremlinsapi.dtos.ClienteDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Cliente;


public class ClienteMapper {

    public static ClienteDTO mapClienteToDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .cId(cliente.getCId())
                .cNombre(cliente.getCNombre())
                .cTelefono(cliente.getCTelefono())
                .cDireccion(cliente.getCDireccion())
                .cEstado(cliente.getCEstado())
                .envios(cliente.getEnvios().stream().map(EnvioMapper::mapEnvioToDTO).collect(Collectors.toList()))
                .pedidos(cliente.getPedidos().stream().map(PedidoMapper::mapPedidoToDTO).collect(Collectors.toList()))
                .build();
    }

    public static Cliente mapDTOToCliente(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .cId(clienteDTO.getCId())
                .cNombre(clienteDTO.getCNombre())
                .cTelefono(clienteDTO.getCTelefono())
                .cDireccion(clienteDTO.getCDireccion())
                .cEstado(clienteDTO.getCEstado())
                // Si necesitas mapear las listas de envíos y pedidos, debes implementarlo aquí
                .build();
    }
}

