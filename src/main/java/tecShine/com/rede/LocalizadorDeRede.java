package tecShine.com.rede;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Classe que procura os Endereços IPV4 nas Interfaces locais da máquina
 * @author daniel
 */
public class LocalizadorDeRede {
    
    /**
     * Cria uma nova Instancia de LocalizadorDeRede
     */
    public LocalizadorDeRede() {
    }
    
    
    /**
     * Pesquisa um endereço IPV4 em uma determinada interface de rede
     * @param iface Interface que deve ser procurado (eth0, eth1, etc)
     * @return Endereço IPV4 ou Null se não encontrado nenhum
     */
    public InetAddress busqueEndereco(String iface) {
        InetAddress add = null;
        try {
            NetworkInterface placa = NetworkInterface.getByName(iface);
            add = this.recupereEnderecoIpv4(placa);
        }
        catch (SocketException e) {
            System.out.println(e.getMessage());
        }
        return add;
    }
    
    /**
     * Pesquisa um endereço IPV4 válido na Internet em todas as interfaces de rede.
     * Será retornado o primeiro endereço encontrado
     * @return Endereço IPV4 ou Null se não encontrado nenhum
     */
    public InetAddress busqueEnderecoInternet() {
        InetAddress add = null;
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            NetworkInterface ifaceAtual = null;
            InetAddress addAtual = null;
            while (e.hasMoreElements()) {
                ifaceAtual = (NetworkInterface)e.nextElement();
                addAtual = this.recupereEnderecoIpv4(ifaceAtual);
                if (addAtual != null) {
                    if (!addAtual.isSiteLocalAddress() && !addAtual.isLoopbackAddress()) {
                        return addAtual;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        return add;
    }
    
    private InetAddress recupereEnderecoIpv4(NetworkInterface net) {
        InetAddress add = null;
        Enumeration e = net.getInetAddresses();
        while (e.hasMoreElements()) {
            try {
                add = (Inet4Address)e.nextElement();
            }
            catch(ClassCastException exception) {}
        }   
        return add;
    }
    
}
