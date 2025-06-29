package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.entities.Cliente;
import org.example.entities.Sexo;

import java.util.List;

public class ClienteJPA {
    // Registra un nuevo cliente
    public void agregarCliente(String nombre, String apellido1, String apellido2, Sexo sexo, String ciudad, String fechaNacimiento, String telefono, String correo) {
        EntityManager em = ConfigJPA.getEntityManager();
        em.getTransaction().begin();
        em.persist(new Cliente(nombre,apellido1,apellido2,sexo,ciudad,fechaNacimiento,telefono,correo));
        em.getTransaction().commit();
        em.close();
    }

    // Realiza una consulta SQL para devolver todos los clientes
    public List<Cliente> listarClientes() {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            return em.createQuery("FROM Cliente", Cliente.class).getResultList();
        }
    }
    // Devuelve el cliente que coincide con la id especificada por el usuario
    public Cliente consultarCliente(Long id) {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            return em.find(Cliente.class, id);
        }
    }
    // Realiza una consulta SQL para encontrar todos los clientes de la ciudad especificada por el usuario
    public List<Cliente> consultarClientesPorCiudad(String ciudad) {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class).setParameter("ciudad", ciudad).getResultList();
        }
    }

    // Actualiza el cliente con la id especificada por el usuario
    public boolean actualizarCliente(Long id, String nombre, String apellido1, String apellido2, Sexo sexo, String ciudad, String fechaNacimiento, String telefono, String correo) {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.getTransaction().begin();
                cliente.setNombre(nombre);
                cliente.setApellido1(apellido1);
                cliente.setApellido2(apellido2);
                cliente.setSexo(sexo);
                cliente.setCiudad(ciudad);
                cliente.setFechaNacimiento(fechaNacimiento);
                cliente.setTelefono(telefono);
                cliente.setCorreo(correo);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        }
    }

    // Elimina el cliente con la id especificada por el usuario
    public boolean eliminarCliente(Long id) {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.getTransaction().begin();
                em.remove(cliente);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        }
    }

}
