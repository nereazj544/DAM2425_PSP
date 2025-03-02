// package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Server;

// import java.io.*;
// import java.net.*;
// import java.security.*;
// import java.security.spec.*;
// import java.util.Map;
// import java.util.Set;

// import javax.crypto.*;

// public class KeyTask implements Runnable {
//     private Socket sck;
//     // private Agenda agenda;
//     private KeyPair SV_keyPair;
//     private SecretKey clave;

//     public KeyTask(Socket sck, KeyPair keyPair) {
//         this.sck = sck;
//         // this.agenda = agenda;
//         this.SV_keyPair = keyPair;
//     }

//     @Override
//     public void run() {
//         try (DataInputStream in = new DataInputStream(sck.getInputStream());
//                 DataOutputStream out = new DataOutputStream(sck.getOutputStream())) {

//             int KY_len = in.readInt();
//             byte[] KY_PubByte = new byte[KY_len];
//             in.readFully(KY_PubByte);

//             KeyFactory KY_Factory = KeyFactory.getInstance("RSA");
//             X509EncodedKeySpec KY_PubSpec = new X509EncodedKeySpec(KY_PubByte);
//             PublicKey KY_PubClient = KY_Factory.generatePublic(KY_PubSpec);
//             out.writeUTF("\n> Recibida clave publica del cliente");

//             KeyGenerator KY_gen = KeyGenerator.getInstance("AES");
//             KY_gen.init(128);
//             clave = KY_gen.generateKey();

//             Cipher RSA_Bill = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//             RSA_Bill.init(Cipher.ENCRYPT_MODE, KY_PubClient);
//             byte[] claveCifrada = RSA_Bill.doFinal(clave.getEncoded());

//             out.writeInt(claveCifrada.length);
//             out.write(claveCifrada);

//             Cipher AES_Chipher = Cipher.getInstance("AES");
//             AES_Chipher.init(Cipher.ENCRYPT_MODE, clave);

//             // boolean s = false;
//             // while (!s && !sck.isClosed()) {
//             //     int msg = in.readInt();
//             //     byte[] encripMSG = new byte[msg];
//             //     in.readFully(encripMSG);

//             //     byte[] decryptedMsg = aesCipherDecrypt.doFinal(encryptedMsg);
//             //     String mensaje = new String(decryptedMsg);

//             //     System.out.println("Mensaje recibido: " + mensaje);

//                 // Procesar mensaje
//                 // Map<String> respuesta;
//                 // if (mensaje.equalsIgnoreCase("FIN")) {
//                 //     respuesta = "Conexión finalizada";
//                 //     s = true;
//                 // } else if (mensaje.equalsIgnoreCase("contactos")) {
//                 //     respuesta = agenda.listarContactos();
//                 // } else if (mensaje.startsWith("buscar:")) {
//                 //     String nombre = mensaje.substring(7);
//                 //     respuesta = agenda.buscarContacto(nombre);
//                 // } else if (mensaje.startsWith("eliminar:")) {
//                 //     String nombre = mensaje.substring(9);
//                 //     respuesta = agenda.eliminarContacto(nombre);
//                 // } else if (mensaje.contains(":")) {
//                 //     String[] partes = mensaje.split(":");
//                 //     if (partes.length == 2) {
//                 //         respuesta = agenda.agregarContacto(partes[0], partes[1]);
//                 //     } else {
//                 //         respuesta = "Formato incorrecto. Use: nombre:teléfono";
//                 //     }
//                 // } else {
//                 //     respuesta = "Comando no reconocido";
//                 // }

//                 // Cifrar respuesta
//                 // byte[] encryptedResp = AES_Chipher.doFinal(respuesta.getBytes());

//                 // Firmar respuesta
//                 // Signature dsa = Signature.getInstance("SHA256withRSA");
//                 // dsa.initSign(SV_keyPair.getPrivate());
//                 // dsa.update(respuesta.getBytes());
//                 // byte[] firma = dsa.sign();

//                 // Enviar respuesta cifrada
//                 // out.writeInt(encryptedResp.length);
//                 // out.write(encryptedResp);

//                 // // Enviar firma
//                 // out.writeInt(firma.length);
//                 // out.write(firma);

//                 // if (s) {
//                 //     sck.close();
//                 // }
//             }

//         // } catch (Exception e) {
//             // System.err.println("Error en comunicación con cliente: " + e.getMessage());
//             // e.printStackTrace();
//         // } finally {
//         //     try {
//         //         sck.close();
//         //     } catch (IOException e) {
//         //         System.err.println("Error cerrando socket: " + e.getMessage());
//             // }
//     //  }
//     }

// }
