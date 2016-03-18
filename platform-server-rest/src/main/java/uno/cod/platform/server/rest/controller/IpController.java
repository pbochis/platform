package uno.cod.platform.server.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.cod.platform.server.rest.RestUrls;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
            if(inetAddress.isSiteLocalAddress())
                return new ResponseEntity<>(inetAddress.getHostAddress(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
