import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;

public class DNS {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        String zeroTo255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
        String IP_REGEX = zeroTo255 + "\\." + zeroTo255 + "\\."
                + zeroTo255 + "\\." + zeroTo255;
        while (true){
            String _input = new Scanner(System.in).nextLine();
            if(_input.contains("resolve")){
                String[] words = _input.split(" ");
                String option = words[1];
                if(option.matches(IP_REGEX)){
                    try {
                        InetAddress _ip = InetAddress.getByName(option);
                        byte[] bytes = _ip.getAddress();
                        System.out.println(InetAddress.getByAddress(bytes).getHostName());
                    } catch (UnknownHostException e){
                        System.out.println("Nu exista asa IP");
                    }
                } else {
                    try {
                        InetAddress doms = InetAddress.getByName(option);
                        InetAddress[] _ips = InetAddress.getAllByName(doms.getCanonicalHostName());
                        for(int i = 0; i < _ips.length; i++) System.out.println(_ips[i]);
                    } catch (UnknownHostException e){
                        System.out.println("Nu exista asa domeniul");
                    }
                }
            } else if(_input.contains("use dns")){
                String[] words = _input.split(" ");
                String _ip = words[2];
                if(_ip.matches(IP_REGEX)){
                    System.setProperty("sun.net.spi.nameservice.nameservers", _ip);
                    System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
                    System.out.println("DNS-ul a fost setat cu succes!");
                } else if(_ip.equalsIgnoreCase("default")){
                    System.setProperties(props);
                    System.out.println("DNS-ul sistemului a fost setat cu succes!");
                } else {
                    System.out.println("DNS-ul nu a putut fi citit.");
                }
            } else if(_input.equalsIgnoreCase("exit")){
                break;
            }
        }
    }
}
