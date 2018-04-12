package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class ConsultarSerial {

	CommPort puerto = null;
	SerialPort sp = null;
	CommPortIdentifier portId = null;

	/**
	 * Metodo encargado de identificar si hay puertos seriales intalados
	 * @return
	 */
	public CommPortIdentifier idintificarSerial() {		
		@SuppressWarnings("rawtypes")
		Enumeration comPots = CommPortIdentifier.getPortIdentifiers();
		while (comPots.hasMoreElements()) {
			CommPortIdentifier mc = (CommPortIdentifier) comPots.nextElement();
			
			//mc.getName().equals("COM1") &&
			if (mc.getPortType() == CommPortIdentifier.PORT_SERIAL) {//mc.getName().equals("COM1") && 
				System.out.println("puerto Com: " + mc.getName());
				portId=mc;
				return mc;
			}
		}

		return null;
	}

	/**
	 * Metodo encargado de inicializar el puerto
	 */
	public void inicializar() {
		
		try {
			puerto = portId.open("puertoC" + new Date(), 2000);
		} catch (PortInUseException e) {
			e.printStackTrace();
		}
		sp = (SerialPort) puerto;
		try {
			sp.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que suelta el serial
	 */
	public void cerrarSerial(){
		puerto.close();
		puerto=null;
		sp=null;
		portId=null;
	}

	/**
	 * Metodo encargado de devolver el dato en el puerto serial
	 * @return
	 * @throws IOException 
	 */
	
	@SuppressWarnings("unused")
	public String obtenerDato(String gramera) throws IOException {
		Scanner sc = null;	
		PrintStream ps;	
		sc = new Scanner(sp.getInputStream());
		ps = new PrintStream(sp.getOutputStream());
		//if(gramera.equals("1")){
			sc.close();
			sc = null;
			sc = new Scanner(sp.getInputStream());
		//}	
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
				sc.close();
				sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
		}
		System.out.println("primera busqueda");
		String data = sc.next();
		System.out.println("primer valor leido:" + data);
		//System.out.println("primer residuo"+sc.next());
		
		try {
			sc = new Scanner(sp.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ps = new PrintStream(sp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
			
			try {
				//sc.close();
				//sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("lee el resultado");
	     data = sc.next();
	    // String reciduo =sc.next(); 
		//System.out.println("residuo:"+reciduo);
		sc.close();
		sc = null;
		System.out.println("valor: " + data);		
		return data;
	}
	
	@SuppressWarnings("unused")
	public String obtenerDato2(String gramera) throws IOException {
		Scanner sc = null;	
		PrintStream ps;	
		sc = new Scanner(sp.getInputStream());
		ps = new PrintStream(sp.getOutputStream());
		//if(gramera.equals("1")){
			sc.close();
			sc = null;
			sc = new Scanner(sp.getInputStream());
		//}	
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
				sc.close();
				sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
		}
		System.out.println("primera busqueda");
		String data = sc.next();
		System.out.println("primer valor leido:" + data);
		System.out.println("primer residuo"+sc.next());
		
		try {
			sc = new Scanner(sp.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ps = new PrintStream(sp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
			
			try {
				//sc.close();
				//sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("lee el resultado");
		 do {
			 data = sc.next();
		} while (data.indexOf("k") != -1);
		sc.close();
		sc = null;
		System.out.println("valor: " + data);		
		return data;
	}
	
	
	
	/**
	 * Metodo encargado de devolver el dato en el puerto serial
	 * @return
	 * @throws IOException 
	 */
	
	@SuppressWarnings("unused")
	public String obtenerDatoGramera3(String gramera) throws IOException {
		Scanner sc = null;	
		PrintStream ps;	
		sc = new Scanner(sp.getInputStream());
		ps = new PrintStream(sp.getOutputStream());
		//if(gramera.equals("1")){
			sc.close();
			sc = null;
			sc = new Scanner(sp.getInputStream());
		//}	
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
				sc.close();
				sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
		}
		System.out.println("primera busqueda");
		String data ="";
		 String reciduo =""; 
		try {
			 data = sc.next();
			  reciduo =sc.next(); 
		} catch (Exception e) {
			 data = "";
			  reciduo= ""; 
		}
		
		System.out.println("primer valor leido:" + data);
		System.out.println("primer residuo"+reciduo);
		sc.close();			
		return reciduo;
	}
	
	/**
	 * Metodo encargado de devolver el dato en el puerto serial
	 * @return
	 * @throws IOException 
	 */
	
	@SuppressWarnings("unused")
	public String obtenerDatoGramera4(String gramera) throws IOException {
		Scanner sc = null;	
		PrintStream ps;	
		sc = new Scanner(sp.getInputStream());
		ps = new PrintStream(sp.getOutputStream());
		//if(gramera.equals("1")){
			sc.close();
			sc = null;
			sc = new Scanner(sp.getInputStream());
		//}	
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
				sc.close();
				sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
		}
		//System.out.println("primera busqueda");
		String data ="";//= sc.next();
		 do {
			 data = sc.next();
		} while (data.indexOf("Wt") == -1);
		System.out.println("primer valor leido:" + data);
		
		try {
			sc = new Scanner(sp.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ps = new PrintStream(sp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
			
			try {
				//sc.close();
				//sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("lee el resultado");
		 do {
			 data = sc.next();
		} while (data.indexOf("Wt") == -1);
		 data = sc.next();
		 if(data.equals(",")||data.indexOf("Wt") == -1 ||data.indexOf("Kg") == -1 || data.indexOf("Ut") == -1 ||data.indexOf("Tt") == -1){
			 do {
				 data = sc.next();
			} while (data.indexOf("Wt") == -1);
		 }
		 data = sc.next();
		sc.close();
		sc = null;
		System.out.println("valor: " + data);		
		return data;
	}
	
	/**
	 * Metodo encargado de devolver el dato en el puerto serial
	 * @return
	 * @throws IOException 
	 */
	
	
	public String obtenerDatoGramera5(String gramera) throws IOException {
		Scanner sc = null;	
		PrintStream ps;	
		sc = new Scanner(sp.getInputStream());
		ps = new PrintStream(sp.getOutputStream());
		//if(gramera.equals("1")){
			sc.close();
			sc = null;
			sc = new Scanner(sp.getInputStream());
		//}	
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
				sc.close();
				sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
		}
		System.out.println("primera busqueda");
		String data = sc.next();
		System.out.println("primer valor leido:" + data);
		System.out.println("primer residuo"+sc.next());
		
		try {
			sc = new Scanner(sp.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ps = new PrintStream(sp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!sc.hasNext()) {// se queda pegado mientras que no
			// llegue nungun valor por el puerto
			// serial
			
			try {
				//sc.close();
				//sc = null;
				sc = new Scanner(sp.getInputStream());
				//
				//System.out.println("dato leido actual limp2"+sc.next());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("lee el resultado");
	     data = sc.next();
	     String reciduo =sc.next(); 
		System.out.println("residuo:"+reciduo);
		sc.close();
		sc = null;
		System.out.println("valor: " + data);		
		return data;
	}
	
	
		
		@SuppressWarnings("unused")
		public String obtenerDatoGramera6(String gramera) throws IOException {
			Scanner sc = null;	
			PrintStream ps;	
			sc = new Scanner(sp.getInputStream());
			ps = new PrintStream(sp.getOutputStream());
			//if(gramera.equals("1")){
				sc.close();
				sc = null;
				sc = new Scanner(sp.getInputStream());
			//}	
			while (!sc.hasNext()) {// se queda pegado mientras que no
				// llegue nungun valor por el puerto
				// serial
					sc.close();
					sc = null;
					sc = new Scanner(sp.getInputStream());
					//
					//System.out.println("dato leido actual limp2"+sc.next());
			}
			System.out.println("primera busqueda");
			String data = sc.next();
			System.out.println("primer valor leido:" + data);
			//data = sc.next();
			System.out.println("primer residuo"+data);
			
			try {
				sc = new Scanner(sp.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				ps = new PrintStream(sp.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (!sc.hasNext()) {// se queda pegado mientras que no
				// llegue nungun valor por el puerto
				// serial
				
				try {
					//sc.close();
					//sc = null;
					sc = new Scanner(sp.getInputStream());
					//
					//System.out.println("dato leido actual limp2"+sc.next());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("lee el resultado");
			
				 //data = sc.next();
			
			sc.close();
			sc = null;
			System.out.println("valor: " + data);		
			return data;
		}
		
}
