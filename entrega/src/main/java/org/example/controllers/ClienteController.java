package org.example.controllers;

import org.example.entities.Cliente;
import org.example.entities.Sexo;
import org.example.persistence.ClienteJPA;

import java.util.List;
import java.util.Set;

// Clase intermediaria entre la clase Main y la clase clienteJPA
public class ClienteController {

    ClienteJPA clienteJPA = new ClienteJPA();

        public void agregarCliente(String nombre, String apellido1, String apellido2, Sexo sexo, String ciudad, String fechaNacimiento, String telefono, String correo) {
            clienteJPA.agregarCliente(nombre,apellido1,apellido2,sexo,ciudad,fechaNacimiento,telefono,correo);
        }

        public List<Cliente> listarClientes() {
            return clienteJPA.listarClientes();
        }

        public Cliente consultarCliente(Long id) {
            return clienteJPA.consultarCliente(id);
        }
        public List<Cliente> consultarClientesPorCiudad(String ciudad) {
            return clienteJPA.consultarClientesPorCiudad(ciudad);
        }

        public boolean actualizarCliente(Long id, String nombre, String apellido1, String apellido2, Sexo sexo, String ciudad, String fechaNacimiento, String telefono, String correo) {
            return clienteJPA.actualizarCliente(id, nombre,apellido1,apellido2,sexo,ciudad,fechaNacimiento,telefono,correo);
        }

        public boolean eliminarCliente(Long id) {
            return clienteJPA.eliminarCliente(id);
        }


}
