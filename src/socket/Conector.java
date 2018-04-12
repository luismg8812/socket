package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class Conector {

	ServerSocket server;
	Socket socket;
	int puertoSocket = 7010;
	PrintStream salida;
	BufferedReader entrada;
	CommPortIdentifier portId = null;
	CommPort puerto = null;
	SerialPort sp = null;
	ConsultarSerial cs = null;

	/**
	 * Metodo encargado de inicial el socket
	 */
	public void iniciar() {		
			String respuesta = "0.0";
			try {
				server = new ServerSocket(puertoSocket);
				while (true) {
				socket = new Socket();
				socket = server.accept();
				entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String mensaje = entrada.readLine();
				System.out.println("Mensaje entrada: " + mensaje);
				String[] parts = mensaje.split(":");
				if (cs == null) {
					cs = new ConsultarSerial();
				}
				if (portId == null) {
					portId = cs.idintificarSerial();
					if (portId != null) {
						cs.inicializar();
					}
				}
				switch (parts[1]) {
				case "1":
					respuesta = cs.obtenerDato(parts[1]);
					break;
				case "2":
					try {
						respuesta = cs.obtenerDato2(parts[1]);
					} catch (Exception e) {
						respuesta = "nulo";
					}
					
					break;
				case "3":
					respuesta = cs.obtenerDatoGramera3(parts[1]);
					try {
						Double.parseDouble(respuesta);
					} catch (Exception e) {
						cs.cerrarSerial();
						portId=null;
					}
				
					break;
				case "4":
					respuesta = cs.obtenerDatoGramera4(parts[1]);
					respuesta = respuesta.replaceAll(",", "");
					respuesta = respuesta.replace(",", "");
					try {
						if(respuesta.equals("")||respuesta.equals("0.000") ){
							cs.cerrarSerial();
							portId=null;
							portId = cs.idintificarSerial();
							if (portId != null) {
								cs.inicializar();
							}
							respuesta = cs.obtenerDatoGramera4(parts[1]);
							respuesta = respuesta.replaceAll(",", "");
							respuesta = respuesta.replace(",", "");
						}
						Double.parseDouble(respuesta);
						//cs.cerrarSerial();
						//portId=null;
					} catch (Exception e) {
						System.out.println("Error en lectura:"+respuesta);
						//cs.cerrarSerial();
						//portId=null;
					}
				
					break;
				case "5":
					System.out.println("Gramera 5");
					respuesta = cs.obtenerDatoGramera5(parts[1]);
					System.out.println("Sale de Gramera 5");
					respuesta = respuesta.replaceAll(",", "");
					try {
						Double.parseDouble(respuesta);
					} catch (Exception e) {
						//cs.cerrarSerial();
						//portId=null;
					}
				
					break;
				case "6":
					respuesta = cs.obtenerDatoGramera6(parts[1]);
					respuesta = cs.obtenerDatoGramera6(parts[1]);
					break;
				default:
					System.out.println("no hay tipo de gramera asignado");
					break;
				}
				
				if (portId == null) {
					respuesta += ":No hay gramera conectada";
				}
				salida = new PrintStream(socket.getOutputStream());
				salida.println(""+respuesta);
				socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}

}
