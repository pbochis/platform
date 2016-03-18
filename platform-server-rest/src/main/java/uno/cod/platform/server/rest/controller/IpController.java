package uno.cod.platform.server.rest.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@RestController
public class IpController {

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public ResponseEntity<String> ip() throws SocketException {
        List<InetAddress> addrList = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ifc = networkInterfaces.nextElement();
            if (ifc.isUp()) {
                Enumeration<InetAddress> inetAddresses = ifc.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    addrList.add(inetAddresses.nextElement());
                }
            }
        }

        for(InetAddress inetAddress : addrList) {
            if(inetAddress.isSiteLocalAddress()) {
                if(inetAddress.getHostAddress().startsWith("10.")) {
                    return new ResponseEntity<>(inetAddress.getHostAddress(), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/ip2", method = RequestMethod.GET)
    public ResponseEntity<String> ip2() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Metadata-Flavor", "Google");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/ip", HttpMethod.GET, entity, String.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}
