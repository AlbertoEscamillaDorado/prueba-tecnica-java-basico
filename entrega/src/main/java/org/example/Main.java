package org.example;

import org.example.controllers.ClienteController;
import org.example.entities.Accion;
import org.example.entities.Cliente;
import org.example.entities.Sexo;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Declaracion de variables
        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        Accion accion = Accion.CREAR;
        int opcion = 0;
        Cliente cliente;



        while (true){
            System.out.println("-----------------------");
            System.out.println("--- Menú de la Aplicación ---");
            System.out.println("-----------------------");
            System.out.println("1. Registrar un nuevo cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Consultar un cliente");
            System.out.println("4. Consultar un cliente por ciudad");
            System.out.println("5. Actualizar un cliente");
            System.out.println("6. Eliminar un cliente");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
            } catch (Exception e) {
                System.out.println("Error: " + e);
                scanner.nextLine(); // limpiar buffer
            }
            // Menu de opciones de la aplicacion
            switch (opcion) {
                // Caso para la creacion de un cliente
                case 1:
                    accion = Accion.CREAR;
                    pedirDatos(scanner,clienteController,accion);

                    System.out.println("Cliente registrado exitosamente.");

                    break;
                // Caso para listar todos los clientes de la base de datos
                case 2:
                    List<Cliente> clientes = clienteController.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        System.out.println("Clientes registrados: ");
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                    }
                    break;
                // Caso para mostrar el cliente con la id especificada por el usuario
                case 3:
                    System.out.print("ID del cliente a consultar: ");
                    cliente = clienteController.consultarCliente(scanner.nextLong());
                    System.out.println(cliente != null ? cliente : "Cliente no encontrado.");
                    break;
                // Caso para listar todos los clientes de la base de datos con la ciudad especificada por el usuario
                case 4:
                    System.out.print("Ciudad del cliente a consultar: ");
                    clientes = clienteController.consultarClientesPorCiudad(scanner.nextLine().toLowerCase());
                    if (clientes.isEmpty()){
                        System.out.println( "Cliente no encontrado.");
                    }else{
                        System.out.println("Clientes encontrados: ");
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                    }
                    break;
                // Caso para actualizar un cliente con la id especificada por el usuario
                case 5:
                    accion = Accion.ACTUALIZAR;
                    pedirDatos(scanner,clienteController,accion);
                    break;
                // Caso para eliminar un cliente con la id especificada por el usuario
                case 6:
                    System.out.print("ID del cliente a eliminar: ");
                    System.out.println(clienteController.eliminarCliente(scanner.nextLong()) ? "Cliente eliminado." : "Cliente no encontrado.");
                    break;
                case 7:
                    System.out.println("¡Hasta la próxima!");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    // Funcion para pedir los datos de un cliente, sirve para crear y para actualizar
    public static void pedirDatos(Scanner scanner, ClienteController clienteController, Accion accion){
        String nombre, apellido1, apellido2, sexo, ciudad, fechaNacimiento, telefono, correo;
        Sexo sexo1 = Sexo.HOMBRE;
        Long id = 0L;

        if (accion.equals(Accion.ACTUALIZAR)){
            System.out.print("ID del cliente a actualizar: ");
            id = scanner.nextLong();
            scanner.nextLine(); // limpiar buffer
        }


        System.out.print("Nombre del cliente: ");
        nombre = scanner.nextLine();

        System.out.print("Primer apellido del cliente: ");
        apellido1 = scanner.nextLine();

        System.out.print("Segundo apellido del cliente: ");
        apellido2 = scanner.nextLine();

        boolean salir = false;
        while (!salir){

            System.out.print("Sexo del cliente (H,M,O): ");
            sexo = scanner.nextLine();

            switch (sexo.toUpperCase()){
                case "H":
                    sexo1 = Sexo.HOMBRE;
                    salir = true;
                    break;
                case "M":
                    sexo1 = Sexo.MUJER;
                    salir = true;
                    break;
                case "O":
                    sexo1 = Sexo.OTRO;
                    salir = true;
                    break;
                default:
                    System.out.println("La opción: (" + sexo + ") no es valida");
            }

        }

        System.out.print("Ciudad del cliente: ");
        ciudad = scanner.nextLine().toLowerCase();

        System.out.print("Fecha de nacimiento del cliente: ");
        fechaNacimiento = scanner.nextLine();

        System.out.print("Telefono del cliente: ");
        telefono = scanner.nextLine();

        System.out.print("Correo electronico del cliente: ");
        correo = scanner.nextLine();

        // Comparacion para usar el metodo crear o el metodo actualizar
        if(accion.equals(Accion.CREAR)){
            clienteController.agregarCliente(nombre, apellido1, apellido2, sexo1, ciudad, fechaNacimiento, telefono, correo);
        } else if (accion.equals(Accion.ACTUALIZAR)) {
            System.out.println(clienteController.actualizarCliente(id,nombre, apellido1, apellido2, sexo1, ciudad, fechaNacimiento, telefono, correo) ? "Cliente actualizado." : "Cliente no encontrado.");
        }


    }

}